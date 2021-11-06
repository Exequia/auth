package are.auth.utils.users;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.expression.ParseException;
import org.springframework.security.crypto.password.PasswordEncoder;

import are.auth.dtos.RoleDTO;
import are.auth.dtos.UserDTORequest;
import are.auth.dtos.UserDTOResponse;
import are.auth.dtos.UserStatusDTO;
import are.auth.entities.User;
import are.auth.repositories.users.IUserRepository;

public class UserUtils implements IUserUtils {

    private static final Logger log = LoggerFactory.getLogger(UserUtils.class);

    @Autowired
    private ApplicationContext context;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private IUserRepository userRepository;

    @Override
    public UserDTOResponse convertEntityToDto(User user) throws ParseException {
        log.info("start convertEntityToDto: " + user.toString());
        UserDTOResponse userDTOResponse = modelMapper.map(user, UserDTOResponse.class);
        log.info("start convertEntityToDto: " + userDTOResponse.toString());
        return userDTOResponse;
    }

    @Override
    public User convertDtoToEntity(UserDTORequest userDto) throws ParseException {
        log.info("start convertDtoToEntity: " + userDto.toString());
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        if (null == userDto.getRole()) {
            userDto.setRole(context.getBean(RoleDTO.class));
        }
        userDto.setStatus(context.getBean(UserStatusDTO.class));
        User user = modelMapper.map(userDto, User.class);

        // if (userDto.getId() != null) {
        // User oldPost = postService.getPostById(userDto.getId());
        // user.setRedditID(oldPost.getRedditID());
        // user.setSent(oldPost.isSent());
        // }

        log.info("start convertDtoToEntity: " + user.toString());
        return user;
    }

    public UserDTOResponse getUserByEmail(String email) {
        Optional<User> user = this.userRepository.findByEmail(email);
        return this.convertEntityToDto(user.get());
    }
}
