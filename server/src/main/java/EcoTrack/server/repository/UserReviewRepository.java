package EcoTrack.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import EcoTrack.server.entity.UserReview;

@Repository
public interface UserReviewRepository extends JpaRepository<UserReview, Long> {
}