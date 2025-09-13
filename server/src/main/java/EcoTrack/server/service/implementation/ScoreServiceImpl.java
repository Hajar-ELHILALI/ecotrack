package EcoTrack.server.service.implementation;

import EcoTrack.server.DTO.ScoreDTO;
import EcoTrack.server.entity.*;
import EcoTrack.server.enums.CategoryType;
import EcoTrack.server.enums.QualitativeScore;
import EcoTrack.server.enums.SharingType;
import EcoTrack.server.exception.NotFoundException;
import EcoTrack.server.repository.ActivityTypeRepository;
import EcoTrack.server.repository.EmissionFactorRepository;
import EcoTrack.server.repository.ScoreRepository;
import EcoTrack.server.repository.UserRepository;
import EcoTrack.server.service.ScoreService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static EcoTrack.server.enums.CategoryType.*;

@Service
public class ScoreServiceImpl implements ScoreService {

    private final ScoreRepository scoreRepository;
    private final UserRepository userRepository;
    private final EmissionFactorServiceImpl emissionFactorServiceImpl;
    private final EmissionFactorRepository emissionFactorRepository;

    public ScoreServiceImpl(ScoreRepository scoreRepository, UserRepository userRepository, EmissionFactorServiceImpl emissionFactorServiceImpl, EmissionFactorRepository emissionFactorRepository) {
        this.scoreRepository = scoreRepository;
        this.userRepository = userRepository;
        this.emissionFactorServiceImpl = emissionFactorServiceImpl;
        this.emissionFactorRepository = emissionFactorRepository;
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

        User user = userRepository.findById(scoreDTO.getUserId())
                .orElseThrow(() -> new NotFoundException("User not found with : " + scoreDTO.getUserId()));
        score.setUser(user);

        return new ScoreDTO(scoreRepository.save(score));
    }

    @Override
    public ScoreDTO createDTO(ScoreDTO scoreDTO) {
        Score score = new Score(scoreDTO);

        User user = userRepository.findById(scoreDTO.getUserId())
                .orElseThrow(() -> new NotFoundException("User not found with : " + scoreDTO.getId()));
        score.setUser(user);

        return new ScoreDTO(scoreRepository.save(score));
    }

    public void calculateAndSaveScore(UserActivity activity) {
        User user = activity.getUser();
        ActivityType type = activity.getActivityType();

        EmissionFactor factor = emissionFactorRepository
                .findByActivityTypeAndCountry(type, user.getCountry())
                .orElseThrow(() -> new RuntimeException("Emission factor not found"));

        double emission = activity.getQuantity() * factor.getFactor();
        if (activity.getSharingType() != SharingType.SOLO && activity.getNbrPersonnes() > 0) {
            emission = emission / activity.getNbrPersonnes();
        }

        QualitativeScore qualitative = mapToQualitativeScore(type.getCategory(), emission);

        Score score = new Score();
        score.setUser(user);
        score.setTotalco2(emission);
        score.setQualitativeScore(qualitative);
        scoreRepository.save(score);
    }

    private QualitativeScore mapToQualitativeScore(Category category, double emission) {
        switch (category.getCategoryType()) {
            case TRANSPORT:
                if (emission < 2) return QualitativeScore.EXCELLENT;
                else if (emission < 20) return QualitativeScore.GOOD;
                else if (emission < 50) return QualitativeScore.AVERAGE;
                else if (emission < 100) return QualitativeScore.POOR;
                else return QualitativeScore.CRITICAL;

            case ELECTRICITY:
                if (emission < 1) return QualitativeScore.EXCELLENT;
                else if (emission < 10) return QualitativeScore.GOOD;
                else if (emission < 50) return QualitativeScore.AVERAGE;
                else if (emission < 200) return QualitativeScore.POOR;
                else return QualitativeScore.CRITICAL;

            case NUTRITION:
                if (emission < 2) return QualitativeScore.EXCELLENT;
                else if (emission < 5) return QualitativeScore.GOOD;
                else if (emission < 15) return QualitativeScore.AVERAGE;
                else if (emission < 50) return QualitativeScore.POOR;
                else return QualitativeScore.CRITICAL;

            default:
                throw new IllegalArgumentException("Unknown category: " + category);
        }
    }
}
