package are.auth.models;

import are.auth.dtos.UserDTOResponse;
import lombok.Data;

@Data
public class JwtAuthenticationResponse {

    private String accessToken;
    private final String tokenType = "Bearer";
    private UserDTOResponse user;

    public JwtAuthenticationResponse(String accessToken, UserDTOResponse user) {
        this.accessToken = accessToken;
        this.user = user;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

}