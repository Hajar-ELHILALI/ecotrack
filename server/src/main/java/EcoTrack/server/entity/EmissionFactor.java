package EcoTrack.server.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "emission_factors")
public class EmissionFactor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double factor;
    private String source;

    @ManyToOne
    private Country country;

    @ManyToOne
    private ActivityType activityType;
}
