package are.auth.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticateRequest {
    private String userEmail;
    private String password;

    public String getUserEmail() {
        return userEmail;
    }

    // public void setUserEmail(String userEmail) {
    //     this.userEmail = userEmail;
    // }

    public String getPassword() {
        return password;
    }

    // public void setPassword(String password) {
    //     this.password = password;
    // }
}