package EcoTrack.server.repository;

import EcoTrack.server.enums.BadgeLabel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import EcoTrack.server.entity.Badge;

import java.util.Optional;

@Repository
public interface BadgeRepository extends JpaRepository<Badge, Long> {
    Optional<Badge> findByLabel(BadgeLabel badgeLabel);
}