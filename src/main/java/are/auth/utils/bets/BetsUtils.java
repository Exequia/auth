package are.auth.utils.bets;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import com.google.common.collect.Iterables;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;

import are.auth.dtos.BetDTO;
import are.auth.entities.Bet;
import are.auth.entities.BetStatus;
import are.auth.entities.BetsOwners;
import are.auth.entities.BetsOwnersId;
import are.auth.entities.Role;
import are.auth.entities.User;
import are.auth.repositories.bets.IBetsOwnersRepository;
import are.auth.repositories.bets.IBetsRepository;
import are.auth.repositories.roles.IRoleRepository;
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
    private IRoleUtils roleUtils;

    @Override
    public BetDTO convertEntityToDto(Bet bet) throws ParseException {
        log.info("start convertEntityToDto: " + bet.toString());
        BetDTO betDTO = modelMapper.map(bet, BetDTO.class);
        log.info("start convertEntityToDto: " + betDTO.toString());
        return betDTO;
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
    public Bet save(Bet bet) {
        bet = betsRepository.save(bet);
        Iterable<User> owners = this.saveOwners(bet);
        return bet;
    }

    @Override
    public Iterable<User> saveOwners(Bet bet) {
        List<Role> superRoles = this.roleUtils.getRolesByIds(Arrays.asList(1L, 2L));
        Iterable<User> owners = this.userRepository.findAllByRoleIn(superRoles);

        owners.forEach((owner) -> {
            BetsOwners betOwners = new BetsOwners(new BetsOwnersId(bet.getId(), owner.getId()));
            this.betsOwnersRepository.save(betOwners);
        });
        return owners;
    }
}
