package EcoTrack.server.entity;

import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="Categories")
public class Category {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY )
    private int id;
    private String name;

    @OneToMany(mappedBy = "category")
    private Set<Category> categories = new HashSet<>();
}
