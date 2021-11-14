package are.auth.repositories.bets;

import org.springframework.data.repository.CrudRepository;

import are.auth.entities.bets.BetStatus;

public interface IBetsStatusRepository extends CrudRepository<BetStatus, Long> {
}
