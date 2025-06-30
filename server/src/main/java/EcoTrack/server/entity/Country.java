package EcoTrack.server.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Countries")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String code_iso;

    @OneToMany(mappedBy = "country")
    private Set<Country> coutries = new HashSet<>();

    // user-country


}
