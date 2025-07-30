package EcoTrack.server.entity;

import EcoTrack.server.DTO.CountryDTO;
import jakarta.persistence.*;
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
@Entity
@Table(name = "countries")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @NotNull
    private String codeIso;

    @OneToMany(mappedBy = "country")
    private Set<EmissionFactor> emissionFactors = new HashSet<>();

    // user-country
    @OneToMany(mappedBy = "country")
    private Set<User> users = new HashSet<>();

    public Country(CountryDTO countryDTO) {
        setName(countryDTO.getName());
        setCodeIso(countryDTO.getCodeIso());
        setUsers(new HashSet<>());
        setEmissionFactors(new HashSet<>());
    }

}
