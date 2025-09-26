package EcoTrack.server.controller;

import EcoTrack.server.DTO.NotificationDTO;
import EcoTrack.server.entity.User;
import EcoTrack.server.repository.UserRepository;
import EcoTrack.server.service.NotificationService;
import EcoTrack.server.service.UserGoalService;
import EcoTrack.server.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;


@RestController
@RequestMapping("/api/notifications")
public class NotificationController {
    private final NotificationService notificationService;
    private final UserService userService;
    private final UserRepository userRepository;

    public NotificationController(NotificationService notificationService, UserService userService,UserRepository userRepository) {
        this.notificationService = notificationService;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotificationDTO> getNotification(@PathVariable Long id) {
        return ResponseEntity.ok(notificationService.findDTOById(id));
    }

    @GetMapping("/")
    public ResponseEntity<List<NotificationDTO>> getAllNotifications() {
        return ResponseEntity.ok(notificationService.findAllDTO());
    }

    @PostMapping("/")
    public ResponseEntity<NotificationDTO> createNotification(@RequestBody NotificationDTO notificationDTO) {
        return ResponseEntity.ok(notificationService.createDTO(notificationDTO));
    }

    @PutMapping("/")
    public ResponseEntity<NotificationDTO> updateNotification(@RequestBody NotificationDTO notificationDTO) {
        return ResponseEntity.ok(notificationService.updateDTO(notificationDTO));
    }

    @DeleteMapping("/{id}")
    public void deleteNotification(@PathVariable Long id) {
        notificationService.deleteDTOById(id);
    }

    @GetMapping("/unread")
    public ResponseEntity<List<NotificationDTO>> getUnreadNotifications(Principal principal) {
        return notificationService.getUnreadNotifications(principal);
    }

    //c pour  Marquer une notif comme lue
    @PutMapping("/{id}/markAsRead")
    public ResponseEntity<NotificationDTO> markNotificationAsRead(
            @PathVariable Long id,
            Principal principal) {
        return ResponseEntity.ok(notificationService.markAsRead(id, principal));
    }

    @GetMapping("/me")
    public ResponseEntity<List<NotificationDTO>> getNotificationsByUser(Principal principal) {
        List<NotificationDTO> notificationDTOS = notificationService.getNotificationsByUser(principal);
        return ResponseEntity.ok(notificationDTOS);
    }
}
