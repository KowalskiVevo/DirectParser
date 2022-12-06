package urfu.example.yandexdirect.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import urfu.example.yandexdirect.service.ParsingDataService;

@Controller
@RequiredArgsConstructor
public class FrontController {

  private final ParsingDataService parsingDataService;

  @GetMapping("/")
  public String mainPage(Model model){
    model.addAttribute("responses", parsingDataService.findAllAds());
    return "opa";
  }
}
