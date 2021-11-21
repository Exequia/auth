package are.auth.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @NotBlank(message = "emailRequired.")
    @Email(message = "email should be a valid email")
    @Column(unique=true)
    // @Column(name = "unique_key_field", nullable = false, unique = true)
    private String email;
    
    @NotNull(message = "aliasNotNull")
    @NotBlank(message = "aliasNotBlank.")
    private String password;
    
    @NotNull(message = "aliasNotNull")
    @NotBlank(message = "aliasNotBlank.")
    private String alias;

    @JoinColumn(name = "role_id")
    @ManyToOne(optional = false, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Role role;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "people_id", updatable = false, nullable = false)
    private People people;

    @JoinColumn(name = "user_status")
    @ManyToOne(optional = false, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private UserStatus status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        if (!sameAsFormer(this.role, role)) {
            this.role = role;
        }
    }

    public User orElseThrow(Object object) {
        return null;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public People getPeople() {
        return people;
    }

    public void setPeople(People people) {
        this.people = people;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus userStatus) {
        if (!sameAsFormer(this.status, userStatus)) {
            this.status = userStatus;
        }
    }

    // prevent endless loop
    private <T> boolean sameAsFormer(T oldItem, T newItem) {
        return oldItem == null ? newItem == null : oldItem.equals(newItem);
    }
}
