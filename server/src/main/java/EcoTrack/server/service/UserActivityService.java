package EcoTrack.server.service;

import EcoTrack.server.DTO.ActivityDTO;
import EcoTrack.server.DTO.UserActivityDTO;
import EcoTrack.server.entity.UserActivity;

public interface UserActivityService extends CrudDTO<UserActivityDTO, Long>{
    UserActivityDTO createActivity(ActivityDTO dto, String email);
}
