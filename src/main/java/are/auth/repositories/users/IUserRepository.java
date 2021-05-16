package are.auth.repositories.users;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import are.auth.entities.User;

public interface IUserRepository extends CrudRepository<User, Long>  {
    Optional<User> findByEmail(String userEmail);
}
