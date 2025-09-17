package EcoTrack.server.service;

import EcoTrack.server.DTO.ActivityTypeDTO;
import EcoTrack.server.entity.ActivityType;
import EcoTrack.server.entity.Category;

import java.util.List;

public interface ActivityTypeService extends CrudDTO<ActivityTypeDTO, Long> {
    List<ActivityTypeDTO> findActivityTypesByCategory(Long categoryId);
}
