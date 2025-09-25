package EcoTrack.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import EcoTrack.server.entity.Score;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ScoreRepository extends JpaRepository<Score, Long> {
    List<Score> findByUserIdOrderByUserActivityDateAsc(Long userId);
    List<Score> findByUserId(Long userId);
    @Query("SELECT SUM(s.totalco2) FROM Score s WHERE s.user.id = :userId AND s.userActivity.date BETWEEN :start AND :end")
    Double sumUserScoresBetweenDates(@Param("userId") Long userId,
                                     @Param("start") LocalDate start,
                                     @Param("end") LocalDate end);
}