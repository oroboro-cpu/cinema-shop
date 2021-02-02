package cinema.project.dao;

import cinema.project.exception.DataProcessingException;
import cinema.project.lib.Dao;
import cinema.project.model.MovieSession;
import cinema.project.util.HibernateUtil;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

@Dao
public class MovieSessionDaoImpl implements MovieSessionDao {
    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<MovieSession> movieSessionQuery
                    = session.createQuery("SELECT m FROM MovieSession m "
                            + "LEFT JOIN FETCH m.cinemaHall LEFT JOIN FETCH m.movie"
                            + " WHERE m.movie.id = :movie_id "
                            + "AND DATE_FORMAT(m.showTime, '%Y-%m-%d') = :date ",
                    MovieSession.class);
            movieSessionQuery.setParameter("movie_id", movieId);
            movieSessionQuery.setParameter("date",
                    DateTimeFormatter.ofPattern("yyyy-MM-dd").format(date));
            return movieSessionQuery.getResultList();
        } catch (Exception ex) {
            throw new DataProcessingException("Can't find movie sessions by id: "
                    + movieId + " and date: " + date.toString() + " in the DB", ex);
        }
    }

    @Override
    public MovieSession add(MovieSession movieSession) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.persist(movieSession);
            transaction.commit();
            return movieSession;
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't add movie session: " + movieSession, ex);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
