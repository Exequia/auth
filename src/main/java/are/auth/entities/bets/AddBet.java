package are.auth.entities.bets;

import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Value;

import lombok.Data;

@Data
@Entity
@Table(name = "bets_bets")
public class AddBet {

    public AddBet() {
    }

    public AddBet(BetsUsersId betsUsersId) {
        this.betsUsersId = betsUsersId;
    }

    @EmbeddedId
    private BetsUsersId betsUsersId;

    @Value("")
    private String model;

    private Date date = new Date();
}
