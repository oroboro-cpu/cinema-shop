package cinema.project.controller;

import cinema.project.model.User;
import cinema.project.model.dto.response.OrderResponseDto;
import cinema.project.service.OrderService;
import cinema.project.service.ShoppingCartService;
import cinema.project.service.UserService;
import cinema.project.service.mapper.OrderMapper;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public List<OrderResponseDto> getHistory(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userService.findByEmail(userDetails.getUsername()).get();
        return orderService.getOrdersHistory(user).stream()
                .map(orderMapper::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/complete")
    public void completeOrder(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userService.findByEmail(userDetails.getUsername()).get();
        orderService.completeOrder(shoppingCartService.getByUser(user));
    }
}
