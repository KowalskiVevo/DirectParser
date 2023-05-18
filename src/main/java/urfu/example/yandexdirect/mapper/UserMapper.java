package urfu.example.yandexdirect.mapper;

import org.springframework.stereotype.Component;
import urfu.example.yandexdirect.dto.UserDto;
import urfu.example.yandexdirect.jpa.entity.User;

@Component
public class UserMapper {
  public static UserDto toDto(User user){
    return UserDto.builder()
        .userId(user.getUserId())
        .token(user.getToken())
        .login(user.getLogin())
        .password(user.getPassword())
        .fio(user.getFio())
        .build();
  }

  public static User toEntity(UserDto userDto){
    return User.builder()
        .userId(userDto.getUserId())
        .token(userDto.getToken())
        .login(userDto.getLogin())
        .password(userDto.getPassword())
        .fio(userDto.getFio())
        .build();
  }
}
