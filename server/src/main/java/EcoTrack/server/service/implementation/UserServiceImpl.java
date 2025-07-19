package EcoTrack.server.service.implementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.core.userdetails.UserDetailsService;
import EcoTrack.server.DTO.RegisterRequestDTO;
import EcoTrack.server.DTO.UserDTO;
import EcoTrack.server.entity.User;
import EcoTrack.server.repository.UserRepository;
import EcoTrack.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import EcoTrack.server.exception.NotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, UserDetailsService userDetailsService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;

    }

    @Override
    public ResponseEntity<UserDTO> register(RegisterRequestDTO registerRequestDTO) {
        User user = registerRequestDTO.user();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        //user.setRole();
        userRepository.save(user);
        return ResponseEntity.ok(new UserDTO(user));
    }

    @Override
    public void deleteByEmail(String email) {
        userRepository.deleteByEmail(email);
    }

    @Override
    public UserDTO findByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User not found with email: " + email));
        return new UserDTO(user);
    }

    @Override
    public UserDTO findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found with : " + id));
        return new UserDTO(user);
    }

    @Override
    public UserDTO updateDTO(UserDTO userDTO) {
        User user = userRepository.findById(userDTO.getId())
                .orElseThrow(() -> new NotFoundException("user not Found!"));
        user.setEmail(userDTO.getEmail());
        user.setUserName(userDTO.getUserName());
        return new UserDTO(userRepository.save(user));
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}