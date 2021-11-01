package are.auth.repositories.bets;

import org.springframework.data.repository.CrudRepository;

import are.auth.entities.bets.Bet;

public interface IBetsRepository extends CrudRepository<Bet, Long> {
}
