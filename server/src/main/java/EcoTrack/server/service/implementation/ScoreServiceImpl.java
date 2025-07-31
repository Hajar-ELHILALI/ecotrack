package EcoTrack.server.service.implementation;

import EcoTrack.server.DTO.ScoreDTO;
import EcoTrack.server.entity.Score;
import EcoTrack.server.entity.User;
import EcoTrack.server.exception.NotFoundException;
import EcoTrack.server.repository.ScoreRepository;
import EcoTrack.server.repository.UserRepository;
import EcoTrack.server.service.ScoreService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ScoreServiceImpl implements ScoreService {

    private final ScoreRepository scoreRepository;
    private final UserRepository userRepository;

    public ScoreServiceImpl(ScoreRepository scoreRepository, UserRepository userRepository) {
        this.scoreRepository = scoreRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ScoreDTO findDTOById(Long id) {
        Optional<Score> score = scoreRepository.findById(id);
        return score.map(ScoreDTO::new)
                .orElseThrow(() -> new NotFoundException("Score not found with : " + id));
    }

    @Override
    public List<ScoreDTO> findAllDTO() {
        return scoreRepository.findAll().stream().map(ScoreDTO::new).toList();
    }

    @Override
    public void deleteDTOById(Long id) {
        scoreRepository.deleteById(id);
    }

    @Override
    public ScoreDTO updateDTO(ScoreDTO scoreDTO) {
        Score score = scoreRepository.findById(scoreDTO.getId())
                .orElseThrow(() -> new NotFoundException("Score not found with : " + scoreDTO.getId()));

        score.setQualitativeScore(scoreDTO.getQualitativeScore());
        score.setTotalco2(scoreDTO.getTotalco2());

        User oldUser = score.getUser();

        if(!oldUser.getId().equals(scoreDTO.getUserId())) {
            oldUser.getScores().remove(score);
            User user = userRepository.findById(scoreDTO.getUserId())
                    .orElseThrow(() -> new NotFoundException("User not found with : " + scoreDTO.getUserId()));
            score.setUser(user);
            user.getScores().add(score);
        }

        return new ScoreDTO(scoreRepository.save(score));
    }

    @Override
    public ScoreDTO createDTO(ScoreDTO scoreDTO) {
        Score score = new Score(scoreDTO);

        User user = userRepository.findById(scoreDTO.getUserId())
                .orElseThrow(() -> new NotFoundException("User not found with : " + scoreDTO.getId()));
        score.setUser(user);
        user.getScores().add(score);

        return new ScoreDTO(scoreRepository.save(score));
    }
}
