package are.auth.dtos;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class UserDTORequest extends UserDTO {

    @NotBlank(message = "The alias name is required.")
    private String password;
}
