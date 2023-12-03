package urfu.example.yandexdirect.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import urfu.example.yandexdirect.dto.ResponseDto;
import urfu.example.yandexdirect.service.ParsingDataService;

import java.util.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = "/api")
@Api(tags = "Контроллер управления акциями")
public class ParsingDataController {

  private final ParsingDataService parsingDataService;

  @GetMapping("/getAds")
  @ApiOperation(value = "Получить распарсенные акции")
  public ResponseEntity<List<ResponseDto>> findAllAds(@RequestParam(required = false)
                                                      @Param("Массив ID юзеров") List<Long> userIds) {
    if (CollectionUtils.isEmpty(userIds)) {
      return ResponseEntity.ok(parsingDataService.findAllAds());
    }
    return ResponseEntity.ok(parsingDataService.findAdsByUsers(userIds));
  }

  @GetMapping("/parsingAds")
  @ApiOperation(value = "Запуск парсинга данных")
  public ResponseEntity<List<ResponseDto>> saveAllAds() {
    return ResponseEntity.ok(parsingDataService.saveAllAds());
  }
}
