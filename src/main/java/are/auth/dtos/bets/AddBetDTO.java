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

    private String userName;

    @NotBlank(message = "addBetBetModelRequired")
    private String model;

    private Date date;

    private BetDTO betData;

    private Boolean isMine;
}
