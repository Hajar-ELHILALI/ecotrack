package EcoTrack.server.entity;

import EcoTrack.server.DTO.UserReviewDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "usersReviews")
public class UserReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(1)
    @Max(5)
    private Integer rating;// entre 1 (★☆☆☆☆) et 5 (★★★★★)

    private String comment;//optional

    @JsonFormat(pattern = "yyyy-MM-dd")//pour l'API (frontend)
    @DateTimeFormat(pattern = "yyyy-MM-dd")//pour la conversion automatique ,si on reçoit des dates via formulaire (GET/POST)
    private LocalDate createdAT;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public UserReview(UserReviewDTO userReviewDTO) {
        setComment(userReviewDTO.getComment());
        setRating(userReviewDTO.getRating());
        setCreatedAT(userReviewDTO.getCreatedAT());
    }
}
