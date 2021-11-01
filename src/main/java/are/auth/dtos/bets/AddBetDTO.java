package are.auth.dtos.bets;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class AddBetDTO {
    @NotBlank(message = "addBetBetIdRequired")
    private Long betId;

    @NotBlank(message = "addBetUserIdRequired")
    private Long userId;

    @NotBlank(message = "addBetBetModelRequired")
    private String bet;

    private Date date;

    private BetDTO betData;
}
