package urfu.example.yandexdirect.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import urfu.example.yandexdirect.dto.UserDto;
import urfu.example.yandexdirect.jpa.repository.UserRepository;
import urfu.example.yandexdirect.mapper.UserMapper;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  public UserDto saveUser(UserDto userDto){
    try{
      return UserMapper.toDto(userRepository.saveAndFlush(UserMapper.toEntity(userDto)));
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public UserDto deleteUser(UserDto userDto){
    try{
      userRepository.delete(UserMapper.toEntity(userDto));
      return userDto;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
