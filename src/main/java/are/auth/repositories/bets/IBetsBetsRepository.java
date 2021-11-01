package are.auth.repositories.bets;

import org.springframework.data.repository.CrudRepository;

import are.auth.entities.bets.AddBet;

public interface IBetsBetsRepository extends CrudRepository<AddBet, Long> {

}
