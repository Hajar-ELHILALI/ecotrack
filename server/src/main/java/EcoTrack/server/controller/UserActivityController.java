package EcoTrack.server.controller;

import EcoTrack.server.DTO.UserActivityDTO;
import EcoTrack.server.service.UserActivityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user_activities")
public class UserActivityController {
    private final UserActivityService userActivityService;
    public UserActivityController(UserActivityService userActivityService) {
        this.userActivityService = userActivityService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserActivityDTO> getUserActivity(@PathVariable Long id) {
        return ResponseEntity.ok(userActivityService.findDTOById(id));
    }

    @GetMapping("/")
    public ResponseEntity<List<UserActivityDTO>> getAllUserActivity() {
        return ResponseEntity.ok(userActivityService.findAllDTO());
    }

    @PostMapping("/")
    public ResponseEntity<UserActivityDTO> createUserActivity(@RequestBody UserActivityDTO userActivityDTO) {
        return ResponseEntity.ok(userActivityService.createDTO(userActivityDTO));
    }

    @PutMapping("/")
    public ResponseEntity<UserActivityDTO> updateUserActivity(@RequestBody UserActivityDTO userActivityDTO) {
        return ResponseEntity.ok(userActivityService.updateDTO(userActivityDTO));
    }

    @DeleteMapping("/{id}")
    public void deleteUserActivity(@PathVariable Long id) {
        userActivityService.deleteDTOById(id);
    }
}
