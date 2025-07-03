package EcoTrack.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import EcoTrack.server.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}