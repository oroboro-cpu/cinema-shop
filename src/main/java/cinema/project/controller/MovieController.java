package cinema.project.controller;

import cinema.project.model.Movie;
import cinema.project.model.dto.request.MovieRequestDto;
import cinema.project.model.dto.response.MovieResponseDto;
import cinema.project.service.MovieService;
import cinema.project.service.mapper.MovieMapper;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieService movieService;
    private final MovieMapper movieMapper;

    @Autowired
    public MovieController(MovieService movieService, MovieMapper movieMapper) {
        this.movieService = movieService;
        this.movieMapper = movieMapper;
    }

    @PostMapping
    public void add(@RequestBody MovieRequestDto movieDto) {
        Movie movie = movieMapper.toEntity(movieDto);
        movieService.add(movie);
    }

    @GetMapping
    public List<MovieResponseDto> getAll() {
        return movieService.getAll().stream()
                .map(movieMapper::toDto)
                .collect(Collectors.toList());
    }
}
