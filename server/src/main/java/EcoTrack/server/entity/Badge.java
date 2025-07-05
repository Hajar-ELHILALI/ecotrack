package EcoTrack.server.entity;

import EcoTrack.server.DTO.BadgeDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

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

    @NotNull
    private String label;

    @OneToMany(mappedBy = "badge")
    private ArrayList<User> users = new ArrayList<>();

    public Badge(BadgeDTO badgeDTO) {
        setLabel(badgeDTO.getLabel());
        setUsers(new ArrayList<>());
    }
}
