package are.auth.dtos;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public abstract class UserDTO {

    @NotBlank(message = "The email is required.")
    private String email;
    @NotBlank(message = "The alias name is required.")
    private String alias;
    private RoleDTO role;
    private PeopleDTO people;
    private UserStatusDTO status;
}
