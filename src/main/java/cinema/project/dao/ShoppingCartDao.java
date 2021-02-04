package cinema.project.dao;

import cinema.project.model.ShoppingCart;
import cinema.project.model.User;

public interface ShoppingCartDao {
    ShoppingCart add(ShoppingCart shoppingCart);

    ShoppingCart getByUser(User user);

    void update(ShoppingCart shoppingCart);
}
