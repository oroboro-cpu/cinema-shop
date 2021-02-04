package cinema.project.service;

import cinema.project.dao.ShoppingCartDao;
import cinema.project.dao.TicketDao;
import cinema.project.lib.Inject;
import cinema.project.lib.Service;
import cinema.project.model.MovieSession;
import cinema.project.model.ShoppingCart;
import cinema.project.model.Ticket;
import cinema.project.model.User;
import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Inject
    private ShoppingCartDao shoppingCartDao;
    @Inject
    private TicketDao ticketDao;

    @Override
    public void addSession(MovieSession movieSession, User user) {
        Ticket ticket = new Ticket();
        ticket.setUser(user);
        ticket.setMovieSession(movieSession);
        ShoppingCart shoppingCartByUser = shoppingCartDao.getByUser(user);
        List<Ticket> tickets = shoppingCartByUser.getTickets();
        ticketDao.add(ticket);
        tickets.add(ticket);
        shoppingCartDao.update(shoppingCartByUser);
    }

    @Override
    public ShoppingCart getByUser(User user) {
        return shoppingCartDao.getByUser(user);
    }

    @Override
    public void registerNewShoppingCart(User user) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(user);
        shoppingCartDao.add(shoppingCart);
    }

    @Override
    public void clear(ShoppingCart shoppingCart) {
        shoppingCart.getTickets().clear();
        shoppingCartDao.update(shoppingCart);
    }
}
