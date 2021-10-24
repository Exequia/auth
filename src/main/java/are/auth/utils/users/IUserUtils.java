package are.auth.utils.users;

import org.springframework.expression.ParseException;

import are.auth.dtos.UserDTORequest;
import are.auth.dtos.UserDTOResponse;
import are.auth.entities.User;

public interface IUserUtils {

    public UserDTOResponse convertEntityToDto(User user) throws ParseException;

    public User convertDtoToEntity(UserDTORequest userDto) throws ParseException;

}