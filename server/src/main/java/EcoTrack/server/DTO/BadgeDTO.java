package EcoTrack.server.DTO;

import EcoTrack.server.entity.Badge;
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
    @NotNull
    private String label;
    public BadgeDTO(Badge badge){
        setId(badge.getId());
        setLabel(badge.getLabel());
    }
}
