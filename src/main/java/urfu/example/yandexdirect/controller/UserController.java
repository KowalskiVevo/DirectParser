package urfu.example.yandexdirect.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import urfu.example.yandexdirect.dto.UserDto;
import urfu.example.yandexdirect.service.UserService;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = "/api/user")
public class UserController {

  private final UserService userService;

  @PostMapping("/save")
  public ResponseEntity<UserDto> saveUser(@RequestBody UserDto userDto) {
    return ResponseEntity.ok(userService.saveUser(userDto));
  }

  @DeleteMapping("/delete")
  public ResponseEntity<UserDto> deleteUser(@RequestBody UserDto userDto) {
    return ResponseEntity.ok(userService.deleteUser(userDto));
  }
}
