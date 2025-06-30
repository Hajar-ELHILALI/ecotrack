package EcoTrack.server.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ActivityTypes")
public class ActivityType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String unit;

    @OneToMany(mappedBy = "activityType")
    private Set<EmissionFactor> EmissionFactors = new HashSet<>();

    @ManyToOne
    private Category category;

    @OneToMany(mappedBy = "activityType")
    private Set<UserActivity> userActivities = new HashSet<>();
}
