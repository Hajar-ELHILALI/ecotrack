package EcoTrack.server.repository;

import EcoTrack.server.DTO.UserGoalDTO;
import EcoTrack.server.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import EcoTrack.server.entity.UserGoal;

import java.util.List;

@Repository
public interface UserGoalRepository extends JpaRepository<UserGoal, Long> {
    List<UserGoal> findUserGoalByUser(User user);

}