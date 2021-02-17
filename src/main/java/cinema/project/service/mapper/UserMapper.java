package cinema.project.service.mapper;

import cinema.project.model.User;
import cinema.project.model.dto.request.UserRequestDto;
import cinema.project.model.dto.response.UserResponseDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements Mapper<User, UserResponseDto, UserRequestDto> {
    @Override
    public UserResponseDto toDto(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(user.getId());
        userResponseDto.setEmail(user.getEmail());
        return userResponseDto;
    }

    @Override
    public User toEntity(UserRequestDto userRequestDto) {
        User user = new User();
        user.setEmail(userRequestDto.getEmail());
        user.setPassword(userRequestDto.getPassword());
        return user;
    }
}
