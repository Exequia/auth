package are.auth.repositories.bets;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import are.auth.entities.bets.Bet;
import are.auth.entities.bets.BetStatus;

public interface IBetsRepository extends CrudRepository<Bet, Long> {

    List<Bet> findByStatus(BetStatus status);

    @Query(value = "select b.* from bets b join bets_bets bb on (b.id = bb.bet_id) where bb.user_id = ?1 and b.status_id = ?2", nativeQuery = true)
    List<Bet> findByUserIdAndBetStatus(Long userId, Long betStatusId);
}
