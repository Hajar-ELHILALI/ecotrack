package EcoTrack.server.service.implementation;

import EcoTrack.server.DTO.ActivityTypeDTO;
import EcoTrack.server.entity.ActivityType;
import EcoTrack.server.entity.Category;
import EcoTrack.server.repository.ActivityTypeRepository;
import EcoTrack.server.repository.CategoryRepository;
import EcoTrack.server.service.ActivityTypeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActivityTypeServiceImpl implements ActivityTypeService {

    private final ActivityTypeRepository activityTypeRepository;
    private final CategoryRepository categoryRepository;
    public ActivityTypeServiceImpl(ActivityTypeRepository activityTypeRepository, CategoryRepository categoryRepository) {
        this.activityTypeRepository = activityTypeRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<ActivityType> findAll() {
        return activityTypeRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        activityTypeRepository.deleteById(id);
    }

    @Override
    public ActivityType findById(Long id) {
        Optional<ActivityType> activityTypeOptional = activityTypeRepository.findById(id);
        return activityTypeOptional.orElse(null);
    }



    @Override
    public List<ActivityTypeDTO> findAllDTO() {
        return activityTypeRepository.findAll()
                .stream().map(ActivityTypeDTO::new).toList();
    }

    @Override
    public ActivityTypeDTO findDTOById(Long id) {
        Optional<ActivityType> activityTypeOptional = activityTypeRepository.findById(id);
        return activityTypeOptional.map(ActivityTypeDTO::new)
                .orElse(null);
    }

    @Override
    public ActivityTypeDTO updateDTO(ActivityTypeDTO activityTypeDTO) {
        ActivityType activityType = activityTypeRepository.findById(activityTypeDTO.getId())
                .orElse(null);

        Category category = categoryRepository.findById(activityTypeDTO.getCategoryId())
                        .orElse(null);
        activityType.setName(activityTypeDTO.getName());
        activityType.setUnit(activityTypeDTO.getUnit());
        activityType.setCategory(category);

        return new ActivityTypeDTO(activityTypeRepository.save(activityType));


    }

    @Override
    public ActivityType update(ActivityType activityType) {
        ActivityTypeDTO activityTypeDTO = new ActivityTypeDTO(activityType);
        activityTypeDTO = updateDTO(activityTypeDTO);
        return activityTypeRepository.findById(activityTypeDTO.getId())
                .orElse(null);
    }

    @Override
    public ActivityTypeDTO createDTO(ActivityTypeDTO activityTypeDTO) {
        ActivityType activityType = new ActivityType(activityTypeDTO);

        Category category = categoryRepository.findById(activityTypeDTO.getCategoryId())
                .orElse(null);
        activityType.setName(activityTypeDTO.getName());
        activityType.setUnit(activityTypeDTO.getUnit());
        activityType.setCategory(category);


        return new ActivityTypeDTO(activityTypeRepository.save(activityType));




    }


    @Override
    public ActivityType create(ActivityType activityType) {
        ActivityTypeDTO activityTypeDTO = new ActivityTypeDTO(activityType);
        activityTypeDTO = createDTO(activityTypeDTO);
        return activityTypeRepository.findById(activityTypeDTO.getId())
                .orElse(null);

    }

    @Override
    public void deleteDTOById(Long id) {
        activityTypeRepository.deleteById(id);
    }

}
