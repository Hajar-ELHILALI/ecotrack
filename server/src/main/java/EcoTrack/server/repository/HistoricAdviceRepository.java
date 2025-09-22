package EcoTrack.server.repository;

import EcoTrack.server.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import EcoTrack.server.entity.HistoricAdvice;

import java.util.List;

@Repository
public interface HistoricAdviceRepository extends JpaRepository<HistoricAdvice, Long> {
    List<HistoricAdvice> findByUser(User user);
}