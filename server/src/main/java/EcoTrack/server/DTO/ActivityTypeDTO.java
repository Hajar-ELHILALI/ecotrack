package EcoTrack.server.DTO;

import EcoTrack.server.entity.ActivityType;
import EcoTrack.server.entity.Category;
import EcoTrack.server.entity.UserActivity;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ActivityTypeDTO {
    private Long id;

    @NotNull
    private String name;

    private String unit;

    @NotNull
    private Long CategoryId;



    public ActivityTypeDTO(ActivityType activityType){
        setId(activityType.getId());
        setName(activityType.getName());
        setUnit(activityType.getUnit());
        setCategoryId(activityType.getCategory().getId());

    }
}
