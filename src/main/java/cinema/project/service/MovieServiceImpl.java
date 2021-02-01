package cinema.project.service;

import cinema.project.dao.MovieDao;
import cinema.project.lib.Inject;
import cinema.project.lib.Service;
import cinema.project.model.Movie;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {
    @Inject
    private MovieDao movieDao;

    public Movie add(Movie movie) {
        return movieDao.add(movie);
    }

    public List<Movie> getAll() {
        return movieDao.getAll();
    }
}
