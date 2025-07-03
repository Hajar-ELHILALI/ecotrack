package EcoTrack.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import EcoTrack.server.entity.Badge;

@Repository
public interface BadgeRepository extends JpaRepository<Badge, Long> {
}