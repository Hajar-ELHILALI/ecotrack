package EcoTrack.server.entity;

import EcoTrack.server.DTO.ActivityTypeDTO;
import EcoTrack.server.enums.UnitType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "Activity_types")
public class ActivityType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @Enumerated(EnumType.STRING)
    private UnitType unit;

    @OneToMany(mappedBy = "activityType", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<EmissionFactor> EmissionFactors = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    @JsonIgnore
    private Category category;

    @OneToMany(mappedBy = "activityType", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<UserActivity> userActivities = new HashSet<>();

    public ActivityType(ActivityTypeDTO activityTypeDTO) {
        setName(activityTypeDTO.getName());
        setUnit(activityTypeDTO.getUnit());
        setUserActivities(new HashSet<>());
        setEmissionFactors(new HashSet<>());
    }
}
