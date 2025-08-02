package EcoTrack.server.repository;

import EcoTrack.server.DTO.CountryDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import EcoTrack.server.entity.Country;

import java.util.Optional;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
    Optional<CountryDTO> findCountryByName(String name);
    Optional<CountryDTO> findCountryByCodeIso(String iso);
}