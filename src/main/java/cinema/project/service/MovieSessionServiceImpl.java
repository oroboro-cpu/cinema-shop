package cinema.project.service;

import cinema.project.dao.MovieSessionDao;
import cinema.project.model.MovieSession;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieSessionServiceImpl implements MovieSessionService {
    private final MovieSessionDao movieSessionDao;

    @Autowired
    public MovieSessionServiceImpl(MovieSessionDao movieSessionDao) {
        this.movieSessionDao = movieSessionDao;
    }

    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) {
        return movieSessionDao.findAvailableSessions(movieId, date);
    }

    @Override
    public MovieSession add(MovieSession session) {
        return movieSessionDao.add(session);
    }

    @Override
    public void update(MovieSession movieSession) {
        movieSessionDao.update(movieSession);
    }

    @Override
    public void remove(Long id) {
        movieSessionDao.remove(id);
    }

    @Override
    public MovieSession get(Long id) {
        return movieSessionDao.get(id).get();
    }
}
