package are.auth.utils.users;

import java.util.Optional;

import org.springframework.expression.ParseException;

import are.auth.dtos.UserDTO;
import are.auth.dtos.UserDTORequest;
import are.auth.dtos.UserDTOResponse;
import are.auth.entities.User;
import are.auth.models.AuthenticateRequest;

public interface IUserUtils {

    public UserDTO findDtoById(Long id);
    
    public Optional<User> findUserById(Long id);

    public UserDTOResponse convertEntityToDto(User user) throws ParseException;

    public User convertDtoToEntity(UserDTORequest userDto) throws ParseException;

    public String getToken(UserDTORequest userDto);

    public String getToken(AuthenticateRequest authenticateRequest);
    
    public User saveUser(UserDTORequest userDto);
}
