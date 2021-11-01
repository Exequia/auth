package are.auth.repositories.users;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import are.auth.entities.Role;
import are.auth.entities.User;

public interface IUserRepository extends CrudRepository<User, Long> {

    public Optional<User> findByEmail(String userEmail);

    public Iterable<User> findAllByRole(Role role);

    public Iterable<User> findAllByRoleIn(List<Role> roles);
}
