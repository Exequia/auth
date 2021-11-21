package are.auth.controllers.bets;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import are.auth.dtos.bets.AddBetDTO;
import are.auth.dtos.bets.BetDTO;

public interface IBetsController {

    public BetDTO saveBet(@RequestBody BetDTO betDto);

    public List<BetDTO> getOpenBets();

    public BetDTO findById(@PathVariable Long id);

    public void deleteById(@PathVariable Long id);

    public void addBet(@RequestBody AddBetDTO addBetDto);

    public AddBetDTO findAddedBet(@PathVariable Long betId, @PathVariable Long userId);

    public void close(@RequestBody AddBetDTO betResults);
}
