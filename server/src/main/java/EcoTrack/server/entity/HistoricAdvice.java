package EcoTrack.server.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


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
    private Date sentDate;

    @ManyToOne
    @JoinColumn(name = "advice_id", nullable = false)
    private Advice advice;

    //user-HistoricAdvice
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
