package EcoTrack.server.controller;

import EcoTrack.server.DTO.HouseholdDTO;
import EcoTrack.server.service.HouseholdService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/households")
public class HouseholdController {
    private final HouseholdService householdService;
    public HouseholdController(HouseholdService householdService) {
        this.householdService = householdService;
    }

    @PostMapping("/")
    public ResponseEntity<HouseholdDTO> createHousehold(@RequestBody HouseholdDTO householdDTO) {
        return ResponseEntity.ok(householdService.createDTO(householdDTO));
    }

    @GetMapping("/")
    public ResponseEntity<List<HouseholdDTO>> getAllHouseholds() {
        return ResponseEntity.ok(householdService.findAllDTO());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HouseholdDTO> getHouseholdById(@PathVariable Long id) {
        return ResponseEntity.ok(householdService.findDTOById(id));
    }

    @PutMapping("/")
    public ResponseEntity<HouseholdDTO> updateHousehold(@RequestBody HouseholdDTO householdDTO) {
        return ResponseEntity.ok(householdService.updateDTO(householdDTO));
    }

    @DeleteMapping("/{id}")
    public void deleteHousehold(@PathVariable Long id) {
        householdService.deleteDTOById(id);
    }
}
