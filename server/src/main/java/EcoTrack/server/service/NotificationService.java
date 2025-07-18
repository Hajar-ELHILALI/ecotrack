package EcoTrack.server.service;

import EcoTrack.server.DTO.NotificationDTO;
import EcoTrack.server.entity.Notification;

public interface NotificationService extends CrudDTO<NotificationDTO, Long>, CrudService<Notification, Long>{
}
