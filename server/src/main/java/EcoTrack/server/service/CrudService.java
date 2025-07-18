package EcoTrack.server.service;

import java.util.List;

public interface CrudService <Entity, ID>{
    Entity create(Entity entity);
    Entity update(Entity entity);
    void deleteById(ID id);
    Entity findById(ID id);
    List<Entity> findAll();
}
