package EcoTrack.server.service.implementation;


import EcoTrack.server.DTO.AdviceDTO;
import EcoTrack.server.entity.Advice;
import EcoTrack.server.exception.NotFoundException;
import EcoTrack.server.repository.AdviceRepository;
import EcoTrack.server.service.AdviceService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdviceServiceImpl implements AdviceService {

    private final AdviceRepository adviceRepository;
    public AdviceServiceImpl(AdviceRepository adviceRepository) {
        this.adviceRepository = adviceRepository;
    }




    @Override
    public List<AdviceDTO> findAllDTO() {
        return adviceRepository.findAll()
                .stream().map(AdviceDTO::new).toList();
    }

    @Override
    public AdviceDTO findDTOById(Long id) {
        Optional<Advice> adviceOptional = adviceRepository.findById(id);
        return adviceOptional.map(AdviceDTO::new)
                .orElseThrow(() -> new NotFoundException("Advice not found with : " + id));
    }

    @Override
    public void deleteDTOById(Long id) {
        adviceRepository.deleteById(id);
    }

    @Override
    public AdviceDTO updateDTO(AdviceDTO adviceDTO) {
        Advice advice = adviceRepository.findById(adviceDTO.getId())
                .orElseThrow(() -> new NotFoundException("Advice not found with : " + adviceDTO.getId()));
        advice.setContent(adviceDTO.getContent());
        advice.setGenerationDate(adviceDTO.getGenerationDate());
        advice.setType(adviceDTO.getType());

        return new AdviceDTO(adviceRepository.save(advice));
    }



    @Override
    public AdviceDTO createDTO(AdviceDTO adviceDTO) {
        Advice advice = new Advice(adviceDTO);

        advice.setGenerationDate(adviceDTO.getGenerationDate());
        advice.setType(adviceDTO.getType());
        advice.setContent(adviceDTO.getContent());
        return new AdviceDTO(adviceRepository.save(advice));
    }

}

