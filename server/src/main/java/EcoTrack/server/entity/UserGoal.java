package EcoTrack.server.entity;

import EcoTrack.server.DTO.UserGoalDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "usersGoals")
public class UserGoal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date startDate;
    private Date endDate;
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
