package are.auth.controllers.users;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import are.auth.dtos.UserDTO;
import are.auth.dtos.UserDTORequest;
import are.auth.models.JwtAuthenticationResponse;

public interface IUserController {
    public List<UserDTO> getAllUsers();

    public UserDTO findByid(@PathVariable Long id);

    public JwtAuthenticationResponse saveUser(@RequestBody UserDTORequest user);
}
