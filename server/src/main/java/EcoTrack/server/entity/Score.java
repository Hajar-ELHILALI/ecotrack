package EcoTrack.server.entity;


import EcoTrack.server.enums.QualitativeScore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "scores")
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private QualitativeScore qualitativeScore;
    private double totalco2;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
