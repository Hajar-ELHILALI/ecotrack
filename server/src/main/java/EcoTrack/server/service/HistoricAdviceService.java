package EcoTrack.server.service;

import EcoTrack.server.DTO.HistoricAdviceDTO;
import EcoTrack.server.entity.HistoricAdvice;

import java.util.List;

public interface HistoricAdviceService extends CrudDTO<HistoricAdviceDTO, Long>{
    List<HistoricAdviceDTO> getHistoricAdvicesForUser(String email);
}
