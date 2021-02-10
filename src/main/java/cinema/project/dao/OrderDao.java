package cinema.project.dao;

import cinema.project.model.Order;
import cinema.project.model.User;
import java.util.List;

public interface OrderDao {
    Order add(Order order);

    List<Order> getOrdersHistory(User user);
}
