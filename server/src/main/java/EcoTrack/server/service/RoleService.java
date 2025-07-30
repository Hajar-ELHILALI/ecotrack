package EcoTrack.server.service;

import EcoTrack.server.entity.Role;

import java.util.List;

public interface RoleService {
    Role create(Role role);
    Role update(Role role);
    void deleteById(Long id);
    Role findById(Long id);
    List<Role> findAll();
}
