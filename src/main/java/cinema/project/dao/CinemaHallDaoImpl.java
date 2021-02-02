package cinema.project.dao;

import cinema.project.exception.DataProcessingException;
import cinema.project.lib.Dao;
import cinema.project.model.CinemaHall;
import cinema.project.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Dao
public class CinemaHallDaoImpl implements CinemaHallDao {
    @Override
    public CinemaHall add(CinemaHall cinemaHall) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.persist(cinemaHall);
            transaction.commit();
            return cinemaHall;
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't add cinema hall: "
                    + cinemaHall + " to the DB", ex);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<CinemaHall> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from CinemaHall", CinemaHall.class).getResultList();
        } catch (Exception ex) {
            throw new DataProcessingException("Can't get all cinema halls from DB", ex);
        }
    }
}
