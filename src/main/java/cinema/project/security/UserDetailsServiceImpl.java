package cinema.project.security;

import static org.springframework.security.core.userdetails.User.UserBuilder;
import static org.springframework.security.core.userdetails.User.withUsername;

import cinema.project.model.User;
import cinema.project.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserService userService;

    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException("Can't find user by email: " + email));
        UserBuilder userBuilder = withUsername(email);
        userBuilder.username(user.getEmail());
        userBuilder.password(user.getPassword());
        userBuilder.roles(user.getRoles()
                .stream().map(r -> r.getRoleName().name()).toArray(String[]::new));
        return userBuilder.build();
    }
}
