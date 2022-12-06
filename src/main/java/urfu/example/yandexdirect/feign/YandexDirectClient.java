package urfu.example.yandexdirect.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import urfu.example.yandexdirect.dto.CampaignsYandexDto;

@FeignClient(name = "yandexSearch", url = "${app.parsing.url}")
public interface YandexDirectClient {

  @PostMapping("/campaigns")
  CampaignsYandexDto searchCampaigns(@RequestHeader("Authorization") String token, @RequestBody String body);

  @PostMapping("/ads")
  CampaignsYandexDto searchAds(@RequestHeader("Authorization") String token, @RequestBody String body);

  @PostMapping("/adextensions")
  CampaignsYandexDto seacrhAdExtensions(@RequestHeader("Authorization") String token, @RequestBody String body);
}
