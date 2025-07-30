package EcoTrack.server.controller;

import EcoTrack.server.DTO.EmissionFactorDTO;
import EcoTrack.server.service.EmissionFactorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/emissionFactors")
public class EmissionFactorController {
    private final EmissionFactorService emisssionFactorService;
    public EmissionFactorController(EmissionFactorService emisssionFactorService) {
        this.emisssionFactorService = emisssionFactorService;
    }

    @PostMapping("/")
    public ResponseEntity<EmissionFactorDTO> createEmissionFactor(@RequestBody EmissionFactorDTO emisssionFactorDTO) {
        return ResponseEntity.ok(emisssionFactorService.createDTO(emisssionFactorDTO));
    }

    @GetMapping("/")
    public ResponseEntity<List<EmissionFactorDTO>> getAllEmissionFactors() {
        return ResponseEntity.ok(emisssionFactorService.findAllDTO());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmissionFactorDTO> getEmissionFactorById(@PathVariable Long id) {
        return ResponseEntity.ok(emisssionFactorService.findDTOById(id));
    }

    @PutMapping("/")
    public ResponseEntity<EmissionFactorDTO> updateEmissionFactor(@RequestBody EmissionFactorDTO emisssionFactorDTO) {
        return ResponseEntity.ok(emisssionFactorService.updateDTO(emisssionFactorDTO));
    }

    @DeleteMapping("/{id}")
    public void deleteEmissionFactor(@PathVariable Long id) {
        emisssionFactorService.deleteDTOById(id);
    }
}
