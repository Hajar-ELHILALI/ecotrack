package EcoTrack.server.service;

import EcoTrack.server.DTO.ScoreDTO;
import EcoTrack.server.entity.Score;

public interface ScoreService extends CrudDTO<ScoreDTO, Long>, CrudService<Score, Long>{
}
