package cinema.project;

import cinema.project.lib.Injector;
import cinema.project.model.Movie;
import cinema.project.service.MovieService;

public class Main {
    private static final Injector injector = Injector.getInstance("cinema.project");

    public static void main(String[] args) {
        Movie movie = new Movie();
        movie.setTitle("Fast and Furious");
        movie.setDescription("Good movie");
        Movie movie1 = new Movie();
        movie1.setTitle("Pulp Fiction");
        movie1.setDescription("Best Tarantino's movie");
        MovieService movieService = (MovieService) injector.getInstance(MovieService.class);
        movieService.add(movie);
        movieService.add(movie1);

        movieService.getAll().forEach(System.out::println);
    }
}
