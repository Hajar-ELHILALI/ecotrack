package EcoTrack.server.service;

import EcoTrack.server.DTO.ScoreDTO;
import EcoTrack.server.DTO.StatisticsResponseDTO;
import EcoTrack.server.DTO.statisticDTO;
import EcoTrack.server.entity.Score;
import EcoTrack.server.entity.UserActivity;

import java.util.List;

public interface ScoreService extends CrudDTO<ScoreDTO, Long>{
   void calculateAndSaveScore(UserActivity activity);
   List<statisticDTO> getUserScores(Long userId);
   StatisticsResponseDTO getUserStatistics(Long userId, String period);
}
