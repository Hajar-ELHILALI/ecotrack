package EcoTrack.server.repository;

import EcoTrack.server.entity.ActivityType;
import EcoTrack.server.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import EcoTrack.server.entity.EmissionFactor;

import java.util.Optional;

@Repository
public interface EmissionFactorRepository extends JpaRepository<EmissionFactor, Long> {
    Optional<EmissionFactor> findByActivityTypeAndCountry(ActivityType activityType, Country country);
}