package are.auth.utils.bets;

import org.springframework.expression.ParseException;

import are.auth.dtos.bets.AddBetDTO;
import are.auth.dtos.bets.BetDTO;
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

    public AddBet addBet(AddBet addBet);

    public AddBetDTO findAddedBet(Long betId, Long userId);

}
