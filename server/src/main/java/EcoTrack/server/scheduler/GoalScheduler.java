package EcoTrack.server.scheduler;

import EcoTrack.server.entity.*;
import EcoTrack.server.repository.*;
import EcoTrack.server.service.UserGoalService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class GoalScheduler {

    private final UserGoalService userGoalService;

    public GoalScheduler(UserGoalService userGoalService) {
        this.userGoalService = userGoalService;
    }

    //Planification : chaque jour à minuit
    @Scheduled(cron = "0 0 0 * * ?")
    public void checkGoalsAndNotify() {
        userGoalService.checkGoalsAndNotify();
    }
}

