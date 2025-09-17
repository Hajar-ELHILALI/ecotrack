package EcoTrack.server.repository;

import EcoTrack.server.entity.Category;
import EcoTrack.server.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import EcoTrack.server.entity.ActivityType;

import java.util.List;
import java.util.Optional;

@Repository
public interface ActivityTypeRepository extends JpaRepository<ActivityType, Long> {
    Optional<ActivityType> findByName(String name);
    List<ActivityType> findByCategoryId(Long id);
}