package are.auth.utils.roles;

import org.springframework.expression.ParseException;

import are.auth.dtos.RoleDTO;
import are.auth.entities.Role;

public interface IRoleUtils {
    public RoleDTO convertEntityToDto(Role role) throws ParseException;

    Role convertDtoToEntity(RoleDTO roleDto) throws ParseException;

}
