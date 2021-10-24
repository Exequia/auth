package are.auth.dtos;

import org.springframework.beans.factory.annotation.Value;

import lombok.Data;

@Data
public class RoleDTO {
    @Value("${role.id}")
    private Long id;
    private String name;
}
