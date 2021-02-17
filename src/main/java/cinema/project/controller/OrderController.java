package cinema.project.controller;

import cinema.project.model.dto.response.OrderResponseDto;
import cinema.project.service.OrderService;
import cinema.project.service.ShoppingCartService;
import cinema.project.service.UserService;
import cinema.project.service.mapper.OrderMapper;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;
    private final ShoppingCartService shoppingCartService;
    private final OrderMapper orderMapper;

    @Autowired
    public OrderController(OrderService service, UserService userService,
                           ShoppingCartService shoppingCartService, OrderMapper mapper) {
        this.orderService = service;
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
        this.orderMapper = mapper;
    }

    @GetMapping
    public List<OrderResponseDto> getHistory(@RequestParam Long userId) {
        return orderService.getOrdersHistory(userService.get(userId)).stream()
                .map(orderMapper::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/complete")
    public OrderResponseDto completeOrder(@RequestParam Long userId) {
        return orderMapper.toDto(orderService.completeOrder(shoppingCartService
                .getByUser(userService.get(userId))));
    }
}
