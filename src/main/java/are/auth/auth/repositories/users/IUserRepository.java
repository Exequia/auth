package are.auth.auth.repositories.users;

import org.springframework.data.repository.CrudRepository;

import are.auth.auth.models.User;

public interface IUserRepository extends CrudRepository<User, String>  {
    
}
