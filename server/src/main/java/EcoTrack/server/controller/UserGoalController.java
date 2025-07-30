package EcoTrack.server.controller;

import EcoTrack.server.DTO.UserGoalDTO;
import EcoTrack.server.service.UserGoalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user_goals")
public class UserGoalController {
    private final UserGoalService userGoalService;
    public UserGoalController(UserGoalService userGoalService) {
        this.userGoalService = userGoalService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserGoalDTO> getUserGoal(@PathVariable Long id) {
        return ResponseEntity.ok(userGoalService.findDTOById(id));
    }

    @GetMapping("/")
    public ResponseEntity<List<UserGoalDTO>> getUserGoals() {
        return ResponseEntity.ok(userGoalService.findAllDTO());
    }

    @PostMapping("/")
    public ResponseEntity<UserGoalDTO> createUserGoal(@RequestBody UserGoalDTO userGoalDTO) {
        return ResponseEntity.ok(userGoalService.createDTO(userGoalDTO));
    }

    @PutMapping("/")
    public ResponseEntity<UserGoalDTO> updateUserGoal(@RequestBody UserGoalDTO userGoalDTO) {
        return ResponseEntity.ok(userGoalService.updateDTO(userGoalDTO));
    }

    @DeleteMapping("/{id}")
    public void deleteUserGoal(@PathVariable Long id) {
        userGoalService.deleteDTOById(id);
    }
}
