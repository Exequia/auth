package are.auth.entities.bets;

import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "bets_owners")
public class BetsOwners {

    public BetsOwners() {
    }

    public BetsOwners(BetsUsersId betsUsersId) {
        this.betsUsersId = betsUsersId;
    }

    @EmbeddedId
    private BetsUsersId betsUsersId;

    private Date date = new Date();
}
