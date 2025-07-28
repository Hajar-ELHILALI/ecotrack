package EcoTrack.server.controller;

import EcoTrack.server.DTO.ScoreDTO;
import EcoTrack.server.service.ScoreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/scores")
public class ScoreController {
    private final ScoreService scoreService;
    public ScoreController(ScoreService scoreService) {
        this.scoreService = scoreService;
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
}
