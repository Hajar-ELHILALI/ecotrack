package EcoTrack.server.service.implementation;

import EcoTrack.server.DTO.HouseholdDTO;
import EcoTrack.server.entity.Household;
import EcoTrack.server.exception.NotFoundException;
import EcoTrack.server.repository.HouseholdRepository;
import EcoTrack.server.service.HouseholdService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HouseholdServiceImpl implements HouseholdService {
    private final HouseholdRepository householdRepository;
    public HouseholdServiceImpl(HouseholdRepository householdRepository) {
        this.householdRepository = householdRepository;
    }

    @Override
    public HouseholdDTO findDTOById(Long id) {
        Optional<Household> householdOptional = householdRepository.findById(id);
        return householdOptional.map(HouseholdDTO::new)
                .orElseThrow(() -> new NotFoundException("Household not found with : " + id));
    }

    @Override
    public List<HouseholdDTO> findAllDTO() {
        return householdRepository.findAll().stream()
                .map(HouseholdDTO::new).toList();
    }

    @Override
    public void deleteDTOById(Long id) {
        householdRepository.deleteById(id);
    }

    @Override
    public HouseholdDTO updateDTO(HouseholdDTO householdDTO) {
        Household household = householdRepository.findById(householdDTO.getId())
                .orElseThrow(() -> new NotFoundException("Household not found with : " + householdDTO.getId()));

        household.setCity(householdDTO.getCity());
        household.setNumber(householdDTO.getNumber());
        household.setStreet(householdDTO.getStreet());
        return new HouseholdDTO(householdRepository.save(household));
    }

    @Override
    public HouseholdDTO createDTO(HouseholdDTO householdDTO) {
        Household household = new Household(householdDTO);
        return new HouseholdDTO(householdRepository.save(household));
    }
}
