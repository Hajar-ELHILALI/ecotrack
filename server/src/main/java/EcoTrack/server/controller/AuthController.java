package EcoTrack.server.controller;

import EcoTrack.server.DTO.RegisterRequestDTO;
import EcoTrack.server.DTO.UserDTO;
import EcoTrack.server.service.UserService;
import EcoTrack.server.service.implementation.TokenService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final TokenService tokenService;
    private final UserService userService;
    public AuthController(TokenService tokenService, UserService userService) {
        this.tokenService = tokenService;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> token(Authentication authentication) {

        return ResponseEntity.ok(tokenService.generateToken(authentication));
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> saveUser(Authentication authentication, @RequestBody @Valid RegisterRequestDTO registerRequestDTO) {
        if (authentication!= null) {
            return ResponseEntity.badRequest().build();
        }
        return userService.register(registerRequestDTO);
    }


}
