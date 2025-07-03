package EcoTrack.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import EcoTrack.server.entity.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
}