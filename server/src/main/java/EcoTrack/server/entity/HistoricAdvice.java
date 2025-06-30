package EcoTrack.server.entity;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "adviceHistories")
public class HistoricAdvice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private boolean isRead;
    private boolean isApplied;
    private Date sentAt;

    @ManyToOne
    private Advice advice;

    //user-HistoricAdvice


}
