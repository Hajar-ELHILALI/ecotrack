package EcoTrack.server.security;

import EcoTrack.server.entity.User;
import EcoTrack.server.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> optionalClient = userRepository.findByEmail(email);
        if (optionalClient.isPresent()) {
            return new UserDetailsImpl(optionalClient.get());
        } else {
            throw new UsernameNotFoundException(email);
        }
    }
}
