package EcoTrack.server.DTO;

import EcoTrack.server.entity.Household;
import EcoTrack.server.entity.User;
import EcoTrack.server.entity.UserActivity;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HouseholdDTO {
    private Long id;
    @NotNull
    private int number;
    @NotNull
    private String Street;
    @NotNull
    private String city;




    public HouseholdDTO(Household household) {
        setId(household.getId());
        setNumber(household.getNumber());
        setStreet(household.getStreet());
        setCity(household.getCity());

    }
}
