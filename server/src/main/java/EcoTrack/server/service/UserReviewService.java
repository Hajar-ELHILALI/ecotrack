package EcoTrack.server.service;

import EcoTrack.server.DTO.UserReviewDTO;
import EcoTrack.server.entity.UserReview;

import java.util.List;

public interface UserReviewService extends CrudDTO<UserReviewDTO, Long>{
    List<UserReview> findUserReviews(Long id);
}
