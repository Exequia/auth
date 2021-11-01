package are.auth.utils.bets;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;

import are.auth.dtos.bets.AddBetDTO;
import are.auth.dtos.bets.BetDTO;
import are.auth.entities.Role;
import are.auth.entities.User;
import are.auth.entities.bets.AddBet;
import are.auth.entities.bets.Bet;
import are.auth.entities.bets.BetStatus;
import are.auth.entities.bets.BetsOwners;
import are.auth.entities.bets.BetsUsersId;
import are.auth.repositories.bets.IBetsBetsRepository;
import are.auth.repositories.bets.IBetsOwnersRepository;
import are.auth.repositories.bets.IBetsRepository;
import are.auth.repositories.users.IUserRepository;
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

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IBetsBetsRepository betsBetsRepository;

    @Autowired
    private IRoleUtils roleUtils;

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
        };
        if (addBet.getDate() == null) {
            addBet.setDate(new Date());
        }
        log.info("start convertDtoToEntity: " + addBet.toString());
        return addBet;
    }

    @Override
    public Bet save(Bet bet) {
        bet = betsRepository.save(bet);
        // Iterable<User> owners = this.saveOwners(bet);
        return bet;
    }

    @Override
    public Iterable<User> saveOwners(Bet bet) {
        // TODO: No es funcional, puesto que pueden modificarse roles a posteriory. hay
        // que cogerlo del modelo apuesta
        List<Role> superRoles = this.roleUtils.getRolesByIds(Arrays.asList(1L, 2L));
        Iterable<User> owners = this.userRepository.findAllByRoleIn(superRoles);

        owners.forEach((owner) -> {
            BetsOwners betOwners = new BetsOwners(new BetsUsersId(bet.getId(), owner.getId()));
            this.betsOwnersRepository.save(betOwners);
        });
        return owners;
    }

    @Override
    public AddBet addBet(AddBet addBet) {
        return this.betsBetsRepository.save(addBet);
    }
}
