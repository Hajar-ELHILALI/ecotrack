package EcoTrack.server.DTO;

import EcoTrack.server.entity.Score;
import EcoTrack.server.enums.QualitativeScore;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScoreDTO {
    private Long id;
    private QualitativeScore qualitativeScore;
    private double totalco2;
    private Long userActivity;
    private Long userId;

    public ScoreDTO(Score score) {
        setId(score.getId());
        setQualitativeScore(score.getQualitativeScore());
        setUserId(score.getUser().getId());
        setTotalco2(score.getTotalco2());
    }
}
