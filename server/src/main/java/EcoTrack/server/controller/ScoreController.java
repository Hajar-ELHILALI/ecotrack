package EcoTrack.server.controller;

import EcoTrack.server.DTO.ScoreDTO;
import EcoTrack.server.DTO.StatisticsResponseDTO;
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

    /**
     * Retourne les statistiques des émissions de CO2 pour l'utilisateur connecté.
     *
     * Ce endpoint renvoie trois séries de données correspondant aux catégories :
     * - Transport
     * - Électricité
     * - Nutrition
     *
     * Chaque série contient des objets avec :
     * - date : la période correspondant au point (jour, mois ou année)
     * - value : la somme des émissions de CO2 pour cette période
     *
     * L'agrégation peut être faite par :
     * - "daily"  : par jour
     * - "monthly": par mois
     * - "yearly" : par année
     *
     * Exemple d'appel :
     * GET /api/scores/statistics?period=daily
     *
     * Exemple de réponse JSON :
     * {
     *   "transport": [{"date":"2025-09-19", "value": 5.2}],
     *   "electricity": [{"date":"2025-09-19", "value": 2.5}],
     *   "nutrition": [{"date":"2025-09-19", "value": 1.0}]
     * }
     *
     * @param principal l'utilisateur connecté (Spring injecte automatiquement ses informations)
     * @param period période d'agrégation souhaitée : "daily", "monthly" ou "yearly" (valeur par défaut : "daily")
     * @return ResponseEntity contenant un objet StatisticsResponseDTO avec les trois séries de statistiques
     * @throws RuntimeException si l'utilisateur n'est pas trouvé
     */
    //endpoint avec paramètre
    //GET /api/scores/statistics?period=daily
    //GET /api/scores/statistics?period=monthly
    //GET /api/scores/statistics?period=yearly
    @GetMapping("/statistics")
    public ResponseEntity<StatisticsResponseDTO> getMyStatistics(
            Principal principal,
            @RequestParam(defaultValue = "daily") String period // "day", "month", "year"
    ) {
        String email = principal.getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return ResponseEntity.ok(scoreService.getUserStatistics(user.getId(), period));
    }

}
