package are.auth.auth.controllers.users;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import are.auth.auth.models.User;
import are.auth.auth.repositories.users.IUserRepository;

@RestController
@RequestMapping("/users")
public class UserController implements IUserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private IUserRepository userRepository;

    @GetMapping
    public Iterable<User> getAllUsers() {
        log.info("start getAllUsers");
        Iterable<User> users = userRepository.findAll();
        users.forEach(user -> System.out.println("user:" + user));
        log.info("end getAllUsers");
        return users;
    }

}
