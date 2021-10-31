package are.auth.controllers.bets;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import are.auth.dtos.BetDTO;

public interface IBetsController {

    public BetDTO saveBet(@RequestBody BetDTO betDto);

    public List<BetDTO> getBets();

    public BetDTO findById(@PathVariable Long id);

    public void deleteById(@PathVariable Long id);
}
