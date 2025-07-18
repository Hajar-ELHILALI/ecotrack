package EcoTrack.server.service;

import EcoTrack.server.DTO.UserGoalDTO;
import EcoTrack.server.entity.UserGoal;

public interface UserGoalService extends CrudDTO<UserGoalDTO, Long>, CrudService<UserGoal, Long>{
}
