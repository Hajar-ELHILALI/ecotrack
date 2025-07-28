package EcoTrack.server.controller;

import EcoTrack.server.DTO.UserReviewDTO;
import EcoTrack.server.service.UserReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user_reviews")
public class UserReviewController {
    private final UserReviewService userReviewService;
    public UserReviewController(UserReviewService userReviewService) {
        this.userReviewService = userReviewService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserReviewDTO> getUserReview(@PathVariable Long id) {
        return ResponseEntity.ok(userReviewService.findDTOById(id));
    }

    @GetMapping("/")
    public ResponseEntity<List<UserReviewDTO>> getUserReviews() {
        return ResponseEntity.ok(userReviewService.findAllDTO());
    }

    @PostMapping("/")
    public ResponseEntity<UserReviewDTO> createUserReview(@RequestBody UserReviewDTO userReviewDTO) {
        return ResponseEntity.ok(userReviewService.createDTO(userReviewDTO));
    }

    @PutMapping("/")
    public ResponseEntity<UserReviewDTO> updateUserReview(@RequestBody UserReviewDTO userReviewDTO) {
        return ResponseEntity.ok(userReviewService.updateDTO(userReviewDTO));
    }

    @DeleteMapping("/{id}")
    public void deleteUserReview(@PathVariable Long id) {
        userReviewService.deleteDTOById(id);
    }
}
