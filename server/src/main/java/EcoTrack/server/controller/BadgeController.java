package EcoTrack.server.controller;

import EcoTrack.server.DTO.BadgeDTO;

import EcoTrack.server.entity.User;
import EcoTrack.server.repository.UserRepository;
import EcoTrack.server.service.BadgeService;
import EcoTrack.server.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/badges")
public class BadgeController {
    private final BadgeService badgeService;
    private final UserRepository userRepository;
    public BadgeController(BadgeService badgeService,UserRepository userRepository) {
        this.badgeService = badgeService;
        this.userRepository = userRepository;
    }

    @PostMapping("/")
    public ResponseEntity<BadgeDTO> createBadge(@RequestBody BadgeDTO badgeDTO) {
        return ResponseEntity.ok(badgeService.createDTO(badgeDTO));
    }

    @GetMapping("/")
    public ResponseEntity<List<BadgeDTO>> getAllBadges() {
        return ResponseEntity.ok(badgeService.findAllDTO());
    }
    @GetMapping("/{id}")
    public ResponseEntity<BadgeDTO> getBadgeById(@PathVariable Long id) {
        return ResponseEntity.ok(badgeService.findDTOById(id));
    }

    @PutMapping("/")
    public ResponseEntity<BadgeDTO> updateBadge(@RequestBody BadgeDTO badgeDTO) {
        return ResponseEntity.ok(badgeService.updateDTO(badgeDTO));
    }

    @DeleteMapping("/{id}")
    public void deleteBadge(@PathVariable Long id) {
        badgeService.deleteDTOById(id);
    }

    @GetMapping("/me")
    public ResponseEntity<String> getMyBadgeLabel(Principal principal) {
        String email = principal.getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (user.getBadge() == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(user.getBadge().getLabel().name());
    }
}
