package cinema.project.dao;

import cinema.project.model.Role;
import cinema.project.model.RoleType;

public interface RoleDao {
    void add(Role role);

    Role getRoleByName(RoleType roleType);
}
