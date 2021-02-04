package cinema.project.service;

import cinema.project.model.MovieSession;
import cinema.project.model.ShoppingCart;
import cinema.project.model.User;

public interface ShoppingCartService {
    void addSession(MovieSession movieSession, User user);

    ShoppingCart getByUser(User user);

    void registerNewShoppingCart(User user);

    void clear(ShoppingCart shoppingCart);
}
