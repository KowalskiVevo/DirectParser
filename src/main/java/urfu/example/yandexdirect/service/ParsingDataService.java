package urfu.example.yandexdirect.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import urfu.example.yandexdirect.dto.CampaignsYandexDto;
import urfu.example.yandexdirect.dto.ResponseDto;
import urfu.example.yandexdirect.feign.YandexDirectClient;
import urfu.example.yandexdirect.util.ConstantValue;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class ParsingDataService {

  @Value("${app.parsing.token}")
  private String token;

  private final YandexDirectClient yandexDirectClient;

  public List<ResponseDto> findAllAds(){
    CampaignsYandexDto dto = yandexDirectClient.searchAds(token, String.format(
        ConstantValue.BODY_FOR_ADS,
        String.join(",",
            yandexDirectClient.searchCampaigns(token, ConstantValue.BODY_FOR_CAMPAIGNS).getResult().getCampaigns()
                .stream()
                .map(e -> e.getId().toString())
                .toList())));
    return dto.getResult().getAds()
        .stream()
        .filter(ads -> ads.getTextAd() != null)
        .map(ads -> {
          CampaignsYandexDto.Result.Ads.TextAd textAd = ads.getTextAd();
          if (textAd.getText() != null && !textAd.getText().isBlank()) {
            List<LocalDate> dates = ConstantValue.FUNCTION_PARSING.entrySet().stream().map(enrty -> {
              Pattern key = enrty.getKey();
              Matcher matcher = key.matcher(textAd.getText().toLowerCase());
              if (matcher.find()) {
                return enrty.getValue().apply(matcher.group());
              }
              return null;
            }).filter(Objects::nonNull).toList();
            if (!dates.isEmpty()) {
              return ResponseDto.builder()
                  .id(ads.getId())
                  .campaignId(ads.getCampaignId())
                  .ads(ads.getTextAd().getText())
                  .dateEnd(dates.get(0).isAfter(LocalDate.now()) ? dates.get(0) : dates.get(0).plusYears(1L))
                  .build();
            }
          }
          if (textAd.getTitle() != null && !textAd.getTitle().isBlank()) {
            List<LocalDate> dates = ConstantValue.FUNCTION_PARSING.entrySet().stream().map(enrty -> {
              Pattern key = enrty.getKey();
              Matcher matcher = key.matcher(textAd.getTitle().toLowerCase());
              if (matcher.find()) {
                return enrty.getValue().apply(matcher.group());
              }
              return null;
            }).filter(Objects::nonNull).toList();
            if (!dates.isEmpty()) {
              return ResponseDto.builder()
                  .id(ads.getId())
                  .campaignId(ads.getCampaignId())
                  .ads(ads.getTextAd().getTitle())
                  .dateEnd(dates.get(0).isAfter(LocalDate.now()) ? dates.get(0) : dates.get(0).plusYears(1L))
                  .build();
            }
          }
          if (textAd.getTitle2() != null && !textAd.getTitle2().isBlank()) {
            List<LocalDate> dates = ConstantValue.FUNCTION_PARSING.entrySet().stream().map(enrty -> {
              Pattern key = enrty.getKey();
              Matcher matcher = key.matcher(textAd.getTitle2().toLowerCase());
              if (matcher.find()) {
                return enrty.getValue().apply(matcher.group());
              }
              return null;
            }).filter(Objects::nonNull).toList();
            if (!dates.isEmpty()) {
              return ResponseDto.builder()
                  .id(ads.getId())
                  .campaignId(ads.getCampaignId())
                  .ads(ads.getTextAd().getTitle2())
                  .dateEnd(dates.get(0).isAfter(LocalDate.now()) ? dates.get(0) : dates.get(0).plusYears(1L))
                  .build();
            }
          }
          return null;
        }).filter(Objects::nonNull).sorted(Comparator.comparing(ResponseDto::getDateEnd)).toList();
  }
}
