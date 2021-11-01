package are.auth.repositories.bets;

import org.springframework.data.repository.CrudRepository;

import are.auth.entities.BetsOwners;

public interface IBetsOwnersRepository extends CrudRepository<BetsOwners, Long> {
    
}
