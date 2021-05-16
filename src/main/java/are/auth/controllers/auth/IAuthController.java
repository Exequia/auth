package are.auth.controllers.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import are.auth.models.AuthenticateRequest;
import are.auth.models.JwtAuthenticationResponse;

public interface IAuthController {
    public ResponseEntity<JwtAuthenticationResponse> authenticateUser(
            @RequestBody AuthenticateRequest authenticateRequest);
}
