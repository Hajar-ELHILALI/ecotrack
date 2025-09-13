package EcoTrack.server.controller;

import EcoTrack.server.DTO.ScoreDTO;
import EcoTrack.server.DTO.statisticDTO;
import EcoTrack.server.entity.User;
import EcoTrack.server.repository.UserRepository;
import EcoTrack.server.service.ScoreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/scores")
public class ScoreController {
    private final ScoreService scoreService;
    private final UserRepository userRepository;

    public ScoreController(ScoreService scoreService,UserRepository userRepository) {
        this.scoreService = scoreService;
        this.userRepository = userRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScoreDTO> getScore(@PathVariable Long id) {
        return ResponseEntity.ok(scoreService.findDTOById(id));
    }

    @GetMapping("/")
    public ResponseEntity<List<ScoreDTO>> getAllScores() {
        return ResponseEntity.ok(scoreService.findAllDTO());
    }

    @PostMapping("/")
    public ResponseEntity<ScoreDTO> createScore(@RequestBody ScoreDTO scoreDTO) {
        return ResponseEntity.ok(scoreService.createDTO(scoreDTO));
    }

    @PutMapping("/")
    public ResponseEntity<ScoreDTO> updateScore(@RequestBody ScoreDTO scoreDTO) {
        return ResponseEntity.ok(scoreService.updateDTO(scoreDTO));

    }

    @DeleteMapping("/{id}")
   public void deleteScore(@PathVariable Long id) {
        scoreService.deleteDTOById(id);
    }

    @GetMapping("/me/history")
    public ResponseEntity<List<statisticDTO>> getMyScores(Principal principal) {
        String email = principal.getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return ResponseEntity.ok(scoreService.getUserScores(user.getId()));
    }

}
