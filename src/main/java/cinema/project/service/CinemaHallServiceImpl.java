package cinema.project.service;

import cinema.project.dao.CinemaHallDao;
import cinema.project.lib.Inject;
import cinema.project.lib.Service;
import cinema.project.model.CinemaHall;
import java.util.List;

@Service
public class CinemaHallServiceImpl implements CinemaHallService {
    @Inject
    private CinemaHallDao cinemaHallDao;

    @Override
    public CinemaHall add(CinemaHall cinemaHall) {
        return cinemaHallDao.add(cinemaHall);
    }

    @Override
    public List<CinemaHall> getAll() {
        return cinemaHallDao.getAll();
    }
}
