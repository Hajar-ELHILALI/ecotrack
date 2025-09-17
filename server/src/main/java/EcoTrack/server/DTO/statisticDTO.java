package EcoTrack.server.DTO;

import EcoTrack.server.entity.Score;
import EcoTrack.server.enums.QualitativeScore;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class statisticDTO {
    private QualitativeScore qualitativeScore;
    private double totalco2;
    private LocalDate activityDate;

    public statisticDTO(Score score) {
        setQualitativeScore(score.getQualitativeScore());
        setTotalco2(score.getTotalco2());
        if (score.getUserActivity() != null) {
            setActivityDate(score.getUserActivity().getDate());
        }
    }
}
