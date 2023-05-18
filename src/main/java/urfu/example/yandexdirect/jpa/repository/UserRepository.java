package urfu.example.yandexdirect.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import urfu.example.yandexdirect.jpa.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
