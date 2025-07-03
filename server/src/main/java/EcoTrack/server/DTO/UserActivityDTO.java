package EcoTrack.server.DTO;

import EcoTrack.server.entity.UserActivity;
import EcoTrack.server.enums.SharingType;
import jakarta.validation.constraints.Min;
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
public class UserActivityDTO {
    private Long id;

    @NotNull
    private double quantity;

    @NotNull
    private Date date;

    @Min(1)
    private int nbrPersonnes;

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
        setNbrPersonnes(userActivity.getNbrPersonnes());
        if(userActivity.getSharingType() == SharingType.HOUSEHOLD){

            setHouseholdId(userActivity.getHousehold().getId());
            setUserId(null);
        }else{

            setUserId(userActivity.getUser().getId());
            setHouseholdId(null);
        }

        setActivityTypeId(userActivity.getActivityType().getId());
        setSharingType(userActivity.getSharingType());
    }

}
