package are.auth.utils.users;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.expression.ParseException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

import are.auth.dtos.RoleDTO;
import are.auth.dtos.UserDTO;
import are.auth.dtos.UserDTORequest;
import are.auth.dtos.UserDTOResponse;
import are.auth.dtos.UserStatusDTO;
import are.auth.entities.User;
import are.auth.models.AuthenticateRequest;
import are.auth.models.UserPrincipal;
import are.auth.repositories.users.IUserRepository;
import are.auth.services.JWTTokenProvider;

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

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTTokenProvider jwtTokenProvider;

    @Override
    public UserDTO findDtoById(Long id) {
        Optional<User> user = findUserById(id);
        return this.convertEntityToDto(user.get());
    }

    @Override
    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public UserDTOResponse convertEntityToDto(User user) throws ParseException {
        log.info("start convertEntityToDto: " + user.toString());
        UserDTOResponse userDTOResponse = modelMapper.map(user, UserDTOResponse.class);
        log.info("start convertEntityToDto: " + userDTOResponse.toString());
        return userDTOResponse;
    }

    @Override
    public User convertDtoToEntity(UserDTORequest userDto) throws ParseException, InvalidParameterException {
        log.info("start convertDtoToEntity: " + userDto.toString());
        this.validateDTO(userDto);
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

    @Override
    public String getToken(UserDTORequest userDto) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("regular"));
        UserPrincipal principal = new UserPrincipal(userDto.getEmail(), userDto.getPassword(), authorities);
        return jwtTokenProvider.generateToken(principal);
    }

    @Override
    public String getToken(AuthenticateRequest authenticateRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authenticateRequest.getEmail(), authenticateRequest.getPassword()));
        return jwtTokenProvider.generateToken((UserPrincipal) authentication.getPrincipal());
    }

    @Override
    public User saveUser(UserDTORequest userDto) throws InvalidParameterException {
        User user = this.convertDtoToEntity(userDto);
        return userRepository.save(user);
    }

    private void validateDTO(UserDTORequest userDto) throws InvalidParameterException {
        if (null == userDto.getEmail()) {
            throw new InvalidParameterException("emailNotNull");
        }
        if (userRepository.existsByEmail(userDto.getEmail()))
            throw new InvalidParameterException("emailExists");
        if (null == userDto.getAlias())
            throw new InvalidParameterException("aliasNotNull");
        if ("" == userDto.getAlias())
            throw new InvalidParameterException("aliasNotEmpty");
    }
}
