package cinema.project.dao;

import cinema.project.model.Role;
import cinema.project.model.RoleType;
import java.util.Optional;

public interface RoleDao {
    void add(Role role);

    Optional<Role> getRoleByName(RoleType roleType);
}
