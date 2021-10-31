package are.auth.utils.bets;

import org.springframework.expression.ParseException;

import are.auth.dtos.BetDTO;
import are.auth.entities.Bet;

public interface IBetsUtils {

    public BetDTO convertEntityToDto(Bet Bet) throws ParseException;

    public Bet convertDtoToEntity(BetDTO BetDTO) throws ParseException;
}
