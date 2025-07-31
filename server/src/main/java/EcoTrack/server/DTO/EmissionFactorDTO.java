package EcoTrack.server.DTO;

import EcoTrack.server.entity.EmissionFactor;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmissionFactorDTO {
    private Long id;
    @NotNull
    private double factor;
    private String source;
    @NotNull
    private Long countryId;
    @NotNull
    private Long activityTypeId;

    public EmissionFactorDTO(EmissionFactor emissionFactor){
        setId(emissionFactor.getId());
        setFactor(emissionFactor.getFactor());
        setSource(emissionFactor.getSource());
        setCountryId(emissionFactor.getId());
        setActivityTypeId(emissionFactor.getId());
    }
}
