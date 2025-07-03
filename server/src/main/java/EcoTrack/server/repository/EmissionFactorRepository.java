package EcoTrack.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import EcoTrack.server.entity.EmissionFactor;

@Repository
public interface EmissionFactorRepository extends JpaRepository<EmissionFactor, Long> {
}