package EcoTrack.server.service.implementation;

import EcoTrack.server.DTO.ActivityTypeDTO;
import EcoTrack.server.DTO.BadgeDTO;
import EcoTrack.server.entity.Badge;
import EcoTrack.server.entity.Score;
import EcoTrack.server.entity.User;
import EcoTrack.server.enums.BadgeLabel;
import EcoTrack.server.exception.NotFoundException;
import EcoTrack.server.repository.BadgeRepository;
import EcoTrack.server.repository.ScoreRepository;
import EcoTrack.server.repository.UserRepository;
import EcoTrack.server.service.BadgeService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BadgeServiceImpl implements BadgeService {
    private final BadgeRepository badgeRepository;
    private final ScoreRepository scoreRepository;
    private final UserRepository userRepository;
    public BadgeServiceImpl(BadgeRepository badgeRepository, ScoreRepository scoreRepository,UserRepository userRepository) {
        this.badgeRepository = badgeRepository;
        this.scoreRepository = scoreRepository;
        this.userRepository = userRepository;
    }

    @Override
    public BadgeDTO findDTOById(Long id) {
       Optional<Badge> badge = badgeRepository.findById(id);
       return badge.map(BadgeDTO::new).orElse(null);
    }

    @Override
    public List<BadgeDTO> findAllDTO(){
        return badgeRepository.findAll()
                .stream().map(BadgeDTO::new).toList();
    }

    @Override
    public void deleteDTOById(Long id) {
        badgeRepository.deleteById(id);
    }

    @Override
    public BadgeDTO createDTO(BadgeDTO badgeDTO) {
        Badge badge = new Badge(badgeDTO);
        return new BadgeDTO(badgeRepository.save(badge));
    }

    @Override
    public BadgeDTO updateDTO(BadgeDTO badgeDTO) {
        Badge badge = badgeRepository.findById(badgeDTO.getId())
                .orElseThrow(() -> new NotFoundException("ActivtyType not found with : " + badgeDTO.getId()));
        badge.setDescription(badgeDTO.getDescription());
        badge.setLabel(badgeDTO.getLabel());
        return new BadgeDTO(badgeRepository.save(badge));
    }


     //Met à jour le badge d'1 user pour une période donnée
    public void updateUserBadge(User user, LocalDate start, LocalDate end) {
        double monthlyTotal = scoreRepository
                .findByUserIdAndUserActivity_DateBetween(user.getId(), start, end)
                .stream()
                .mapToDouble(Score::getTotalco2)
                .sum();

        if (monthlyTotal < 50) {
            user.setBadge(badgeRepository.findByLabel(BadgeLabel.Planet_Hero).orElse(null));
        } else if (monthlyTotal < 150) {
            user.setBadge(badgeRepository.findByLabel(BadgeLabel.Green_Achiever).orElse(null));
        } else {
            user.setBadge(badgeRepository.findByLabel(BadgeLabel.Eco_Novice).orElse(null));
        }

        userRepository.save(user);
    }


    //Met à jour les badges pour tous les utilisateurs du mois précédent
    public void updateAllUserBadges() {
        LocalDate now = LocalDate.now();
        LocalDate startOfLastMonth = now.minusMonths(1).withDayOfMonth(1);
        LocalDate endOfLastMonth = now.withDayOfMonth(1).minusDays(1);

        List<User> users = userRepository.findAll();

        for (User user : users) {
            updateUserBadge(user, startOfLastMonth, endOfLastMonth);
        }
    }
}
