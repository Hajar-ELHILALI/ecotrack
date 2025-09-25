package EcoTrack.server.service.implementation;

import EcoTrack.server.DTO.UserActivityDTO;
import EcoTrack.server.DTO.UserGoalDTO;
import EcoTrack.server.entity.User;
import EcoTrack.server.entity.UserGoal;
import EcoTrack.server.exception.NotFoundException;
import EcoTrack.server.repository.ScoreRepository;
import EcoTrack.server.repository.UserGoalRepository;
import EcoTrack.server.repository.UserRepository;
import EcoTrack.server.service.UserGoalService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UserGoalServiceImpl implements UserGoalService {
    private final UserGoalRepository userGoalRepository;
    private final UserRepository userRepository;
    private final ScoreRepository scoreRepository;

    public UserGoalServiceImpl(UserGoalRepository userGoalRepository, UserRepository userRepository,ScoreRepository scoreRepository) {
        this.userGoalRepository = userGoalRepository;
        this.userRepository = userRepository;
        this.scoreRepository= scoreRepository;
    }


    public UserGoalDTO findDTOById(Long id) {
        Optional<UserGoal> userGoal = userGoalRepository.findById(id);
        return userGoal.map(UserGoalDTO::new)
                .orElseThrow(() -> new NotFoundException("UserGoal not found with : " + id));
    }

    public List<UserGoalDTO> findAllDTO() {
        return userGoalRepository.findAll().stream().map(UserGoalDTO::new).toList();
    }

    @Override
    public void deleteDTOById(Long id) {
        userGoalRepository.deleteById(id);
    }

    @Override
    public UserGoalDTO createGoal(UserGoalDTO dto, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        UserGoal goal = new UserGoal();
        goal.setStartDate(dto.getStartDate());
        goal.setEndDate(dto.getEndDate());
        goal.setEmissionTarget(dto.getEmissionTarget());
        goal.setGoalAchieved(false);
        goal.setUser(user);

        return new UserGoalDTO(userGoalRepository.save(goal));
    }

    @Scheduled(cron = "0 0 0 * * ?") // Tous les jours à minuit
    public void checkGoals() {
        LocalDate today = LocalDate.now();
        List<UserGoal> goals = userGoalRepository.findExpiredUnachievedGoals(today);

        for (UserGoal goal : goals) {
            Double total = scoreRepository.sumUserScoresBetweenDates(
                    goal.getUser().getId(),
                    goal.getStartDate(),
                    goal.getEndDate()
            );

            if (total != null && total <= goal.getEmissionTarget()) {
                goal.setGoalAchieved(true);
                userGoalRepository.save(goal);
            }
        }
    }

    @Override
    public UserGoalDTO updateDTO(UserGoalDTO userGoalDTO, String email) {
        UserGoal userGoal = userGoalRepository.findById(userGoalDTO.getId())
                .orElseThrow(() -> new NotFoundException("UserGoal not found with : " + userGoalDTO.getId()));

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        userGoal.setStartDate(userGoalDTO.getStartDate());
        userGoal.setEndDate(userGoalDTO.getEndDate());
        userGoal.setGoalAchieved(userGoalDTO.isGoalAchieved());
        userGoal.setEmissionTarget(userGoalDTO.getEmissionTarget());

        return new UserGoalDTO(userGoalRepository.save(userGoal));



    }

    @Override
    public List<UserGoal> findUserGoals(User user) {
        List<UserGoal> userGoals = userGoalRepository.findUserGoalByUser(user);
        return userGoals;
    }

}
