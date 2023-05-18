package urfu.example.yandexdirect.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "user")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  @Column(name = "user_id")
  private Long userId;

  private String token;
  private String login;
  private String password;
  private String fio;
}
