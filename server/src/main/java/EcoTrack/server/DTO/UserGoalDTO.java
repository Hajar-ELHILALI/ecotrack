package EcoTrack.server.DTO;

import EcoTrack.server.entity.UserGoal;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserGoalDTO {
    private Long id;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;

    private boolean goalAchieved;

    @NotNull
    private Double emissionTarget;


    public UserGoalDTO(UserGoal userGoal) {
        setId(userGoal.getId());
        setStartDate(userGoal.getStartDate());
        setEndDate(userGoal.getEndDate());
        setEmissionTarget(userGoal.getEmissionTarget());
        setGoalAchieved(userGoal.isGoalAchieved());
    }
}
