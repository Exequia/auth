package are.auth.dtos;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Email;

import lombok.Data;

@Data
public abstract class UserDTO {

    @NotBlank(message = "emailRequired.")
    @Email(message = "email should be a valid email")
    private String email;

    @NotNull(message = "aliasNotNull")
    @NotBlank(message = "aliasNotBlank.")
    private String alias;
    private RoleDTO role;
    private PeopleDTO people;
    private UserStatusDTO status;
}
