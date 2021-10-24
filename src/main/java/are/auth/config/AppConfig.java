package are.auth.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import are.auth.dtos.RoleDTO;
import are.auth.dtos.UserStatusDTO;
import are.auth.utils.roles.RoleUtils;
import are.auth.utils.users.UserUtils;

@Configuration
@ComponentScan({ "are.auth.utils" })
public class AppConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public UserUtils userUtils() {
        return new UserUtils();
    }

    @Bean
    public RoleUtils roleUtils() {
        return new RoleUtils();
    }

    @Bean
    public RoleDTO roleDTO() {
        return new RoleDTO();
    }

    @Bean
    public UserStatusDTO userStatusDTO() {
        return new UserStatusDTO();
    }

}
