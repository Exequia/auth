package are.auth.entities.bets;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
@Table(name = "bets")
public class Bet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @JoinColumn(name = "profile_id")
    @ManyToOne(optional = true, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private BetProfile profile;

    @NotNull  
    @Column(columnDefinition = "TEXT")
    private String fields;

    @NotNull  
    @Column(columnDefinition = "TEXT")
    private String model;

    @NotNull  
    @Column(columnDefinition = "TEXT")
    private String result;

    @JoinColumn(name = "status_id")
    @ManyToOne(optional = false, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private BetStatus status;

    private Date startDate;

    private Date endDate;
}
