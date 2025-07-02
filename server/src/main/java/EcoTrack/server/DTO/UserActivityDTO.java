package EcoTrack.server.DTO;

import EcoTrack.server.entity.UserActivity;
import EcoTrack.server.enums.SharingType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserActivityDTO {
    private Long id;

    @NotNull
    private double quantity;

    @NotNull
    private Date date;

    private int nbr_personnes;

    @NotNull
    private SharingType sharingType;

    @NotNull
    private Long activityTypeId;

    private Long householdId;

    private Long userId;

    public UserActivityDTO(UserActivity userActivity){
        setId(userActivity.getId());
        setQuantity(userActivity.getQuantity());
        setDate(userActivity.getDate());
        setNbr_personnes(userActivity.getNbr_personnes());
        setUserId(userActivity.getUser().getId());
        setHouseholdId(userActivity.getUser().getId());
        setActivityTypeId(userActivity.getActivityType().getId());
        setSharingType(userActivity.getSharingType());
    }

}
