package are.auth.entities.bets;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Embeddable
public class BetsUsersId implements Serializable {

    public BetsUsersId() {
    }


    @Column(name = "bet_id")
    private Long betId;
    
    @Column(name = "user_id")
    private Long userId;
}
