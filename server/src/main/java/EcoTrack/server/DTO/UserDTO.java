package EcoTrack.server.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import EcoTrack.server.entity.*;
import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    //@NotNull : id peut etre null en cas de creation user envoi les donnees server genere id
    private Long id;

    @NotNull
    @Email
    private String email;


    private String user_name;
    @NotNull
    private Long roleId;
    @NotNull
    private Long householdId;
    private Long badgeId;
    @NotNull
    private Long countryId;


    public UserDTO(User user) {
        setId(user.getId());
        setEmail(user.getEmail());
        setUser_name(user.getUser_name());

        setCountryId(user.getCountry().getId());
        setRoleId(user.getRole().getId());
        setHouseholdId(user.getHousehold().getId());
        setBadgeId(user.getBadge().getId());

    }
}