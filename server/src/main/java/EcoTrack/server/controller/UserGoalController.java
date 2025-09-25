package EcoTrack.server.controller;

import EcoTrack.server.DTO.UserGoalDTO;
import EcoTrack.server.entity.UserGoal;
import EcoTrack.server.service.UserGoalService;
import EcoTrack.server.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/user_goals")
public class UserGoalController {
    private final UserGoalService userGoalService;
    private final UserService userService;
    public UserGoalController(UserGoalService userGoalService, UserService userService) {

        this.userGoalService = userGoalService;
        this.userService = userService;
    }


    @PostMapping
    public ResponseEntity<UserGoalDTO> createGoal(@RequestBody UserGoalDTO dto, Principal principal) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userGoalService.createGoal(dto, principal.getName()));

    }
    @GetMapping
    public ResponseEntity<List<UserGoalDTO>> getUserGoals(Principal principal) {
        List<UserGoal> userGoals = userGoalService.findUserGoals(userService.getFromPrincipal(principal));
        List<UserGoalDTO> userGoalDTOs = userGoals.stream()
                .map(UserGoalDTO::new)
                .toList();
        return ResponseEntity.ok(userGoalDTOs);
    }

    @DeleteMapping("/{id}")
    public void deleteUserGoal(@PathVariable Long id) {
        userGoalService.deleteDTOById(id);
    }

    @PutMapping()
    public ResponseEntity<UserGoalDTO> updateGoal(@RequestBody UserGoalDTO userGoalDTO, Principal principal) {
        UserGoalDTO newUserGoalDTO = userGoalService.updateDTO(userGoalDTO, userService.getFromPrincipal(principal).getEmail());
        return ResponseEntity.ok(newUserGoalDTO);
    }
}
