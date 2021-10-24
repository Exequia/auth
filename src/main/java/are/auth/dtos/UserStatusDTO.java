package are.auth.dtos;

import org.springframework.beans.factory.annotation.Value;

import lombok.Data;

@Data
public class UserStatusDTO
 {
    @Value("${status.id}")
    private Long id;
    private String name;
}
