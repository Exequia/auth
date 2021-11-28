package are.auth.utils.bets;

import java.security.InvalidParameterException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;

import are.auth.dtos.bets.AddBetDTO;
import are.auth.dtos.bets.BetDTO;
import are.auth.entities.User;
import are.auth.entities.bets.AddBet;
import are.auth.entities.bets.Bet;
import are.auth.entities.bets.BetStatus;
import are.auth.entities.bets.BetsOwners;
import are.auth.entities.bets.BetsUsersId;
import are.auth.repositories.bets.IBetsBetsRepository;
import are.auth.repositories.bets.IBetsOwnersRepository;
import are.auth.repositories.bets.IBetsRepository;
import are.auth.repositories.bets.IBetsStatusRepository;
import are.auth.services.UserAuthDetailsService;
import are.auth.utils.roles.IRoleUtils;
import are.auth.utils.users.IUserUtils;

public class BetsUtils implements IBetsUtils {

    private static final Logger log = LoggerFactory.getLogger(BetsUtils.class);

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BetStatus betStatus;

    @Autowired
    private IBetsRepository betsRepository;

    @Autowired
    private IBetsOwnersRepository betsOwnersRepository;

    // @Autowired
    // private IUserRepository userRepository;

    @Autowired
    private IBetsBetsRepository betsBetsRepository;

    @Autowired
    private IRoleUtils roleUtils;

    @Autowired
    private UserAuthDetailsService uathService;

    @Autowired
    private IBetsStatusRepository betsStatusRepository;

    @Autowired
    private IUserUtils userUtils;

    @Override
    public BetDTO convertEntityToDto(Bet bet) throws ParseException {
        log.info("start convertEntityToDto: " + bet.toString());
        BetDTO betDTO = modelMapper.map(bet, BetDTO.class);
        log.info("start convertEntityToDto: " + betDTO.toString());
        return betDTO;
    }

    @Override
    public AddBetDTO convertEntityToDto(AddBet addBet) throws ParseException {
        log.info("start convertEntityToDto: " + addBet.toString());
        AddBetDTO addBetDTO = modelMapper.map(addBet, AddBetDTO.class);
        log.info("start convertEntityToDto: " + addBetDTO.toString());
        return addBetDTO;
    }

    @Override
    public Bet convertDtoToEntity(BetDTO betDto) throws ParseException {
        log.info("start convertDtoToEntity: " + betDto.toString());
        Bet bet = modelMapper.map(betDto, Bet.class);
        if (bet.getStatus() == null) {
            bet.setStatus(betStatus);
        }
        log.info("start convertDtoToEntity: " + bet.toString());
        return bet;
    }

    @Override
    public AddBet convertDtoToEntity(AddBetDTO addBetDto) throws ParseException {
        log.info("start convertDtoToEntity: " + addBetDto.toString());
        AddBet addBet = modelMapper.map(addBetDto, AddBet.class);
        if (addBet.getBetsUsersId() == null) {
            BetsUsersId betsUsersId = new BetsUsersId(addBetDto.getBetId(), addBetDto.getUserId());
            addBet.setBetsUsersId(betsUsersId);
        }
        ;
        if (addBet.getDate() == null) {
            addBet.setDate(new Date());
        }
        log.info("start convertDtoToEntity: " + addBet.toString());
        return addBet;
    }

    @Override
    public Bet save(Bet bet) {
        bet = betsRepository.save(bet);
        this.saveOwners(bet);
        return bet;
    }

    @Override
    public BetsOwners saveOwners(Bet bet) {
        // owners.forEach((owner) -> {
        // BetsOwners betOwners = new BetsOwners(new BetsUsersId(bet.getId(),
        // owner.getId()));
        // this.betsOwnersRepository.save(betOwners);
        // });

        User loggedUser = uathService.getLoggedUser();
        BetsOwners betOwners = new BetsOwners(new BetsUsersId(bet.getId(), loggedUser.getId()));
        return this.betsOwnersRepository.save(betOwners);
    }

    @Override
    public void addBet(AddBetDTO addBetDto) {
        AddBet addBet = this.convertDtoToEntity(addBetDto);
        User loggedUser = uathService.getLoggedUser();
        addBet.setDate(new Date());
        addBet.setBetsUsersId(new BetsUsersId(addBetDto.getBetId(), loggedUser.getId()));
        this.betsBetsRepository.save(addBet);
    }

    @Override
    public AddBetDTO findAddedBet(Long betId, Long userId) {
        Optional<Bet> bet = this.betsRepository.findById(betId);
        BetDTO betDto = this.convertEntityToDto(bet.get());
        Optional<AddBet> addBet = this.betsBetsRepository.findById(new BetsUsersId(betId, userId));
        AddBetDTO addBetDTO = this.convertEntityToDto(addBet.get());
        addBetDTO.setBetData(betDto);
        return addBetDTO;
    }

    @Override
    public List<BetDTO> getOpenBets() {
        Iterable<Bet> bets = betsRepository.findByStatus(betsStatusRepository.findById(1L).get());
        // this.addBetsLinked(bets);
        List<BetDTO> betsDto = StreamSupport.stream(bets.spliterator(), false)
                .map((bet) -> this.convertEntityToDto(bet)).collect(Collectors.toList());
        return this.addBetsLinked(betsDto);
    }

    @Override
    public List<BetDTO> addBetsLinked(Iterable<BetDTO> betsDto) {
        User loggedUser = uathService.getLoggedUser();
        return StreamSupport.stream(betsDto.spliterator(), false).map((betDto) -> setAddedBet(betDto, loggedUser)).collect(Collectors.toList());
    }

    @Override
    public BetDTO setAddedBet(BetDTO betDto, User loggedUser) {
        AddBet addedBet = this.betsBetsRepository.findById(new BetsUsersId(betDto.getId(), loggedUser.getId())).orElse(null);
        if (null != addedBet) {
            betDto.setAddedBet(addedBet.getModel());
        }
        BetsUsersId betKey = new BetsUsersId(betDto.getId(), loggedUser.getId());
        if (null != betKey &&  this.betsOwnersRepository.existsByBetsUsersId(betKey)) {
            betDto.setOwnerId(0L);
        } else {
            betDto.setOwnerId(null);
        }
        return betDto;
    }

    @Override
    public void close(AddBetDTO betResults) throws InvalidParameterException {
        Optional<Bet> optionalBet = betsRepository.findById(betResults.getBetId());
        if (null != optionalBet) {
            User loggedUser = uathService.getLoggedUser();
            Bet bet = optionalBet.get();
            BetsUsersId betKey = new BetsUsersId(betResults.getBetId(), loggedUser.getId());
            if (null != betKey &&  this.betsOwnersRepository.existsByBetsUsersId(betKey)) {
                bet.setEndDate(new Date());
                bet.setStatus(betsStatusRepository.findById(3L).get());
                bet.setResult(betResults.getModel());
                betsRepository.save(bet);
            } else {
                throw new InvalidParameterException("betIdNotExists");
            }
        } else {
            throw new InvalidParameterException("betIdNotExists");
        }
    }

    @Override
    public BetDTO allBets(Long betId) {
        Optional<Bet> bet = betsRepository.findById(betId);
        BetDTO betDto = this.convertEntityToDto(bet.get());

        List<AddBet> allbets = this.betsBetsRepository.findByBetsUsersIdBetId(betId);
        List<AddBetDTO> allBetsDto = StreamSupport.stream(allbets.spliterator(), false)
                .map((betBet) -> {
                    Optional<User> user = this.userUtils.findUserById(betBet.getBetsUsersId().getUserId());
                    AddBetDTO addBetDTO = this.convertEntityToDto(betBet);
                    addBetDTO.setUserName(user.get().getAlias());
                    return addBetDTO;
                }).collect(Collectors.toList());
        betDto.setAllBets(allBetsDto);
        
        return betDto;
    }
}
