package EcoTrack.server.service;

import EcoTrack.server.DTO.UserGoalDTO;
import EcoTrack.server.entity.UserGoal;

public interface UserGoalService {
    UserGoalDTO createGoal(UserGoalDTO dto, String email);
}
