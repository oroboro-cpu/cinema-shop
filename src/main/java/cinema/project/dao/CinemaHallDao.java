package cinema.project.dao;

import cinema.project.model.CinemaHall;
import java.util.List;

public interface CinemaHallDao {
    public CinemaHall add(CinemaHall cinemaHall);

    public List<CinemaHall> getAll();
}
