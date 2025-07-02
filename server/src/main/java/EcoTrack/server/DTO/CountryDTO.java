package EcoTrack.server.DTO;

import EcoTrack.server.entity.Country;
import EcoTrack.server.entity.EmissionFactor;
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
public class CountryDTO {
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String code_iso;

    private Set<EmissionFactor> emissionFactors = new HashSet<>();
    public CountryDTO(Country country){
        setId(country.getId());
        setName(country.getName());
        setCode_iso(country.getCode_iso());
        setEmissionFactors(country.getEmissionFactors());
    }
}
