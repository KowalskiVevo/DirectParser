package urfu.example.yandexdirect.schedule;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import urfu.example.yandexdirect.service.ParsingDataService;

@Component
@RequiredArgsConstructor
@ConditionalOnProperty(value = "app.parsing.enabled")
public class ParsingDataSchedule {

  private final ParsingDataService parsingDataService;

  @Scheduled(cron = "${app.parsing.cron}")
  public void searchDataForParsing() {
    parsingDataService.saveAllAds();
  }
}
