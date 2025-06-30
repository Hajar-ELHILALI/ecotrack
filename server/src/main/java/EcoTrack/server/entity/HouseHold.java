package EcoTrack.server.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="house_holds")
public class HouseHold {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int numero;
    private String street;
    private String city;

    @OneToMany(mappedBy = "houseHold")
    private Set<UserActivity> userActivities = new HashSet<>();

    //user-HouseHold
}
