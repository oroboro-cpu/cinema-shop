package cinema.project;

import cinema.project.exception.AuthenticationException;
import cinema.project.lib.Injector;
import cinema.project.model.CinemaHall;
import cinema.project.model.Movie;
import cinema.project.model.MovieSession;
import cinema.project.model.Order;
import cinema.project.model.User;
import cinema.project.service.AuthenticationService;
import cinema.project.service.CinemaHallService;
import cinema.project.service.MovieService;
import cinema.project.service.MovieSessionService;
import cinema.project.service.OrderService;
import cinema.project.service.ShoppingCartService;
import java.time.LocalDateTime;
import java.util.List;

public class Main {
    private static final Injector injector = Injector.getInstance("cinema.project");

    public static void main(String[] args) throws AuthenticationException {
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

        AuthenticationService authenticationService
                = (AuthenticationService) injector.getInstance(AuthenticationService.class);
        String email1 = "email01@gmail.com";
        String password1 = "java01";
        User user1 = authenticationService.register(email1, password1);
        String email2 = "email02@gmail.com";
        String password2 = "java02";
        System.out.println(authenticationService.login(email1, password1));
        User user2 = authenticationService.register(email2, password2);
        System.out.println(authenticationService.login(email2, password2));

        ShoppingCartService shoppingCartService =
                (ShoppingCartService) injector.getInstance(ShoppingCartService.class);
        shoppingCartService.addSession(movieSession, user1);
        shoppingCartService.addSession(movieSession, user2);
        System.out.println(shoppingCartService.getByUser(user1));
        System.out.println(shoppingCartService.getByUser(user2));
        shoppingCartService.clear(shoppingCartService.getByUser(user1));
        System.out.println(shoppingCartService.getByUser(user1));

        OrderService orderService = (OrderService)
                injector.getInstance(OrderService.class);
        Order order1 = orderService.completeOrder(shoppingCartService.getByUser(user1));
        System.out.println(order1);
        Order order2 = orderService.completeOrder(shoppingCartService.getByUser(user2));
        System.out.println(order2);
        List<Order> ordersHistory1 = orderService.getOrdersHistory(user1);
        ordersHistory1.forEach(System.out::println);
        List<Order> ordersHistory2 = orderService.getOrdersHistory(user2);
        ordersHistory2.forEach(System.out::println);
    }
}
