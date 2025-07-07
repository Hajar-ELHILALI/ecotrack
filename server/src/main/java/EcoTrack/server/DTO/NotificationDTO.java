package EcoTrack.server.DTO;

import EcoTrack.server.entity.Notification;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDTO {
    private Long id;
    private LocalDate date;
    private String content;
    private boolean isRead;
    private String type;

    @NotNull
    private Long  userId;

    public NotificationDTO(Notification notification) {
        setId(notification.getId());
        setUserId(notification.getUser().getId());
        setDate(notification.getDate());
        setContent(notification.getContent());
        setRead(notification.isRead());
        setType(notification.getType());
    }
}
