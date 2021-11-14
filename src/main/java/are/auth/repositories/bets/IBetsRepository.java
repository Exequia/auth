package are.auth.repositories.bets;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import are.auth.entities.bets.Bet;
import are.auth.entities.bets.BetStatus;

public interface IBetsRepository extends CrudRepository<Bet, Long> {

    List<Bet> findByStatus(BetStatus status);
}
