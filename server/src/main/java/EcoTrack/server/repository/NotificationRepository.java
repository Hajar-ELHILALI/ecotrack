package EcoTrack.server.repository;

import EcoTrack.server.DTO.NotificationDTO;
import EcoTrack.server.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import EcoTrack.server.entity.Notification;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByUserAndIsReadFalse(User user);
    List<Notification> findByUser(User user);
    NotificationDTO markAsRead(Long notificationId, Principal principal);
    Optional<Notification> findByIdAndUserId(Long notificationId, Long userId);

}