package cinema.project.service.mapper;

public interface Mapper<S, T, P> {
    T toDto(S entity);

    S toEntity(P requestDto);
}
