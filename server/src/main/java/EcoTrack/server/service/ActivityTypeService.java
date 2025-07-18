package EcoTrack.server.service;

import EcoTrack.server.DTO.ActivityTypeDTO;
import EcoTrack.server.entity.ActivityType;

public interface ActivityTypeService extends CrudDTO<ActivityTypeDTO, Long>, CrudService<ActivityType, Long> {
}
