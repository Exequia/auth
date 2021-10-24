package are.auth.controllers.roles;

import java.util.List;
import are.auth.dtos.RoleDTO;

public interface IRoleController {

    public List<RoleDTO> getRoles();
}
