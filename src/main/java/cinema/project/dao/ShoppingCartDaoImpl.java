package cinema.project.dao;

import cinema.project.exception.DataProcessingException;
import cinema.project.model.ShoppingCart;
import cinema.project.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class ShoppingCartDaoImpl implements ShoppingCartDao {
    private final SessionFactory sessionFactory;

    public ShoppingCartDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public ShoppingCart add(ShoppingCart shoppingCart) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(shoppingCart);
            transaction.commit();
            return shoppingCart;
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't add shopping cart: " + shoppingCart
                    + " to the DB", ex);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public ShoppingCart getByUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            Query<ShoppingCart> query =
                    session.createQuery("SELECT s FROM ShoppingCart s"
                            + " LEFT JOIN FETCH s.tickets t"
                            + " LEFT JOIN FETCH t.movieSession m"
                            + " LEFT JOIN FETCH m.movie"
                            + " LEFT JOIN FETCH m.cinemaHall"
                            + " WHERE s.user = :user", ShoppingCart.class);
            query.setParameter("user", user);
            return query.getSingleResult();
        } catch (Exception ex) {
            throw new DataProcessingException("Can't get shopping cart by user: "
                    + user + " from DB", ex);
        }
    }

    @Override
    public void update(ShoppingCart shoppingCart) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(shoppingCart);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't update shopping cart: " + shoppingCart
                    + " in the DB", ex);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
