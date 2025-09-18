package EcoTrack.server.controller;

import EcoTrack.server.DTO.CountryDTO;
import EcoTrack.server.service.CountryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/countries")
public class CountryController{
    private final CountryService countryService;
    public CountryController(CountryService countryService){
        this.countryService = countryService;
    }

    @PostMapping("/")
    public ResponseEntity<CountryDTO> createCountry(@RequestBody CountryDTO countryDTO){
        return ResponseEntity.ok(countryService.createDTO(countryDTO));
    }

    @GetMapping("/")
    public ResponseEntity<List<CountryDTO>> getAllCountries(){
        return ResponseEntity.ok(countryService.findAllDTO());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CountryDTO> getCountryById(@PathVariable Long id){
        return ResponseEntity.ok(countryService.findDTOById(id));
    }

    @PutMapping("/")
    public ResponseEntity<CountryDTO> updateCountry(@RequestBody CountryDTO countryDTO){
        return ResponseEntity.ok(countryService.updateDTO(countryDTO));
    }

    @DeleteMapping("/{id}")
    public void deleteCountry(@PathVariable Long id){
        countryService.deleteDTOById(id);
    }

    @GetMapping("/countryName/{countryName}")
    public ResponseEntity<CountryDTO> getCountryByName(@PathVariable String countryName){
        return ResponseEntity.ok(countryService.findCountryByName(countryName));
    }
}
