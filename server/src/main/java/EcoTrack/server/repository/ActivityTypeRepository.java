package EcoTrack.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import EcoTrack.server.entity.ActivityType;

@Repository
public interface ActivityTypeRepository extends JpaRepository<ActivityType, Long> {
}