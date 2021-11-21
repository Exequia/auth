package are.auth.repositories.bets;

import org.springframework.data.repository.CrudRepository;

import are.auth.entities.bets.BetsOwners;
import are.auth.entities.bets.BetsUsersId;

public interface IBetsOwnersRepository extends CrudRepository<BetsOwners, Long> {

    public Boolean existsByBetsUsersId(BetsUsersId betsUsersId);
    
}
