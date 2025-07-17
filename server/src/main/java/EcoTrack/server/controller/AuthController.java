package EcoTrack.server.controller;

import EcoTrack.server.DTO.RegisterRequestDTO;
import EcoTrack.server.DTO.UserDTO;
import EcoTrack.server.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/account")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDTO> signup(
            Authentication authentication,
            @RequestBody @Valid RegisterRequestDTO registerRequest
    ) {
        if (authentication != null) {
            return ResponseEntity.badRequest().body(null);
        }
        return userService.register(registerRequest);
    }
}
