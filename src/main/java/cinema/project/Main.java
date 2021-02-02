package cinema.project;

import cinema.project.lib.Injector;
import cinema.project.model.CinemaHall;
import cinema.project.model.Movie;
import cinema.project.model.MovieSession;
import cinema.project.service.CinemaHallService;
import cinema.project.service.MovieService;
import cinema.project.service.MovieSessionService;
import java.time.LocalDateTime;

public class Main {
    private static final Injector injector = Injector.getInstance("cinema.project");

    public static void main(String[] args) {
        Movie movie1 = new Movie();
        movie1.setTitle("Fast and Furious");
        movie1.setDescription("Good movie");
        Movie movie2 = new Movie();
        movie2.setTitle("Pulp Fiction");
        movie2.setDescription("Best Tarantino's movie");
        MovieService movieService = (MovieService) injector.getInstance(MovieService.class);
        movieService.add(movie1);
        movieService.add(movie2);
        movieService.getAll().forEach(System.out::println);

        CinemaHall cinemaHall1 = new CinemaHall();
        cinemaHall1.setCapacity(200);
        cinemaHall1.setDescription("RED Hall 3D");
        CinemaHall cinemaHall2 = new CinemaHall();
        cinemaHall2.setCapacity(30);
        cinemaHall2.setDescription("BLUE Hall 5D");
        CinemaHallService cinemaHallService =
                (CinemaHallService) injector.getInstance(CinemaHallService.class);
        cinemaHallService.add(cinemaHall1);
        cinemaHallService.add(cinemaHall2);
        cinemaHallService.getAll().forEach(System.out::println);

        MovieSession movieSession = new MovieSession();
        movieSession.setMovie(movie1);
        movieSession.setCinemaHall(cinemaHall1);
        LocalDateTime time = LocalDateTime.of(2021, 2, 1, 18, 45);
        movieSession.setShowTime(time);
        MovieSessionService movieSessionService =
                (MovieSessionService) injector.getInstance(MovieSessionService.class);
        movieSessionService.add(movieSession);
        System.out.println(movieSessionService
                .findAvailableSessions(movie1.getId(), time.toLocalDate()));
    }
}
