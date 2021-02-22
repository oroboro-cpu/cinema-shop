package cinema.project.service;

import cinema.project.dao.RoleDao;
import cinema.project.model.Role;
import cinema.project.model.RoleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    private RoleDao roleDao;

    @Autowired
    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public void add(Role role) {
        roleDao.add(role);
    }

    @Override
    public Role getRoleByName(RoleType roleType) {
        return roleDao.getRoleByName(roleType).get();
    }
}
