package are.auth.controllers.bets;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import are.auth.dtos.bets.AddBetDTO;
import are.auth.dtos.bets.BetDTO;
import are.auth.entities.bets.Bet;
import are.auth.repositories.bets.IBetsRepository;
import are.auth.utils.bets.IBetsUtils;

@RestController
@RequestMapping("/bets")
public class BetsController implements IBetsController {

    private static final Logger log = LoggerFactory.getLogger(BetsController.class);
    @Autowired
    private IBetsRepository betsRepository;
    @Autowired
    private IBetsUtils betsUtils;

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public BetDTO saveBet(@RequestBody BetDTO betDto) {
        // TODO: CREAR VALIDADOR
        log.info("start saveBet for: " + betDto.toString());
        Bet bet = betsUtils.convertDtoToEntity(betDto);
        bet = betsUtils.save(bet);
        BetDTO newBetDto = betsUtils.convertEntityToDto(bet);
        log.info("end saveBet:" + newBetDto.toString());
        return newBetDto;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Override
    public List<BetDTO> getOpenBets() {
        log.info("start getOpenBets");
        List<BetDTO> betsDTO = betsUtils.getOpenBets();
        log.info("end getOpenBets with: " + betsDTO.toString());
        return betsDTO;
    }

    @Override
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public BetDTO findById(@PathVariable Long id) {
        log.info("start findByid for id: " + id);
        Optional<Bet> bet = betsRepository.findById(id);
        BetDTO betDto = betsUtils.convertEntityToDto(bet.get());
        log.info("end findByid with: " + betDto.toString());
        return betDto;
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void deleteById(@PathVariable Long id) {
        log.info("start deleteById for id: " + id);
        betsRepository.deleteById(id);
        log.info("end deleteById");
    }

    @Override
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public void addBet(@RequestBody AddBetDTO addBetDto) {
        log.info("start addBet for: " + addBetDto.toString());
        betsUtils.addBet(addBetDto);
        log.info("end addBet:" + addBetDto.toString());
    }
    
    @Override
    @GetMapping("/{betId}/{userId}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public AddBetDTO findAddedBet(@PathVariable Long betId, @PathVariable Long userId) {
        log.info("start findAddedBet for betId: " + betId + " and userId : " + userId);
        AddBetDTO addBetDTO = betsUtils.findAddedBet(betId, userId);
        log.info("end findAddedBet with: " + addBetDTO.toString());
        return addBetDTO;
    }

    @Override
    @PostMapping("/close")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void close(@RequestBody AddBetDTO betResults) {
        log.info("start close for: " + betResults.toString());
        betsUtils.close(betResults);
        log.info("end close:" + betResults.toString());
    }
    
    @Override
    @GetMapping("/allBets/{betId}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public BetDTO allBets(@PathVariable Long betId) {
        log.info("start allBets for betId: " + betId);
        BetDTO betDto = betsUtils.allBets(betId);
        log.info("end allBets with: " + betDto.toString());
        return betDto;
    }

    @Override
    @GetMapping("/closed")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<BetDTO> getClosedBets() {
        log.info("start getClosedBets");
        List<BetDTO> betsDTO = betsUtils.getClosedBets();
        log.info("end getClosedBets with: " + betsDTO.toString());
        return betsDTO;
    }
}
