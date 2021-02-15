package cinema.project.service;

import cinema.project.model.Movie;
import java.util.List;

public interface MovieService {
    Movie add(Movie movie);

    List<Movie> getAll();

    Movie get(Long id);
}
