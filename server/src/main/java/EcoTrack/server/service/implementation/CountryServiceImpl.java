package EcoTrack.server.service.implementation;

import EcoTrack.server.DTO.CountryDTO;
import EcoTrack.server.entity.Country;
import EcoTrack.server.exception.NotFoundException;
import EcoTrack.server.repository.CountryRepository;
import EcoTrack.server.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;
    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public CountryDTO findDTOById(Long id){
        Optional<Country> countryOptional = countryRepository.findById(id);
        return countryOptional.map(CountryDTO::new)
                .orElseThrow(() -> new NotFoundException("Country not found with : " + id));
    }
    @Override
    public List<CountryDTO> findAllDTO() {
        return countryRepository.findAll().stream()
                .map(CountryDTO::new).toList();
    }

    @Override
    public CountryDTO createDTO(CountryDTO countryDTO) {
        if(countryRepository.findCountryByCodeIso(countryDTO.getCodeIso()).isPresent()){
            throw new IllegalArgumentException("Country already exists!");
        }
        Country country = new Country(countryDTO);
        return new CountryDTO(countryRepository.save(country));
    }

    @Override
    public CountryDTO updateDTO(CountryDTO countryDTO) {
        Country country = countryRepository.findById(countryDTO.getId())
                .orElseThrow(() -> new NotFoundException("Country not found with : " + countryDTO.getId()));
        country.setName(countryDTO.getName());
        country.setCodeIso(countryDTO.getCodeIso());
        return new CountryDTO(countryRepository.save(country));
    }

    @Override
    public void deleteDTOById(Long id){
        countryRepository.deleteById(id);
    }

    @Override
    public CountryDTO findCountryByName(String countryName){
        return countryRepository.findCountryByName(countryName)
                .orElseThrow(() -> new NotFoundException("Country not found with : " + countryName));
    }
}
