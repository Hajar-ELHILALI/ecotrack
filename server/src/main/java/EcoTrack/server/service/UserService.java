package EcoTrack.server.service;

import EcoTrack.server.DTO.RegisterRequestDTO;
import EcoTrack.server.DTO.UserDTO;
import EcoTrack.server.entity.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService{
    ResponseEntity<UserDTO> register(RegisterRequestDTO registerRequestDTO);
    void deleteByEmail(String email);
    UserDTO findByEmail(String email);
    UserDTO findById(Long id);
    UserDTO updateDTO(UserDTO userDTO);
    List<User> findAll();
}
