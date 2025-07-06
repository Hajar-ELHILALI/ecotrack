package EcoTrack.server.DTO;

import EcoTrack.server.entity.Badge;
import EcoTrack.server.enums.BadgeLabel;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BadgeDTO {

    private Long id;
    private BadgeLabel label;
    private String description;

    public BadgeDTO(Badge badge){
        setId(badge.getId());
        setLabel(badge.getLabel());
        setLabel(badge.getLabel());
    }
}
