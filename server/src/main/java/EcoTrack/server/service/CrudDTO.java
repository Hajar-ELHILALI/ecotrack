package EcoTrack.server.service;

import java.util.List;

public interface CrudDTO <DTO, ID>{
    DTO createDTO(DTO dto);
    DTO updateDTO(DTO dto);
    DTO findDTOById(ID id);
    List<DTO> findAllDTO();
    void deleteDTOById(ID id);
}
