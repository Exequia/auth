package are.auth.controllers.roles;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import are.auth.dtos.RoleDTO;
import are.auth.entities.Role;
import are.auth.repositories.roles.IRoleRepository;
import are.auth.utils.roles.RoleUtils;

@RestController
@RequestMapping("/roles")
public class RoleController implements IRoleController {

    private static final Logger log = LoggerFactory.getLogger(RoleController.class);
    @Autowired
    private IRoleRepository roleRepository;
    @Autowired
    private RoleUtils roleUtils;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('god', 'admin')")
    @ResponseStatus(HttpStatus.OK)
    @Override
    public List<RoleDTO> getRoles() {
        log.info("start getRoles");
        Iterable<Role> roles = roleRepository.findAll();
        List<RoleDTO> rolesDto = StreamSupport.stream(roles.spliterator(), false)
                .map((user) -> roleUtils.convertEntityToDto(user)).collect(Collectors.toList());
        log.info("end getRoles with: " + rolesDto.toString());
        return rolesDto;
    }
}
