package are.auth.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import are.auth.entities.User;
import are.auth.models.UserPrincipal;
import are.auth.repositories.users.IUserRepository;

@Component
public class UserAuthDetailsService implements UserDetailsService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public UserPrincipal loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        User user = userRepository
                .findByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User name " + userEmail + "Not Found in DB"));
        return UserPrincipal.create(user);
    }
}