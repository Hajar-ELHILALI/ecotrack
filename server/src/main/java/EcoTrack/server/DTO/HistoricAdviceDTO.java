package EcoTrack.server.DTO;

import EcoTrack.server.entity.HistoricAdvice;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HistoricAdviceDTO {
    private Long id;
    private Date sentDate;

    @NotNull
    private Long adviceId;
    private Long userId;
    private boolean isRead;
    private boolean isApplied;
    public HistoricAdviceDTO(HistoricAdvice historicAdvice){
        setId(historicAdvice.getId());
        setAdviceId(historicAdvice.getAdvice().getId());
        setSentDate(historicAdvice.getSentDate());
        setUserId(historicAdvice.getUser().getId());
        setApplied(historicAdvice.isApplied());
        setRead(historicAdvice.isRead());
    }
}
