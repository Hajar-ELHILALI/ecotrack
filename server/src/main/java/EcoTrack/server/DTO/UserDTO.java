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
    @NotNull
    private Long id;

    @NotNull
    @Email
    private String email;

    @NotNull
    private String password_hash;

    private String user_name;
    private Role role;
    private Household household;
    private Badge badge;
    private Country country;
    private ArrayList<UserGoal> goals = new ArrayList<>();
    private ArrayList<UserReview> reviews = new ArrayList<>();
    private ArrayList<Notification> notifications = new ArrayList<>();
    private ArrayList<Score> scores = new ArrayList<>();

    public UserDTO(User user) {
        setId(user.getId());
        setEmail(user.getEmail());
        setUser_name(user.getUser_name());
        setPassword_hash(user.getPassword_hash());
        setCountry(user.getCountry());
        setRole(user.getRole());
        setHousehold(user.getHousehold());
        setBadge(user.getBadge());
        setReviews(user.getReviews());
        setNotifications(user.getNotifications());
        setScores(user.getScores());
        setGoals(user.getGoals());
    }
}