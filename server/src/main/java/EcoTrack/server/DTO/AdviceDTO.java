package EcoTrack.server.DTO;

import EcoTrack.server.entity.Advice;
import EcoTrack.server.entity.HistoricAdvice;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AdviceDTO {
    private Long id;

    @NotNull
    private String content;

    @NotNull
    private Date generatioDate;
    @NotNull
    private String type;
    private Set<HistoricAdvice> historicAdvice = new HashSet<>();
    public AdviceDTO(Advice advice){
        setId(advice.getId());
        setContent(advice.getContent());
        setType(advice.getType());
        setGeneratioDate(advice.getGenerationDate());
        setHistoricAdvice(advice.getHistoricAdvice());
    }
}
