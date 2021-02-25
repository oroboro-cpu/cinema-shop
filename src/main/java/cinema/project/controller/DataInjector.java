package cinema.project.controller;

import cinema.project.model.Role;
import cinema.project.model.RoleType;
import cinema.project.model.User;
import cinema.project.service.RoleService;
import cinema.project.service.UserService;
import java.util.Set;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class DataInjector {
    private final RoleService roleService;
    private final UserService userService;
    private final Environment environment;

    @Autowired
    public DataInjector(RoleService roleService,
                        UserService userService, Environment environment) {
        this.roleService = roleService;
        this.userService = userService;
        this.environment = environment;
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
        userAdmin.setEmail(environment.getProperty("admin.email"));
        userAdmin.setPassword("admin.password");
        userAdmin.setRoles(Set.of(roleAdmin));
        userService.add(userAdmin);
    }
}
