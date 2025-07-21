package EcoTrack.server.controller;

import EcoTrack.server.DTO.AdviceDTO;

import EcoTrack.server.service.AdviceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/advices")
public class AdviceController {
    private final AdviceService adviceService;


    public AdviceController(AdviceService adviceService) {
        this.adviceService = adviceService;

    }

    @GetMapping("/{id}")
    public ResponseEntity<AdviceDTO> getAdvice(@PathVariable Long id) {
        return ResponseEntity.ok(adviceService.findDTOById(id));
    }

    @GetMapping("/")
    public ResponseEntity<List<AdviceDTO>> getAllAdvices() {
        return ResponseEntity.ok(adviceService.findAllDTO());
    }

    @PostMapping("/")
    public ResponseEntity<AdviceDTO> createAdvice(@RequestBody AdviceDTO adviceDTO) {
        return ResponseEntity.ok(adviceService.createDTO(adviceDTO));
    }

    @PutMapping("/")
    public ResponseEntity<AdviceDTO> updateAdvice(@RequestBody AdviceDTO adviceDTO) {
        return ResponseEntity.ok(adviceService.updateDTO(adviceDTO));
    }

    @DeleteMapping("/{id}")
    public void deleteAdvice(@PathVariable Long id) {
        adviceService.deleteDTOById(id);
    }
}
