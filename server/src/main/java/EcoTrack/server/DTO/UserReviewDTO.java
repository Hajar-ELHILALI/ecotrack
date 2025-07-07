package EcoTrack.server.DTO;

import EcoTrack.server.entity.UserReview;
import com.fasterxml.jackson.annotation.JsonFormat;
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
public class UserReviewDTO {
    private Long id;
    private Integer rating;// entre 1 (★☆☆☆☆) et 5 (★★★★★)
    private String comment;//optional
    private LocalDate createdAT;
    private Long userId;

    public UserReviewDTO(UserReview userreview) {
        setId(userreview.getId());
        setRating(userreview.getRating());
        setComment(userreview.getComment());
        setCreatedAT(userreview.getCreatedAT());
        setUserId(userreview.getId());
    }
}
