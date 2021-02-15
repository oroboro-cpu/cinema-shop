package cinema.project.service.mapper;

public interface Mapper<S, T, P> {
    T toDto(S t);

    S toEntity(P u);
}
