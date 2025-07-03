package EcoTrack.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import EcoTrack.server.entity.HistoricAdvice;

@Repository
public interface HistoricAdviceRepository extends JpaRepository<HistoricAdvice, Long> {
}