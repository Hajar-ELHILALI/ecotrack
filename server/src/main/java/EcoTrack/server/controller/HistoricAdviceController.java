package EcoTrack.server.controller;

import EcoTrack.server.DTO.HistoricAdviceDTO;
import EcoTrack.server.service.HistoricAdviceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/historic_advice")
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
}
