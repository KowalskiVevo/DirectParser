package urfu.example.yandexdirect.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ParsingDataSchedule {

  @Scheduled(cron = "${app.parsing.cron}")
  public void searchDataForParsing() {

  }
}
