package EcoTrack.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import EcoTrack.server.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}