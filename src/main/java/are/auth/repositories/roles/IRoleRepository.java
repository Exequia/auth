package are.auth.repositories.roles;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import are.auth.entities.Role;

public interface IRoleRepository extends CrudRepository<Role, Long>  {
    
    Iterable<Role> findAllByIdIn(List<Long> rolesId);
}
