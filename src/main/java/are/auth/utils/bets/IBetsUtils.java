package are.auth.utils.bets;

import java.util.List;

import org.springframework.expression.ParseException;

import are.auth.dtos.bets.AddBetDTO;
import are.auth.dtos.bets.BetDTO;
import are.auth.entities.User;
import are.auth.entities.bets.AddBet;
import are.auth.entities.bets.Bet;
import are.auth.entities.bets.BetsOwners;

public interface IBetsUtils {

    public BetDTO convertEntityToDto(Bet Bet) throws ParseException;

    public AddBetDTO convertEntityToDto(AddBet addBet) throws ParseException;

    public Bet convertDtoToEntity(BetDTO BetDTO) throws ParseException;

    public AddBet convertDtoToEntity(AddBetDTO addBetDto) throws ParseException;

    public Bet save(Bet bet);

    public BetsOwners saveOwners(Bet bet);

    public void addBet(AddBetDTO addBetDto);

    public AddBetDTO findAddedBet(Long betId, Long userId);

    public List<BetDTO> getOpenBets();

    public List<BetDTO> addBetsLinked(Iterable<BetDTO> betsDto);

    public BetDTO setAddedBet(BetDTO betDto, User loggedUser);

    public void close(AddBetDTO betResults);

    public BetDTO allBets(Long betId);

    public List<BetDTO> getClosedBets();
}
