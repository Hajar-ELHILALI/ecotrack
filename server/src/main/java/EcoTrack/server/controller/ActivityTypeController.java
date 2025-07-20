package EcoTrack.server.controller;

import EcoTrack.server.DTO.ActivityTypeDTO;
import EcoTrack.server.entity.ActivityType;
import EcoTrack.server.service.ActivityTypeService;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activity-types")
public class ActivityTypeController {

    private final ActivityTypeService activityTypeService;
    public ActivityTypeController(ActivityTypeService activityTypeService) {
        this.activityTypeService = activityTypeService;

    }

    @GetMapping("/")
    public ResponseEntity<List<ActivityType>> getAllActivityTypes() {
        return ResponseEntity.ok(activityTypeService.findAll());

    }

    @PostMapping("/")
    public ResponseEntity<ActivityTypeDTO> createActivityType(@RequestBody @Valid ActivityTypeDTO activityTypeDTO) {
        return ResponseEntity.ok(activityTypeService.createDTO(activityTypeDTO));
    }


}
