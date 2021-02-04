package cinema.project.service;

import cinema.project.model.Order;
import cinema.project.model.ShoppingCart;
import cinema.project.model.User;
import java.util.List;

public interface OrderService {
    Order completeOrder(ShoppingCart shoppingCart);

    List<Order> getOrdersHistory(User user);
}
