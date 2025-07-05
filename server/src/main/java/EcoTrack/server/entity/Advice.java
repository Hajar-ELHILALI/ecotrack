package EcoTrack.server.entity;

import EcoTrack.server.DTO.AdviceDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "advice")
public class Advice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;


    private Date generationDate;
    // on peut faire type comme enum
    private String type;

    @OneToMany(mappedBy = "advice")
    private Set<HistoricAdvice> historicAdvice = new HashSet<>();

    public Advice(AdviceDTO adviceDTO){
        setType(adviceDTO.getType());
        setContent(adviceDTO.getContent());
        setGenerationDate(adviceDTO.getGenerationDate());
        setHistoricAdvice(new HashSet<>());
    }
}
