package are.auth.dtos.bets;

import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Value;

import lombok.Data;

@Data
public class BetDTO {
    @NotBlank(message = "betIdRequired")
    private Long id;

    @NotBlank(message = "betNameRequired")
    private String name;

    private Long profileId;

    private String config;
    
    private String result;
    
    @Value("${bet.status.id}")
    private Long statusId;
}
