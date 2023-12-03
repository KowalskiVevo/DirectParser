package urfu.example.yandexdirect.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import urfu.example.yandexdirect.dto.CampaignsYandexDto;
import urfu.example.yandexdirect.dto.ResponseDto;
import urfu.example.yandexdirect.feign.YandexDirectClient;
import urfu.example.yandexdirect.jpa.entity.Ads;
import urfu.example.yandexdirect.jpa.entity.User;
import urfu.example.yandexdirect.jpa.repository.AdsRepository;
import urfu.example.yandexdirect.jpa.repository.UserRepository;
import urfu.example.yandexdirect.util.ConstantValue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class ParsingDataService {

  private final YandexDirectClient yandexDirectClient;
  private final UserRepository userRepository;
  private final AdsRepository adsRepository;


  public List<ResponseDto> findAllAds() {
    return adsRepository.findAll().stream().map(ads -> ResponseDto.builder()
            .id(ads.getAdsId())
            .campaignId(ads.getCampaignId())
            .ads(ads.getAdsContent())
            .dateEnd(ads.getDateEnd())
            .fio(ads.getUser().getFio())
            .build())
        .sorted(Comparator.comparing(ResponseDto::getDateEnd))
        .toList();
  }

  public List<ResponseDto> findAdsByUsers(List<Long> userIds){
    return adsRepository.findByUserIds(userIds).stream().map(ads -> ResponseDto.builder()
            .id(ads.getAdsId())
            .campaignId(ads.getCampaignId())
            .ads(ads.getAdsContent())
            .dateEnd(ads.getDateEnd())
            .fio(ads.getUser().getFio())
            .build())
        .sorted(Comparator.comparing(ResponseDto::getDateEnd))
        .toList();
  }

  public List<ResponseDto> saveAllAds() {
    List<User> users = userRepository.findAll();
    List<ResponseDto> responseDtoList = new ArrayList<>();
    users.stream()
        .map(user -> {
          List<ResponseDto> dtoList = findAdsByUserToken(user);
          dtoList.forEach(responseDto -> {
            if (adsRepository.findById(responseDto.getId()).isEmpty()) {
              adsRepository.saveAndFlush(Ads.builder()
                  .adsId(responseDto.getId())
                  .adsContent(responseDto.getAds())
                  .campaignId(responseDto.getCampaignId())
                  .user(user)
                  .dateEnd(responseDto.getDateEnd())
                  .build());
            }
          });
          return dtoList;
        })
        .toList()
        .forEach(responseDtoList::addAll);
    return responseDtoList;
  }

  private List<ResponseDto> findAdsByUserToken(User user) {
    CampaignsYandexDto dto = yandexDirectClient.searchAds(user.getToken(), String.format(
        ConstantValue.BODY_FOR_ADS,
        String.join(",",
            yandexDirectClient.searchCampaigns(user.getToken(), ConstantValue.BODY_FOR_CAMPAIGNS).getResult().getCampaigns()
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
                  .fio(user.getFio())
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
                  .fio(user.getFio())
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
                  .fio(user.getFio())
                  .build();
            }
          }
          return null;
        }).filter(Objects::nonNull).sorted(Comparator.comparing(ResponseDto::getDateEnd)).toList();
  }
}
