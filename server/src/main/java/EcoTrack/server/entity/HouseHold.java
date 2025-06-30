package EcoTrack.server.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="house_holds")
public class HouseHold {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int number;
    private String street;
    private String city;

    @OneToMany(mappedBy = "houseHold")
    private Set<UserActivity> userActivities = new HashSet<>();

    //user-HouseHold
    @OneToMany(mappedBy = "houseHold")
    private Set<User> users = new HashSet<>();

}
