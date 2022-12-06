package urfu.example.yandexdirect.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class CampaignsYandexDto {

  private Result result;

  @Data
  public static class Result {
    @JsonProperty("Campaigns")
    List<Campaigns> campaigns;
    @JsonProperty("Ads")
    List<Ads> ads;

    @Data
    public static class Campaigns {
      @JsonProperty("Id")
      private Long id;
      @JsonProperty("Name")
      private String name;
      @JsonProperty("Status")
      private String status;
      @JsonProperty("State")
      private String state;
      @JsonProperty("StatusPayment")
      private String statusPayment;
    }

    @Data
    public static class Ads {
      @JsonProperty("Id")
      private Long id;
      @JsonProperty("CampaignId")
      private Long campaignId;
      @JsonProperty("AdGroupId")
      private Long adGroupId;
      @JsonProperty("Status")
      private String status;
      @JsonProperty("State")
      private String state;
      @JsonProperty("TextAd")
      private TextAd textAd;

      @Data
      public static class TextAd {
        @JsonProperty("Text")
        private String text;
        @JsonProperty("Title")
        private String title;
        @JsonProperty("Title2")
        private String title2;
        @JsonProperty("Href")
        private String href;
      }
    }
  }
}
