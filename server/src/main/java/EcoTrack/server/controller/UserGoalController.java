package EcoTrack.server.controller;

import EcoTrack.server.DTO.UserGoalDTO;
import EcoTrack.server.entity.UserGoal;
import EcoTrack.server.service.UserGoalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/user_goals")
public class UserGoalController {
    private final UserGoalService userGoalService;
    public UserGoalController(UserGoalService userGoalService) {
        this.userGoalService = userGoalService;
    }


    @PostMapping
    public ResponseEntity<UserGoalDTO> createGoal(@RequestBody UserGoalDTO dto, Principal principal) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userGoalService.createGoal(dto, principal.getName()));

    }

}
