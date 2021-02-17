package cinema.project.service.mapper;

import cinema.project.model.Order;
import cinema.project.model.Ticket;
import cinema.project.model.dto.response.OrderResponseDto;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    private static final String DATE_PATTERN = "dd.MM.yyyy HH:mm";

    public OrderResponseDto toDto(Order order) {
        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setId(order.getId());
        orderResponseDto.setOrderDate(order.getOrderDate()
                .format(DateTimeFormatter.ofPattern(DATE_PATTERN)));
        orderResponseDto.setTicketsId(order.getTickets()
                .stream()
                .map(Ticket::getId)
                .collect(Collectors.toList()));
        orderResponseDto.setUserId(order.getUser().getId());
        return orderResponseDto;
    }
}
