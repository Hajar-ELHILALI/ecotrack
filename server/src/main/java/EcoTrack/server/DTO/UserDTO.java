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
    private Long roleId;
    private Long householdId;
    private Long badgeId;
    private Long countryId;
    private ArrayList<UserGoal> goals = new ArrayList<>();
    private ArrayList<UserReview> reviews = new ArrayList<>();
    private ArrayList<Notification> notifications = new ArrayList<>();
    private ArrayList<Score> scores = new ArrayList<>();

    public UserDTO(User user) {
        setId(user.getId());
        setEmail(user.getEmail());
        setUser_name(user.getUser_name());

        setCountryId(user.getCountry().getId());
        setRoleId(user.getRole().getId());
        setHouseholdId(user.getHousehold().getId());
        setBadgeId(user.getBadge().getId());
        setReviews(user.getReviews());
        setNotifications(user.getNotifications());
        setScores(user.getScores());
        setGoals(user.getGoals());
    }
}