package EcoTrack.server.DTO;

import EcoTrack.server.enums.CategoryType;
import EcoTrack.server.enums.SharingType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ActivityDTO {
    private CategoryType categoryType;
    private String activityTypeName;
    private double quantity;
    private int nbrPersonnes;
    private SharingType sharingType;
}