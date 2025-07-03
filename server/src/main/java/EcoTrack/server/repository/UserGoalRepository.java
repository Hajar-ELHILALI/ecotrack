package EcoTrack.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import EcoTrack.server.entity.UserGoal;

@Repository
public interface UserGoalRepository extends JpaRepository<UserGoal, Long> {
}