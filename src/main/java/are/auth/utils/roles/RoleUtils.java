package are.auth.utils.roles;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;

import are.auth.dtos.RoleDTO;
import are.auth.entities.Role;

public class RoleUtils implements IRoleUtils {

    private static final Logger log = LoggerFactory.getLogger(RoleUtils.class);

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public RoleDTO convertEntityToDto(Role role) throws ParseException {
        log.info("start convertEntityToDto: " + role.toString());
        RoleDTO roleDto = modelMapper.map(role, RoleDTO.class);
        log.info("start convertEntityToDto: " + roleDto.toString());
        return roleDto;
    }

    @Override
    public Role convertDtoToEntity(RoleDTO roleDto) throws ParseException {
        log.info("start convertDtoToEntity: " + roleDto.toString());
        Role role = modelMapper.map(roleDto, Role.class);
        log.info("start convertDtoToEntity: " + role.toString());
        return role;
    }

}
