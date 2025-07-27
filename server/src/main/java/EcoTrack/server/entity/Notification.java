package EcoTrack.server.entity;

import EcoTrack.server.DTO.NotificationDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "notifications")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    private String content;

    private boolean isRead;

    private String type;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Notification(NotificationDTO notificationDTO) {
        setId(notificationDTO.getId());
        setDate(notificationDTO.getDate());
        setRead(notificationDTO.isRead());
        setType(notificationDTO.getType());
        setContent(notificationDTO.getContent());
    }
}
