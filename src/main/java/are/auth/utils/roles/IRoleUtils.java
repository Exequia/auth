package are.auth.utils.roles;

import java.util.List;

import org.springframework.expression.ParseException;

import are.auth.dtos.RoleDTO;
import are.auth.entities.Role;

public interface IRoleUtils {

    public RoleDTO convertEntityToDto(Role role) throws ParseException;

    public Role convertDtoToEntity(RoleDTO roleDto) throws ParseException;

    public List<Role> getRolesByIds(List<Long> asList);

}
