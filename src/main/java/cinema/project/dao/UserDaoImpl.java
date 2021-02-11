package cinema.project.dao;

import cinema.project.exception.DataProcessingException;
import cinema.project.model.User;
import java.util.Optional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {
    private final SessionFactory sessionFactory;

    public UserDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public User add(User user) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.persist(user);
            transaction.commit();
            return user;
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't add user: " + user + " to the DB", ex);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try (Session session = sessionFactory.openSession()) {
            Query<User> userQuery = session
                    .createQuery("SELECT u FROM User u WHERE u.email = :email");
            userQuery.setParameter("email", email);
            return userQuery.uniqueResultOptional();
        } catch (Exception ex) {
            throw new DataProcessingException("Can't find user in the DB by the email: "
                    + email, ex);
        }
    }
}
