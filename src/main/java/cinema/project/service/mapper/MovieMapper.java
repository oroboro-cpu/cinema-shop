package cinema.project.service.mapper;

import cinema.project.model.Movie;
import cinema.project.model.dto.request.MovieRequestDto;
import cinema.project.model.dto.response.MovieResponseDto;
import org.springframework.stereotype.Component;

@Component
public class MovieMapper implements Mapper<Movie, MovieResponseDto, MovieRequestDto> {
    @Override
    public MovieResponseDto toDto(Movie movie) {
        MovieResponseDto movieResponseDto = new MovieResponseDto();
        movieResponseDto.setId(movie.getId());
        movieResponseDto.setDescription(movie.getDescription());
        movieResponseDto.setTitle(movie.getTitle());
        return movieResponseDto;
    }

    @Override
    public Movie toEntity(MovieRequestDto movieRequestDto) {
        Movie movie = new Movie();
        movie.setTitle(movieRequestDto.getTitle());
        movie.setDescription(movieRequestDto.getDescription());
        return movie;
    }
}
