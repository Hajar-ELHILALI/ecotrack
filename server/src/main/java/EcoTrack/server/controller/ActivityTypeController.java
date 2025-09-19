package EcoTrack.server.controller;

import EcoTrack.server.DTO.ActivityTypeDTO;
import EcoTrack.server.entity.ActivityType;
import EcoTrack.server.repository.ActivityTypeRepository;
import EcoTrack.server.service.ActivityTypeService;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activity-types")
public class ActivityTypeController {

    private final ActivityTypeService activityTypeService;
    private final ActivityTypeRepository activityTypeRepository;

    public ActivityTypeController(ActivityTypeService activityTypeService, ActivityTypeRepository activityTypeRepository) {
        this.activityTypeService = activityTypeService;
        this.activityTypeRepository = activityTypeRepository;
    }

    @GetMapping("/")
    public ResponseEntity<List<ActivityTypeDTO>> getAllActivityTypes() {
        return ResponseEntity.ok(activityTypeService.findAllDTO());

    }

    @PostMapping("/")
    public ResponseEntity<ActivityTypeDTO> createActivityType(@RequestBody @Valid ActivityTypeDTO activityTypeDTO) {
        return ResponseEntity.ok(activityTypeService.createDTO(activityTypeDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ActivityTypeDTO> getActivityTypeById(@PathVariable Long id) {
        return  ResponseEntity.ok(activityTypeService.findDTOById(id));
    }

    @DeleteMapping("/{id}")
    public void deleteActivityTypeById(@PathVariable Long id) {
        activityTypeService.deleteDTOById(id);
    }
    @PutMapping("/")
    public ResponseEntity<ActivityTypeDTO> updateActivityType(@RequestBody @Valid ActivityTypeDTO activityTypeDTO) {
        return ResponseEntity.ok(activityTypeService.updateDTO(activityTypeDTO));
    }

    @GetMapping("/by-category/{categoryId}")
    public ResponseEntity<List<ActivityTypeDTO>> getActivityTypesByCategory(@PathVariable Long categoryId) {
        List<ActivityTypeDTO> activityTypes = activityTypeService.findActivityTypesByCategory(categoryId);
        return ResponseEntity.ok(activityTypes);
    }

}
