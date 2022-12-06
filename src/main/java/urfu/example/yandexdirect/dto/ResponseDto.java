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
public class ResponseDto {
  private Long id;
  private Long campaignId;
  private String ads;
  private LocalDate dateEnd;
}
