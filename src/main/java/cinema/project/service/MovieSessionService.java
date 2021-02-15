package cinema.project.service;

import cinema.project.model.MovieSession;
import java.time.LocalDate;
import java.util.List;

public interface MovieSessionService {
    List<MovieSession> findAvailableSessions(Long movieId, LocalDate date);

    MovieSession add(MovieSession session);

    void update(MovieSession movieSession);

    void remove(Long id);

    MovieSession get(Long id);
}
