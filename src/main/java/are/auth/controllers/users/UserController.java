package are.auth.controllers.users;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import are.auth.entities.User;
import are.auth.repositories.users.IUserRepository;

@RestController
@RequestMapping("/users")
public class UserController implements IUserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private IUserRepository userRepository;

    @GetMapping
    @PreAuthorize("hasRole('admin') OR hasRole('user')")
    public Iterable<User> getAllUsers() {
        log.info("start getAllUsers");
        Iterable<User> users = userRepository.findAll();
        users.forEach(user -> System.out.println("user:" + user));
        log.info("end getAllUsers");
        return users;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Optional<User> findByid(@PathVariable Long id) {
        log.info("start findByid");
        Optional<User> user = userRepository.findById(id);
        log.info("end findByid");
        return user;
    }
}
