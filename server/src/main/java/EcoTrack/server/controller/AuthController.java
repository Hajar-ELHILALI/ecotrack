package EcoTrack.server.controller;

import EcoTrack.server.DTO.AuthRequestDTO;
import EcoTrack.server.DTO.RegisterRequestDTO;
import EcoTrack.server.DTO.UserDTO;
import EcoTrack.server.service.UserService;
import EcoTrack.server.service.implementation.TokenService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
@RestController


@RequestMapping("/api/auth")


public class AuthController {

    private final TokenService tokenService;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    public AuthController(TokenService tokenService, UserService userService,AuthenticationManager authenticationManager) {
        this.tokenService = tokenService;
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")

    public ResponseEntity<String> login(@RequestBody AuthRequestDTO request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        String token = tokenService.generateToken(authentication);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/register")

    public ResponseEntity<Void> saveUser(Authentication authentication, @RequestBody @Valid RegisterRequestDTO registerRequestDTO) {
        if (authentication!= null) {
            return ResponseEntity.badRequest().build();
        }
        return userService.register(registerRequestDTO);
    }
}
