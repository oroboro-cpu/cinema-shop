package cinema.project.dao;

import cinema.project.model.Movie;
import java.util.List;

public interface MovieDao {
    Movie add(Movie movie);

    List<Movie> getAll();
}
