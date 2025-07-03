package EcoTrack.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import EcoTrack.server.entity.Score;

@Repository
public interface ScoreRepository extends JpaRepository<Score, Long> {
}