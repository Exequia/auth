package are.auth.entities;

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

    public BetsOwners(BetsOwnersId betsOwnersId) {
        this.betsOwnersId = betsOwnersId;
    }

    @EmbeddedId
    private BetsOwnersId betsOwnersId;

    private Date date = new Date();
}
