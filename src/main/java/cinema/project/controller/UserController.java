package cinema.project.controller;

import cinema.project.model.dto.response.UserResponseDto;
import cinema.project.service.UserService;
import cinema.project.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @Autowired
    public UserController(UserService service, UserMapper mapper) {
        this.userService = service;
        this.userMapper = mapper;
    }

    @GetMapping("/by-email")
    public UserResponseDto getByEmail(@RequestParam String email) {
        return userService.findByEmail(email).map(userMapper::toDto).get();
    }
}
