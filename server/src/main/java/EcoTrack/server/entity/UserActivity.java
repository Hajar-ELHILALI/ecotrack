package EcoTrack.server.entity;

import EcoTrack.server.enums.SharingType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user_activities")
public class UserActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double quantity;
    private Date date;
    //enum de type de partage
    @Enumerated(EnumType.STRING)
    @Column(name = "sharing_type", length = 20)
    private SharingType sharingType;
    private int nbr_personnes;

    @ManyToOne
    @JoinColumn(name = "activity_type_id", nullable = false)
    private ActivityType activityType;

    @ManyToOne
    @JoinColumn(name = "house_hold_id")
    private HouseHold houseHold;

    //user-UserActivity
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
