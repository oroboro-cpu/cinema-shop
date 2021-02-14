package cinema.project.service;

import cinema.project.exception.AuthenticationException;
import cinema.project.model.User;
import cinema.project.util.HashUtil;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final ShoppingCartService shoppingCartService;
    private final UserService userService;

    @Autowired
    public AuthenticationServiceImpl(ShoppingCartService shoppingCartService,
                                     UserService userService) {
        this.shoppingCartService = shoppingCartService;
        this.userService = userService;
    }

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
        userService.add(user);
        shoppingCartService.registerNewShoppingCart(user);
        return user;
    }
}
