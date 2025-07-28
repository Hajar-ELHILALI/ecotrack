package EcoTrack.server.service.implementation;

import EcoTrack.server.DTO.UserReviewDTO;
import EcoTrack.server.entity.User;
import EcoTrack.server.entity.UserReview;
import EcoTrack.server.exception.NotFoundException;
import EcoTrack.server.repository.UserRepository;
import EcoTrack.server.repository.UserReviewRepository;
import EcoTrack.server.service.UserReviewService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserReviewServiceImpl implements UserReviewService {
    private final UserReviewRepository userReviewRepository;
    private final UserRepository userRepository;

    public UserReviewServiceImpl(UserReviewRepository userReviewRepository, UserRepository userRepository) {
        this.userReviewRepository = userReviewRepository;
        this.userRepository = userRepository;
    }

    @Override
    public UserReviewDTO findDTOById(Long id) {
        Optional<UserReview> userReview = userReviewRepository.findById(id);
        return userReview.map(UserReviewDTO::new)
                .orElseThrow(() -> new NotFoundException("UserReview not found with : " + id));
    }

    @Override
    public List<UserReviewDTO> findAllDTO() {
        return userReviewRepository.findAll().stream().map(UserReviewDTO::new).toList();

    }

    @Override
    public void deleteDTOById(Long id) {
        userReviewRepository.deleteById(id);
    }

    @Override
    public UserReviewDTO createDTO(UserReviewDTO userReviewDTO) {
        UserReview userReview = new UserReview(userReviewDTO);
        User user = userRepository.findById(userReviewDTO.getUserId())
                .orElseThrow(() -> new NotFoundException("User not found with : " + userReviewDTO.getUserId()));
        userReview.setUser(user);
        return new UserReviewDTO(userReviewRepository.save(userReview));

    }

    @Override
    public UserReviewDTO updateDTO(UserReviewDTO userReviewDTO) {
        UserReview userReview = userReviewRepository.findById(userReviewDTO.getId())
                .orElseThrow(() -> new NotFoundException("UserReview not found with : " + userReviewDTO.getId()));
        User user = userRepository.findById(userReviewDTO.getUserId())
                .orElseThrow(() -> new NotFoundException("User not found with : " + userReviewDTO.getUserId()));
        userReview.setUser(user);

        userReview.setRating(userReviewDTO.getRating());
        userReview.setComment(userReviewDTO.getComment());
        userReview.setCreatedAT(userReviewDTO.getCreatedAT());

        return new UserReviewDTO(userReviewRepository.save(userReview));

    }
}
