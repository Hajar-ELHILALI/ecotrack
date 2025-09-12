package EcoTrack.server.service;

import EcoTrack.server.DTO.ScoreDTO;
import EcoTrack.server.entity.Score;
import EcoTrack.server.entity.UserActivity;

public interface ScoreService extends CrudDTO<ScoreDTO, Long>{
   void calculateAndSaveScore(UserActivity activity);
}
