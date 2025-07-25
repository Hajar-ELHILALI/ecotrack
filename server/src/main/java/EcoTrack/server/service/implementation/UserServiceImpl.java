package EcoTrack.server.service.implementation;

import EcoTrack.server.DTO.HouseholdDTO;
import EcoTrack.server.DTO.RegisterRequestDTO;
import EcoTrack.server.DTO.UserDTO;
import EcoTrack.server.entity.*;
import EcoTrack.server.repository.*;
import EcoTrack.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import EcoTrack.server.exception.NotFoundException;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final CountryRepository countryRepository;
    private final HouseholdRepository householdRepository;
    private final PasswordEncoder passwordEncoder;
    private final BadgeRepository badgeRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, CountryRepository countryRepository, HouseholdRepository householdRepository, PasswordEncoder passwordEncoder, BadgeRepository badgeRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.countryRepository = countryRepository;
        this.householdRepository = householdRepository;
        this.passwordEncoder = passwordEncoder;
        this.badgeRepository = badgeRepository;
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

    @Override
    public ResponseEntity<UserDTO> register(RegisterRequestDTO registerRequestDTO) {

        User user = new User(registerRequestDTO.user());


        Role userRole = roleRepository.findById(1L)
                .orElseThrow(() -> new NotFoundException("User Role not found"));
        user.setRole(userRole);

        Country country = countryRepository.findById(registerRequestDTO.getCountryId())
                .orElseThrow(() -> new NotFoundException("Country not found"));
        Household household = householdRepository.findById(registerRequestDTO.getHouseholdId())
                .orElseThrow(() -> new NotFoundException("Household not found"));

        Badge badge = badgeRepository.findById(registerRequestDTO.getBadgeId())
                .orElseThrow(() -> new NotFoundException("Badge not found"));
        user.setBadge(badge);
        user.setHousehold(household);
        user.setCountry(country);
        user.setPassword(passwordEncoder.encode(registerRequestDTO.getPassword()));
        userRepository.save(user);


        return ResponseEntity.ok(new UserDTO(user));

    }

}