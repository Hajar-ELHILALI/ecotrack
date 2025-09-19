package EcoTrack.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import EcoTrack.server.entity.Score;

import java.util.List;

@Repository
public interface ScoreRepository extends JpaRepository<Score, Long> {
    List<Score> findByUserIdOrderByUserActivityDateAsc(Long userId);
    List<Score> findByUserId(Long userId);
}