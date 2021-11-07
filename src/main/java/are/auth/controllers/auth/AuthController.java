package are.auth.controllers.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import are.auth.dtos.UserDTOResponse;
import are.auth.models.AuthenticateRequest;
import are.auth.models.JwtAuthenticationResponse;
import are.auth.utils.users.UserUtils;

@RestController
@RequestMapping("/authenticate")
public class AuthController implements IAuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private UserUtils userUtils;

    @Override
    @PostMapping
    public ResponseEntity<JwtAuthenticationResponse> authenticateUser(
            @RequestBody AuthenticateRequest authenticateRequest) {
        String token = this.userUtils.getToken(authenticateRequest);
        UserDTOResponse user = this.userUtils.getUserByEmail(authenticateRequest.getEmail());
        log.info("Token Created: " + token.toString());
        log.info("User: " + user.toString());
        return ResponseEntity.ok(new JwtAuthenticationResponse(token, user));
    }

}