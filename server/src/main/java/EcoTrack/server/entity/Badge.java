package EcoTrack.server.entity;


import EcoTrack.server.DTO.BadgeDTO;
import EcoTrack.server.enums.BadgeLabel;
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
@Table(name = "badges")
public class Badge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private BadgeLabel label;

    private String description;

    @OneToMany(mappedBy = "badge")
    private Set<User> users = new HashSet<>();

    public Badge(BadgeDTO badgeDTO) {
        setLabel(badgeDTO.getLabel());
        setDescription(badgeDTO.getDescription());
        setUsers(new HashSet<>());
    }
}
