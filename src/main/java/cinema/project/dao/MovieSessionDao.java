package cinema.project.dao;

import cinema.project.model.MovieSession;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MovieSessionDao {
    List<MovieSession> findAvailableSessions(Long movieId, LocalDate date);

    MovieSession add(MovieSession session);

    void update(MovieSession movieSession);

    void remove(Long id);

    Optional<MovieSession> get(Long id);
}
