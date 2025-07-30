package EcoTrack.server.service.implementation;

import EcoTrack.server.DTO.ActivityTypeDTO;
import EcoTrack.server.DTO.BadgeDTO;
import EcoTrack.server.entity.Badge;
import EcoTrack.server.exception.NotFoundException;
import EcoTrack.server.repository.BadgeRepository;
import EcoTrack.server.service.BadgeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BadgeServiceImpl implements BadgeService {
    private final BadgeRepository badgeRepository;
    public BadgeServiceImpl(BadgeRepository badgeRepository) {
        this.badgeRepository = badgeRepository;
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
}
