package EcoTrack.server.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="Categories")
public class Category {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY )
    private Long id;
    @NotNull
    private String name;

    @OneToMany(mappedBy = "category")
    private Set<ActivityType> activityTypes = new HashSet<>();
}
