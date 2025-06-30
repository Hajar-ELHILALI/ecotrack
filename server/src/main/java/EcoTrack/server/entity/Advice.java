package EcoTrack.server.entity;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity

public class Advice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String content;
    private Date generationDate;
    // on peut faire type comme enum
    private String type;

    @OneToMany(mappedBy = "advice")
    private Set<HistoricAdvice> historicAdvice = new HashSet<>();
}
