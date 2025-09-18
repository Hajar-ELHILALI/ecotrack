package EcoTrack.server.controller;

import EcoTrack.server.DTO.UserDTO;
import EcoTrack.server.entity.User;
import EcoTrack.server.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @DeleteMapping("/")
    public ResponseEntity<Void> deleteUser(Principal principal) {
        if (principal != null) {
            userService.deleteByEmail(principal.getName());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        }
    }
    @GetMapping("/")
    public ResponseEntity<UserDTO> getUser(Principal principal){
        User user = userService.getFromPrincipal(principal);
        if (user == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(new UserDTO(user));
        }
    }
}
