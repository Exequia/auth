package are.auth.controllers.bets;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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
import are.auth.entities.bets.AddBet;
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
        //TODO: CREAR VALIDADOR
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
    public List<BetDTO> getBets() {
        log.info("start getBets");
        Iterable<Bet> bets = betsRepository.findAll();
        List<BetDTO> betsDTO = StreamSupport.stream(bets.spliterator(), false)
                .map((bet) -> betsUtils.convertEntityToDto(bet)).collect(Collectors.toList());
        log.info("end getBets with: " + bets.toString());
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
    public AddBetDTO addBet(@RequestBody AddBetDTO addBetDto) {
        log.info("start addBet for: " + addBetDto.toString());
        AddBet addBet = betsUtils.convertDtoToEntity(addBetDto);
        addBet = betsUtils.addBet(addBet);
        addBetDto.setDate(addBet.getDate());
        log.info("end addBet:" + addBetDto.toString());
        return addBetDto;
    }
}
