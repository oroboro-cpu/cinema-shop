package cinema.project.controller;

import cinema.project.model.MovieSession;
import cinema.project.model.dto.request.MovieSessionRequestDto;
import cinema.project.model.dto.response.MovieSessionResponseDto;
import cinema.project.service.MovieSessionService;
import cinema.project.service.mapper.MovieSessionMapper;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie-sessions")
public class MovieSessionController {
    private final MovieSessionService movieSessionService;
    private final MovieSessionMapper movieSessionMapper;

    public MovieSessionController(MovieSessionService movieSessionService,
                                  MovieSessionMapper movieSessionMapper) {
        this.movieSessionService = movieSessionService;
        this.movieSessionMapper = movieSessionMapper;
    }

    @PostMapping
    public void add(@RequestBody MovieSessionRequestDto movieSessionRequestDto) {
        MovieSession movieSession = movieSessionMapper.toEntity(movieSessionRequestDto);
        movieSessionService.add(movieSession);
    }

    @GetMapping("/available")
    public List<MovieSessionResponseDto> findAvailable(@RequestParam Long movieId,
                                                       @RequestParam
                                                       @DateTimeFormat(pattern = "dd.MM.yyyy")
                                                               LocalDate date) {
        return movieSessionService.findAvailableSessions(movieId, date).stream()
                .map(movieSessionMapper::toDto)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public void update(@RequestBody MovieSessionRequestDto movieSessionRequestDto,
                       @PathVariable Long id) {
        MovieSession movieSession = movieSessionMapper.toEntity(movieSessionRequestDto);
        movieSessionService.update(movieSession);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        movieSessionService.remove(movieSessionService.get(id));
    }
}
