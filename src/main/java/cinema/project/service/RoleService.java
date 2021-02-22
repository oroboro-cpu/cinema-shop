package cinema.project.service;

import cinema.project.model.Role;
import cinema.project.model.RoleType;

public interface RoleService {
    void add(Role role);

    Role getRoleByName(RoleType roleType);
}
