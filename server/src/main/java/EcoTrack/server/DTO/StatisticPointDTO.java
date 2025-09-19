package EcoTrack.server.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StatisticPointDTO {
    private String date;   // ex: "2025-01-01", "2025-01", "2025"
    private double totalCo2;
}
