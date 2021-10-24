package are.auth.repositories.roles;

import org.springframework.data.repository.CrudRepository;

import are.auth.entities.Role;

public interface IRoleRepository extends CrudRepository<Role, Long>  {
}
