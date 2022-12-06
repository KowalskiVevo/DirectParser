package urfu.example.yandexdirect.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import urfu.example.yandexdirect.dto.ResponseDto;
import urfu.example.yandexdirect.service.ParsingDataService;

import java.util.*;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ParsingDataController {

  private final ParsingDataService parsingDataService;

  @GetMapping("/start")
  public ResponseEntity<List<ResponseDto>> findAllAds() {
    return ResponseEntity.ok(parsingDataService.findAllAds());
  }
}
