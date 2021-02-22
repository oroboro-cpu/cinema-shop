package cinema.project.controller;

import java.util.List;
import javax.annotation.PostConstruct;
import cinema.project.model.Role;
import cinema.project.model.RoleType;
import cinema.project.model.User;
import cinema.project.service.RoleService;
import cinema.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataInjectionController {
    private final RoleService roleService;
    private final UserService userService;

    @Autowired
    public DataInjectionController(RoleService roleService,
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
        userAdmin.setRoles(List.of(roleAdmin));
        userService.add(userAdmin);
    }
}