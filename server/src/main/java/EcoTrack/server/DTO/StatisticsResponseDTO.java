package EcoTrack.server.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StatisticsResponseDTO {
    private List<StatisticPointDTO> transport;
    private List<StatisticPointDTO> electricity;
    private List<StatisticPointDTO> nutrition;
}

