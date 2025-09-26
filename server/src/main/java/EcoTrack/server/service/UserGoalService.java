package EcoTrack.server.service;

import EcoTrack.server.DTO.UserGoalDTO;
import EcoTrack.server.entity.User;
import EcoTrack.server.entity.UserGoal;

import java.security.Principal;
import java.util.List;

public interface UserGoalService {
    UserGoalDTO createGoal(UserGoalDTO dto, String email);
    List<UserGoal> findUserGoals(User user);
    void deleteDTOById(Long id);
    UserGoalDTO updateDTO(UserGoalDTO userGoalDTO, String email);
    void checkGoalsAndNotify();
}
