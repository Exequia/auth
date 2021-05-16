package are.auth.controllers.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import are.auth.models.AuthenticateRequest;
import are.auth.models.JwtAuthenticationResponse;
import are.auth.models.UserPrincipal;
import are.auth.services.JWTTokenProvider;

@RestController
@RequestMapping("/authenticate")
public class AuthController implements IAuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTTokenProvider jwtTokenProvider;

    @Override
    @PostMapping
    public ResponseEntity<JwtAuthenticationResponse> authenticateUser(
            @RequestBody AuthenticateRequest authenticateRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authenticateRequest.getUserEmail(), authenticateRequest.getPassword()));
        String token = jwtTokenProvider.generateToken((UserPrincipal) authentication.getPrincipal());
        // log.info("Token Created {}",token);
        return ResponseEntity.ok(new JwtAuthenticationResponse(token));
    }

}