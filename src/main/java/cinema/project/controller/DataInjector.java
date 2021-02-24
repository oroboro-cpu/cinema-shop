package cinema.project.controller;

import cinema.project.model.Role;
import cinema.project.model.RoleType;
import cinema.project.model.User;
import cinema.project.service.RoleService;
import cinema.project.service.UserService;
import java.util.Set;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataInjector {
    private final RoleService roleService;
    private final UserService userService;

    @Autowired
    public DataInjector(RoleService roleService,
                        UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @PostConstruct
    public void inject() {
        Role roleUser = new Role();
        roleUser.setRoleName(RoleType.USER);
        Role roleAdmin = new Role();
        roleAdmin.setRoleName(RoleType.ADMIN);
        roleService.add(roleUser);
        roleService.add(roleAdmin);
        User userAdmin = new User();
        userAdmin.setEmail("yaroslav@gmail.com");
        userAdmin.setPassword("12345");
        userAdmin.setRoles(Set.of(roleAdmin));
        userService.add(userAdmin);
    }
}
