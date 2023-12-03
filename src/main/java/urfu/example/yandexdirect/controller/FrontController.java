package urfu.example.yandexdirect.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import urfu.example.yandexdirect.service.ParsingDataService;

@Controller
@RequiredArgsConstructor
@Api(tags = "Контроллер главной страницы")
public class FrontController {

  private final ParsingDataService parsingDataService;

  @GetMapping("/")
  public String mainPage(Model model){
    model.addAttribute("responses", parsingDataService.findAllAds());
    return "markers";
  }
}
