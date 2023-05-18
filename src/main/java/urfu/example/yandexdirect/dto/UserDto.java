package urfu.example.yandexdirect.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
  private Long userId;
  private String token;
  private String login;
  private String password;
  private String fio;
}
