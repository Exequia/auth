package are.auth.utils.bets;

import java.util.Date;
import java.util.Optional;

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
import are.auth.services.UserAuthDetailsService;
import are.auth.utils.roles.IRoleUtils;

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
    public AddBet addBet(AddBet addBet) {
        return this.betsBetsRepository.save(addBet);
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
}
