package EcoTrack.server.entity;

import EcoTrack.server.DTO.CategoryDTO;
import EcoTrack.server.enums.CategoryType;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Table(name="Categories")
public class Category {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY )
    private Long id;
    @NotNull
    @Enumerated(EnumType.STRING)
    private CategoryType categoryType;

    @OneToMany(mappedBy = "category")
    private Set<ActivityType> activityTypes = new HashSet<>();

    public Category(CategoryDTO categoryDTO) {
        setCategoryType(categoryDTO.getCategoryType());
        setActivityTypes(new HashSet<>());
    }
}
