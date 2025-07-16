package EcoTrack.server.DTO;

import EcoTrack.server.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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

    public User user(){
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setUserName(userName);
        return user;
    }
}
