package EcoTrack.server.entity;

import jakarta.persistence.*;

import java.sql.Date;

@Entity

public class HistoricAdvice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private boolean isRead;
    private boolean isApplied;
    private Date date_envoi;

    @ManyToOne
    private Advice advice;

    //user-HistoricAdvice


}
