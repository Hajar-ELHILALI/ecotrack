package EcoTrack.server.controller;

import EcoTrack.server.DTO.BadgeDTO;

import EcoTrack.server.service.BadgeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/badges")
public class BadgeController {
    private final BadgeService badgeService;
    public BadgeController(BadgeService badgeService) {
        this.badgeService = badgeService;
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
}
