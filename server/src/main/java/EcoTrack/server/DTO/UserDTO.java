package EcoTrack.server.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import EcoTrack.server.entity.*;

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

    private String userName;
    @NotNull
    private Long roleId;
    private Long badgeId;
    @NotNull
    private Long countryId;


    public UserDTO(User user) {
        setId(user.getId());
        setEmail(user.getEmail());
        setUserName(user.getUserName());
        setCountryId(user.getCountry().getId());
        setRoleId(user.getRole().getId());
        setBadgeId(user.getBadge().getId());

    }
}