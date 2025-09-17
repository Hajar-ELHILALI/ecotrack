package EcoTrack.server.DTO;

import EcoTrack.server.entity.UserActivity;
import EcoTrack.server.enums.SharingType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserActivityDTO {
    private Long id;

    @NotNull
    private double quantity;

    private LocalDate date;

    private int nbrPersonnes;

    @NotNull
    private SharingType sharingType;

    @NotNull
    private Long activityTypeId;

    private Long userId;

    public UserActivityDTO(UserActivity userActivity){
        setId(userActivity.getId());
        setQuantity(userActivity.getQuantity());
        setDate(userActivity.getDate());
        setNbrPersonnes(userActivity.getNbrPersonnes());
        setActivityTypeId(userActivity.getActivityType().getId());
        setSharingType(userActivity.getSharingType());
    }

}
