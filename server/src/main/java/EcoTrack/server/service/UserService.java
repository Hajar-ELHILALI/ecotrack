package EcoTrack.server.service;

import EcoTrack.server.DTO.RegisterRequestDTO;
import EcoTrack.server.DTO.UserDTO;
import org.springframework.http.ResponseEntity;

public interface UserService {
    void deleteByEmail(String email);
    UserDTO findByEmail(String email);
    ResponseEntity<UserDTO> register(RegisterRequestDTO registerRequestDTO);

}
