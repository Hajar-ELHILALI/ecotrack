package EcoTrack.server.entity;

import EcoTrack.server.DTO.HistoricAdviceDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "adviceHistories")
public class HistoricAdvice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean isRead;
    private boolean isApplied;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate sentDate;

    @ManyToOne
    @JoinColumn(name = "advice_id", nullable = false)
    private Advice advice;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public HistoricAdvice(HistoricAdviceDTO historicAdviceDTO) {
        setRead(historicAdviceDTO.isRead());
        setApplied(historicAdviceDTO.isApplied());
        setSentDate(historicAdviceDTO.getSentDate());

    }

}
