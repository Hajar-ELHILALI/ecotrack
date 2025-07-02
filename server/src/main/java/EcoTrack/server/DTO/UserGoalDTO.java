package EcoTrack.server.DTO;

import EcoTrack.server.entity.UserGoal;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserGoalDTO {
    private Long id;

    @NotNull
    private Date startDate;

    @NotNull
    private Date endDate;

    private boolean goalAchieved;

    @NotNull
    private Double emissionTarget;

    @NotNull
    private Long userId;

    public UserGoalDTO(UserGoal userGoal) {
        setId(userGoal.getId());
        setUserId(userGoal.getUser().getId());
        setStartDate(userGoal.getStartDate());
        setEndDate(userGoal.getEndDate());
        setEmissionTarget(userGoal.getEmissionTarget());
        setGoalAchieved(userGoal.isGoalAchieved());
    }
}
