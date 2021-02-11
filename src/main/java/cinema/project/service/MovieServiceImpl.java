package cinema.project.service;

import cinema.project.dao.MovieDao;
import cinema.project.model.Movie;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieServiceImpl implements MovieService {
    private final MovieDao movieDao;

    @Autowired
    public MovieServiceImpl(MovieDao movieDao) {
        this.movieDao = movieDao;
    }

    public Movie add(Movie movie) {
        return movieDao.add(movie);
    }

    public List<Movie> getAll() {
        return movieDao.getAll();
    }
}
