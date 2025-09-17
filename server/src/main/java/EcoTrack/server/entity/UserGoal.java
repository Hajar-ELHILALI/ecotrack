package EcoTrack.server.entity;

import EcoTrack.server.DTO.UserGoalDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users_Goals")
public class UserGoal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    private boolean goalAchieved;

    private Double emissionTarget;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public UserGoal(UserGoalDTO userGoalDTO){
        setEmissionTarget(userGoalDTO.getEmissionTarget());
        setStartDate(userGoalDTO.getStartDate());
        setEndDate(userGoalDTO.getEndDate());
    }
}
