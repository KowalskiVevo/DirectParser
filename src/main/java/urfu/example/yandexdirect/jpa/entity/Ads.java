package urfu.example.yandexdirect.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "ads")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ads {
  @Id
  @Column(name = "ads_id")
  private Long adsId;

  @Column(name = "ads_content")
  private String adsContent;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @Column(name = "campaign_id")
  private Long campaignId;

  @Column(name = "date_end")
  private LocalDate dateEnd;
}
