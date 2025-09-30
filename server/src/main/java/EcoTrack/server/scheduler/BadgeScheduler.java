package EcoTrack.server.scheduler;

import EcoTrack.server.entity.*;
import EcoTrack.server.repository.*;
import EcoTrack.server.service.implementation.BadgeServiceImpl;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class BadgeScheduler {

    private final BadgeServiceImpl badgeService;

    public BadgeScheduler(BadgeServiceImpl badgeService) {
        this.badgeService = badgeService;
    }

    //Planification : chaque 1er jour du mois à 00:00
    @Scheduled(cron = "0 0 0 1 * ?")
    public void updateBadgesForUsers() {
        badgeService.updateAllUserBadges();
    }
}