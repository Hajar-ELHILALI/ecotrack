package EcoTrack.server.DTO;

import EcoTrack.server.entity.Country;
import EcoTrack.server.entity.Household;
import EcoTrack.server.entity.User;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequestDTO {
    @Email
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String userName;

    @NotNull
    private Long householdId;

    @NotNull
    private Long countryId;

    public UserDTO user(){
        UserDTO user = new UserDTO();
        user.setEmail(email);
        user.setUserName(userName);
        user.setCountryId(countryId);
        user.setHouseholdId(householdId);
        return user;
    }
}
