package are.auth.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Embeddable
public class BetsOwnersId implements Serializable {

    public BetsOwnersId() {
    }


    @Column(name = "bet_id")
    private Long betId;
    
    @Column(name = "user_id")
    private Long userId;
}
