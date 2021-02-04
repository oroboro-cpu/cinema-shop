package cinema.project.service;

import cinema.project.exception.AuthenticationException;
import cinema.project.lib.Inject;
import cinema.project.lib.Service;
import cinema.project.model.User;
import cinema.project.util.HashUtil;
import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    private ShoppingCartService shoppingCartService;
    @Inject
    private UserService userService;

    @Override
    public User login(String email, String password) throws AuthenticationException {
        Optional<User> user = userService.findByEmail(email);
        if (user.isPresent()) {
            String hashPassword = HashUtil.hashPassword(password, user.get().getSalt());
            if (user.get().getPassword().equals(hashPassword)) {
                return user.get();
            }
        }
        throw new AuthenticationException("Wrong login or password!");
    }

    @Override
    public User register(String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        User user1 = userService.add(user);
        shoppingCartService.registerNewShoppingCart(user1);
        return user1;
    }
}
