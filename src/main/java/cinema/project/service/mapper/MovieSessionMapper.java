package cinema.project.service.mapper;

import cinema.project.model.MovieSession;
import cinema.project.model.dto.request.MovieSessionRequestDto;
import cinema.project.model.dto.response.MovieSessionResponseDto;
import cinema.project.service.CinemaHallService;
import cinema.project.service.MovieService;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MovieSessionMapper implements Mapper<MovieSession,
        MovieSessionResponseDto, MovieSessionRequestDto> {
    private final MovieService movieService;
    private final CinemaHallService cinemaHallService;

    @Autowired
    public MovieSessionMapper(MovieService movieService, CinemaHallService cinemaHallService) {
        this.movieService = movieService;
        this.cinemaHallService = cinemaHallService;
    }

    @Override
    public MovieSessionResponseDto toDto(MovieSession movieSession) {
        MovieSessionResponseDto movieSessionResponseDto = new MovieSessionResponseDto();
        movieSessionResponseDto.setId(movieSession.getId());
        movieSessionResponseDto.setMovieId(movieSession.getMovie().getId());
        movieSessionResponseDto.setCinemaHallId(movieSession.getCinemaHall().getId());
        movieSessionResponseDto.setShowTime(movieSession.getShowTime()
                .format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")));
        return movieSessionResponseDto;
    }

    @Override
    public MovieSession toEntity(MovieSessionRequestDto movieSessionRequestDto) {
        MovieSession movieSession = new MovieSession();
        movieSession.setMovie(movieService.get(movieSessionRequestDto.getMovieId()));
        movieSession.setCinemaHall(cinemaHallService.get(movieSessionRequestDto
                .getCinemaHallId()));
        movieSession.setShowTime(LocalDateTime.parse(movieSessionRequestDto.getShowTime(),
                DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")));
        return movieSession;
    }
}
