package EcoTrack.server.controller;

import EcoTrack.server.DTO.HistoricAdviceDTO;
import EcoTrack.server.service.HistoricAdviceService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/historic-advice")
public class HistoricAdviceController {
    private final HistoricAdviceService historicAdviceService;
    public HistoricAdviceController(HistoricAdviceService historicAdviceService) {
        this.historicAdviceService = historicAdviceService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<HistoricAdviceDTO> getHistoricAdvice(@PathVariable Long id) {
        return ResponseEntity.ok(historicAdviceService.findDTOById(id));
    }

    @GetMapping("/")
    public ResponseEntity<List<HistoricAdviceDTO>> getAllHistoricAdvice() {
        return ResponseEntity.ok(historicAdviceService.findAllDTO());
    }

    @PostMapping("/")
    public ResponseEntity<HistoricAdviceDTO> createHistoricAdvice(@RequestBody HistoricAdviceDTO historicAdviceDTO) {
        return ResponseEntity.ok(historicAdviceService.createDTO(historicAdviceDTO));
    }

    @PutMapping("/")
    public ResponseEntity<HistoricAdviceDTO> updateHistoricAdvice(@RequestBody HistoricAdviceDTO historicAdviceDTO) {
        return ResponseEntity.ok(historicAdviceService.updateDTO(historicAdviceDTO));
    }

    @DeleteMapping("/{id}")
    public void deleteHistoricAdvice(@PathVariable Long id) {
        historicAdviceService.deleteDTOById(id);
    }

    @GetMapping("/me")
    public List<HistoricAdviceDTO> getMyAdvices(Authentication authentication) {
        String email = authentication.getName();
        return historicAdviceService.getHistoricAdvicesForUser(email);
    }
}
