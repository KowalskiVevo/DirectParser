package urfu.example.yandexdirect.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import urfu.example.yandexdirect.jpa.entity.Ads;

import java.util.List;

@Repository
public interface AdsRepository extends JpaRepository<Ads, Long> {
  @Query("select a from Ads a where a.user.userId in (:userIds)")
  List<Ads> findByUserIds(List<Long> userIds);
}
