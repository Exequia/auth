package are.auth.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import are.auth.entities.User;
import are.auth.models.UserPrincipal;
import are.auth.repositories.users.IUserRepository;
import lombok.Data;

@Data
@Component
public class UserAuthDetailsService implements UserDetailsService {

    @Autowired
    private IUserRepository userRepository;

    private User loggedUser;

    @Override
    public UserPrincipal loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        this.loggedUser = userRepository
                .findByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User name " + userEmail + "Not Found in DB"));
        return UserPrincipal.create(this.loggedUser);
    }
}