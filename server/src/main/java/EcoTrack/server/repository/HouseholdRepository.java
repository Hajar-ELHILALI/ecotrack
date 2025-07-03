package EcoTrack.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import EcoTrack.server.entity.Household;

@Repository
public interface HouseholdRepository extends JpaRepository<Household, Long> {
}