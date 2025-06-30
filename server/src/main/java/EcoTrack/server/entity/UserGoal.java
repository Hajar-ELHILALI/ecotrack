package EcoTrack.server.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "usersGoals")
public class UserGoal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date date_debut;
    private Date date_fin;
    private boolean goalAchieved;
    private Double emissionTarget;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
