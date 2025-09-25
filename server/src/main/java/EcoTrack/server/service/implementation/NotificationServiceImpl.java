package EcoTrack.server.service.implementation;

import EcoTrack.server.DTO.NotificationDTO;
import EcoTrack.server.entity.Notification;
import EcoTrack.server.entity.User;
import EcoTrack.server.exception.NotFoundException;
import EcoTrack.server.repository.NotificationRepository;
import EcoTrack.server.repository.UserRepository;
import EcoTrack.server.service.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;
    public NotificationServiceImpl(NotificationRepository notificationRepository, UserRepository userRepository) {
        this.notificationRepository = notificationRepository;
        this.userRepository = userRepository;
    }

    @Override
    public NotificationDTO findDTOById(Long id) {
        Optional<Notification> notification = notificationRepository.findById(id);
        return notification.map(NotificationDTO::new)
                .orElseThrow(() -> new NotFoundException("Notification not found with : " + id));
    }

    @Override
    public List<NotificationDTO> findAllDTO() {
        return notificationRepository.findAll().stream()
                .map(NotificationDTO::new).toList();
    }

    @Override
    public void deleteDTOById(Long id) {
        notificationRepository.deleteById(id);
    }

    @Override
    public NotificationDTO createDTO(NotificationDTO notificationDTO) {
        Notification notification = new Notification(notificationDTO);
        User user = userRepository.findById(notificationDTO.getUserId())
                .orElseThrow(() -> new NotFoundException("User not found with : " + notificationDTO.getUserId()));

        notification.setUser(user);

        return new NotificationDTO(notificationRepository.save(notification));
    }

    @Override
    public NotificationDTO updateDTO(NotificationDTO notificationDTO) {
        Notification notification = notificationRepository.findById(notificationDTO.getId())
                .orElseThrow(() -> new NotFoundException("Notification not found with : " + notificationDTO.getId()));


        User user = userRepository.findById(notificationDTO.getUserId())
                        .orElseThrow(() -> new NotFoundException("User not found with : " + notificationDTO.getUserId()));
        notification.setUser(user);

        notification.setRead(notificationDTO.isRead());
        notification.setType(notificationDTO.getType());
        notification.setDate(notificationDTO.getDate());
        notification.setContent(notificationDTO.getContent());

        return new NotificationDTO(notificationRepository.save(notification));
    }

    @Override
    public ResponseEntity<List<NotificationDTO>> getUnreadNotifications(Principal principal) {
        User user = userRepository.findByEmail(principal.getName())
                .orElseThrow(() -> new NotFoundException("User not found"));

        List<Notification> notifications = notificationRepository.findByUserAndIsReadFalse(user);

        List<NotificationDTO> notificationDTOs = notifications.stream()
                .map(NotificationDTO::new)
                .toList();

        return ResponseEntity.ok(notificationDTOs);
    }

    @Override
    public List<NotificationDTO> getNotificationsByUser(Principal principal){
        User user = userRepository.findByEmail(principal.getName())
                .orElseThrow(() -> new NotFoundException("User not found"));
        List<Notification> notifications = notificationRepository.findByUser(user);

        List<NotificationDTO> notificationDTOs = notifications.stream()
                .map(NotificationDTO::new)
                .toList();
        return notificationDTOs;
    }
}
