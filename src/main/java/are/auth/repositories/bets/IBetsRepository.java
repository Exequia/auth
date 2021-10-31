package are.auth.repositories.bets;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import are.auth.entities.Bet;

public interface IBetsRepository extends CrudRepository<Bet, Long> {
}
