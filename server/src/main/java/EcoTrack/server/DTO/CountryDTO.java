package EcoTrack.server.DTO;

import EcoTrack.server.entity.Country;
import EcoTrack.server.entity.EmissionFactor;
import EcoTrack.server.entity.User;
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
    private String codeIso;



    public CountryDTO(Country country){
        setId(country.getId());
        setName(country.getName());
        setCodeIso(country.getCodeIso());

    }
}
