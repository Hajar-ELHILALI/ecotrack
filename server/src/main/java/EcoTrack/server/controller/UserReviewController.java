package EcoTrack.server.controller;

import EcoTrack.server.DTO.UserGoalDTO;
import EcoTrack.server.DTO.UserReviewDTO;
import EcoTrack.server.entity.UserGoal;
import EcoTrack.server.entity.UserReview;
import EcoTrack.server.service.UserReviewService;
import EcoTrack.server.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/user_reviews")
public class UserReviewController {
    private final UserReviewService userReviewService;
    private final UserService userService;
    public UserReviewController(UserReviewService userReviewService, UserService userService) {
        this.userReviewService = userReviewService;
        this.userService = userService;
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

    @GetMapping("/byUser/")
    public ResponseEntity<List<UserReviewDTO>> findUserReviews(Principal principal) {
        List<UserReview> userReviews = userReviewService.findUserReviews(userService.getFromPrincipal(principal).getId());
        List<UserReviewDTO> userReviewDTOS = userReviews.stream()
                .map(UserReviewDTO::new)
                .toList();
        return ResponseEntity.ok(userReviewDTOS);
    }
}
