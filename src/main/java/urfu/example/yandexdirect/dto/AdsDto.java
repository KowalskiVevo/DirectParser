package urfu.example.yandexdirect.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdsDto {
  private Long adsId;
  private String adsContent;
  private Long campaignId;
  private LocalDate dateEnd;
  private Long userId;
}
