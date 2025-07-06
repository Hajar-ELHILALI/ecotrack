package EcoTrack.server.DTO;

import EcoTrack.server.entity.ActivityType;
import EcoTrack.server.entity.Category;
import EcoTrack.server.enums.CategoryType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private CategoryType categoryType;


    public CategoryDTO(Category category) {
        setId(category.getId());
        setCategoryType(category.getCategoryType());
    }
}
