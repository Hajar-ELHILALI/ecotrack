package EcoTrack.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import EcoTrack.server.entity.Advice;

@Repository
public interface AdviceRepository extends JpaRepository<Advice, Long> {
}