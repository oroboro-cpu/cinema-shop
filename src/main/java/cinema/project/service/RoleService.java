package cinema.project.service;

import cinema.project.model.Role;

public interface RoleService {
    void add(Role role);

    Role getRoleByName(String roleName);
}
