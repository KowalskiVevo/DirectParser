package urfu.example.yandexdirect.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import urfu.example.yandexdirect.dto.AdsDto;
import urfu.example.yandexdirect.jpa.entity.Ads;
import urfu.example.yandexdirect.jpa.repository.UserRepository;

@Component
public class AdsMapper {
  @Autowired
  private static UserRepository repository;
  public static AdsDto toDto(Ads ads){
    return AdsDto.builder()
        .adsId(ads.getAdsId())
        .adsContent(ads.getAdsContent())
        .userId(ads.getUser().getUserId())
        .campaignId(ads.getCampaignId())
        .dateEnd(ads.getDateEnd())
        .build();
  }

  public static Ads toEntity(AdsDto adsDto){
    return Ads.builder()
        .adsId(adsDto.getAdsId())
        .adsContent(adsDto.getAdsContent())
        .user(repository.getById(adsDto.getUserId()))
        .campaignId(adsDto.getCampaignId())
        .dateEnd(adsDto.getDateEnd())
        .build();
  }
}
