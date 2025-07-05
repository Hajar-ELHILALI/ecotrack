package EcoTrack.server.entity;

import EcoTrack.server.DTO.HouseholdDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
@Table(name="households")
public class Household {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private int number;
    @NotNull
    private String street;
    @NotNull
    private String city;

    @OneToMany(mappedBy = "household")
    private Set<UserActivity> userActivities = new HashSet<>();

    //user-Household
    @OneToMany(mappedBy = "household")
    private Set<User> users = new HashSet<>();


    public Household(HouseholdDTO householdDTO){
        setNumber(householdDTO.getNumber());
        setStreet(householdDTO.getStreet());
        setCity(householdDTO.getCity());
        setUsers(new HashSet<>());
        setUserActivities(new HashSet<>());
    }
}
