package are.auth.repositories.bets;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import are.auth.entities.bets.AddBet;
import are.auth.entities.bets.BetsUsersId;

public interface IBetsBetsRepository extends CrudRepository<AddBet, BetsUsersId> {

    List<AddBet> findByBetsUsersIdBetId(Long betId);
    List<AddBet> findByBetsUsersIdUserId(Long userId);

}
