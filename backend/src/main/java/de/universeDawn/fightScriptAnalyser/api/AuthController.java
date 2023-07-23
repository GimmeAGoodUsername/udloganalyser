package de.universeDawn.fightscriptanalyser.api;

import de.universeDawn.fightscriptanalyser.api.auth.AuthResponse;
import de.universeDawn.fightscriptanalyser.api.auth.LoginRequest;
import de.universeDawn.fightscriptanalyser.services.SrUserService;
import de.universeDawn.fightscriptanalyser.user.SrUser;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private SrUserService srUserService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest loginRequest) {

        Optional<SrUser> srUser = srUserService.validUser(loginRequest);
        if (srUser.isPresent()) {
            SrUser user = srUser.get();
            String s = Base64.getEncoder().encodeToString((user.getName() + ":" + user.getLoginInformation().getPassword()).getBytes());
            return ResponseEntity.ok(new AuthResponse(user.getId(), user.getName(), s));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }


    public ResponseEntity<Void> logout(@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok().build();
    }


}
