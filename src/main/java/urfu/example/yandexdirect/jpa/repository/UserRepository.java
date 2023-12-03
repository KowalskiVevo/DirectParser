package urfu.example.yandexdirect.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import urfu.example.yandexdirect.jpa.entity.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
  @Query("select u from User u where u.userId in (:userIds)")
  List<User> findByUserId(List<Long> userIds);
}
