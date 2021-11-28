package are.auth.controllers.users;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.expression.ParseException;

import are.auth.dtos.UserDTO;
import are.auth.dtos.UserDTORequest;
import are.auth.dtos.UserDTOResponse;
import are.auth.entities.User;
import are.auth.models.JwtAuthenticationResponse;
import are.auth.repositories.users.IUserRepository;
import are.auth.utils.users.UserUtils;

@RestController
@RequestMapping("/users")
public class UserController implements IUserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    
    @Autowired
    private IUserRepository userRepository;
    
    @Autowired
    private UserUtils userUtils;

    @Override
    @GetMapping
    @PreAuthorize("hasAnyAuthority('god', 'admin')")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDTO> getAllUsers() {
        log.info("start getAllUsers");
        Iterable<User> users = userRepository.findAll();
        List<UserDTO> usersDto = StreamSupport.stream(users.spliterator(), false)
                .map((user) -> userUtils.convertEntityToDto(user)).collect(Collectors.toList());
        log.info("end getAllUsers with: " + usersDto.toString());
        return usersDto;
    }

    @Override
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public UserDTO findByid(@PathVariable Long id) {
        log.info("start findByid for id: " + id);
        UserDTO userDto = userUtils.findDtoById(id);
        log.info("end findByid with: " + userDto.toString());
        return userDto;
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public JwtAuthenticationResponse saveUser(@RequestBody UserDTORequest userDto) throws ParseException, InvalidParameterException {
        log.info("start saveUser for: " + userDto.toString());
        User user = this.userUtils.saveUser(userDto);
        UserDTOResponse newUserDto = userUtils.convertEntityToDto(user);
        String token = this.userUtils.getToken(userDto);
        log.info("end saveUser:" + newUserDto.toString());
        return new JwtAuthenticationResponse(token, newUserDto);
    }
}
