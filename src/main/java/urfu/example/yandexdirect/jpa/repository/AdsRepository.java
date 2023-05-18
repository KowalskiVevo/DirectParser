package urfu.example.yandexdirect.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import urfu.example.yandexdirect.jpa.entity.Ads;

@Repository
public interface AdsRepository extends JpaRepository<Ads, Long> {
}
