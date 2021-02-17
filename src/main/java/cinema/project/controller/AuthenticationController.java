package cinema.project.controller;

import cinema.project.model.dto.request.UserRequestDto;
import cinema.project.service.AuthenticationService;
import cinema.project.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class AuthenticationController {
    private final AuthenticationService service;
    private final UserMapper userMapper;

    @Autowired
    public AuthenticationController(AuthenticationService service,
                                    UserMapper userMapper) {
        this.service = service;
        this.userMapper = userMapper;
    }

    @PostMapping("/register")
    public void register(@RequestBody UserRequestDto dto) {
        service.register(dto.getEmail(), dto.getPassword());
    }
}
