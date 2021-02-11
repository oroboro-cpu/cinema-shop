package cinema.project.dao;

import cinema.project.exception.DataProcessingException;
import cinema.project.model.Order;
import cinema.project.model.User;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDaoImpl implements OrderDao {
    private final SessionFactory sessionFactory;

    public OrderDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Order add(Order order) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(order);
            transaction.commit();
            return order;
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't add order: " + order + " to the DB", ex);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Order> getOrdersHistory(User user) {
        try (Session session = sessionFactory.openSession()) {
            Query<Order> orderQuery = session.createQuery("SELECT DISTINCT o from Order o "
                    + "LEFT JOIN FETCH o.tickets t "
                    + "LEFT JOIN FETCH o.user "
                    + "LEFT JOIN FETCH t.movieSession ms "
                    + "LEFT JOIN FETCH ms.cinemaHall "
                    + "LEFT JOIN FETCH ms.movie "
                    + "WHERE o.user = :user", Order.class)
                    .setParameter("user", user);
            return orderQuery.getResultList();
        } catch (Exception ex) {
            throw new DataProcessingException("Can't get all orders by user: "
                    + user + " from the DB", ex);
        }
    }
}
