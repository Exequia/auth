package are.auth.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import are.auth.dtos.RoleDTO;
import are.auth.dtos.UserStatusDTO;
import are.auth.entities.BetProfile;
import are.auth.entities.BetStatus;
import are.auth.utils.bets.BetsUtils;
import are.auth.utils.bets.IBetsUtils;
import are.auth.utils.roles.IRoleUtils;
import are.auth.utils.roles.RoleUtils;
import are.auth.utils.users.UserUtils;

@Configuration
@ComponentScan({ "are.auth.utils" })
public class AppConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    /* UTILS*/
    @Bean
    public UserUtils userUtils() {
        return new UserUtils();
    }

    @Bean
    public IRoleUtils roleUtils() {
        return new RoleUtils();
    }

    /* DTO */
    @Bean
    public RoleDTO roleDTO() {
        return new RoleDTO();
    }

    @Bean
    public UserStatusDTO userStatusDTO() {
        return new UserStatusDTO();
    }

    @Bean
    public IBetsUtils betsUtils() {
        return new BetsUtils();
    }

    /* CLASS */
    @Bean
    public BetProfile betProfile() {
        return new BetProfile();
    }

    @Bean
    public BetStatus betStatus() {
        return new BetStatus();
    }
}
