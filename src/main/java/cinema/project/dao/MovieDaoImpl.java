package cinema.project.dao;

import cinema.project.lib.Dao;
import cinema.project.model.Movie;
import cinema.project.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

@Dao
public class MovieDaoImpl implements MovieDao {

    public Movie add(Movie movie) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Long itemId = (Long) session.save(movie);
            transaction.commit();
            movie.setId(itemId);
            return movie;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Can't insert movie entity", e);
        } finally {
            session.close();
        }
    }

    public List<Movie> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Movie> getAllMoviesQuery = session.createQuery("from Movie", Movie.class);
            return getAllMoviesQuery.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can't get all movies from DB");
        }
    }
}
