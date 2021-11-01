package are.auth.utils.roles;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;

import are.auth.dtos.RoleDTO;
import are.auth.entities.Role;
import are.auth.repositories.roles.IRoleRepository;

public class RoleUtils implements IRoleUtils {

    private static final Logger log = LoggerFactory.getLogger(RoleUtils.class);

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private IRoleRepository roleRepository;

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

    @Override
    public List<Role> getRolesByIds(List<Long> rolesIds) { 
        Iterable<Role> superRoles = this.roleRepository.findAllByIdIn(rolesIds);
        List<Role> roles = new ArrayList<Role>();
        superRoles.forEach(roles::add);
        return  roles;
    }

}
