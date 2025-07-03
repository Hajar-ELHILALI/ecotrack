package EcoTrack.server.DTO;

import EcoTrack.server.entity.ActivityType;
import EcoTrack.server.entity.Category;
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
    private String name;


    public CategoryDTO(Category category) {
        setId(category.getId());
        setName(category.getName());
    }
}
