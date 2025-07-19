package EcoTrack.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import EcoTrack.server.entity.User;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    void deleteByEmail(String email);
    Optional<User> findByEmail(String email);
    Optional<User> findById(Long id);
}