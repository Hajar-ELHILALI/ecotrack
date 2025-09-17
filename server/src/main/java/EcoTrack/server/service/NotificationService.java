package EcoTrack.server.service;

import EcoTrack.server.DTO.NotificationDTO;
import EcoTrack.server.entity.Notification;
import org.springframework.http.ResponseEntity;

import java.security.Principal;
import java.util.List;

public interface NotificationService extends CrudDTO<NotificationDTO, Long>{
    ResponseEntity<List<NotificationDTO>> getUnreadNotifications(Principal principal);
}

