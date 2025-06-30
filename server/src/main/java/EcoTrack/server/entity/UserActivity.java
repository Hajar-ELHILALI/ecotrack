package EcoTrack.server.entity;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "UserActivities")
public class UserActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double quantity;
    private Date date;
    //enum de type de partage
    private int nbr_personnes;

    @ManyToOne
    private ActivityType activityType;

    @ManyToOne
    private HouseHold houseHold;

    //user-UserActivity

}
