package cinema.project.service;

import cinema.project.model.MovieSession;
import java.time.LocalDate;
import java.util.List;

public interface MovieSessionService {
    List<MovieSession> findAvailableSessions(Long movieId, LocalDate date);

    MovieSession add(MovieSession session);

    MovieSession update(MovieSession movieSession);

    boolean remove(MovieSession movieSession);

    MovieSession get(Long id);
}
