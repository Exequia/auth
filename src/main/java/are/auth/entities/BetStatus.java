package are.auth.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Value;

import lombok.Data;

@Data
@Entity
@Table(name = "bets_status")
public class BetStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "varchar(255) default 1")
    @Value("${bet.status.id}")
    private Long id;
    
    private String name;
}
