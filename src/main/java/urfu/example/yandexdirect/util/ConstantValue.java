package urfu.example.yandexdirect.util;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.*;
import java.util.function.Function;
import java.util.regex.Pattern;

@Component
public class ConstantValue {

  public static String BODY_FOR_CAMPAIGNS =
      "{\"method\": \"get\",\"params\": {\"FieldNames\": [\"Id\" , \"Name\", \"State\" , \"Status\" , \"StatusPayment\"]}}";
  public static String BODY_FOR_ADS = "{\n" +
      "    \"method\": \"get\",\n" +
      "    \"params\":{\n" +
      "        \"SelectionCriteria\":{\n" +
      "            \"CampaignIds\": [%s]\n" +
      "        },\n" +
      "        \"FieldNames\": [\"AdGroupId\" , \"CampaignId\" , \"Id\" , \"State\" , \"Status\"],\n" +
      "        \"TextAdFieldNames\": [\"Href\", \"Text\" , \"Title\" , \"Title2\"]\n" +
      "    }\n" +
      "}";

  public List<String> SORTED_MONTHS =
      Arrays.asList("(январ[яь])", "(феврал[яь])", "(март[а]?)", "(апрел[яь])", "(ма[яй])", "(июн[яь])",
          "(июл[яь])", "(август[а]?)", "(сентябр[яь])", "(октябр[яь])", "(ноябр[яь])", "(декабр[яь])");

  private String pattern =
      "(январ[яь]|феврал[яь]|март[а]?|апрел[яь]|ма[яй]|июн[яь]|июл[яь]|август[а]?|сентябр[яь]|октябр[яь]|ноябр[яь]|декабр[яь])";

  public static Map<Pattern, Function<String, LocalDate>> FUNCTION_PARSING = new HashMap<>();

  @PostConstruct
  private void initFuncParsing() {
    FUNCTION_PARSING.put(
        Pattern.compile("(весь|до конца) " + pattern),
        (String str) -> YearMonth.of(LocalDate.now().getYear(),
            SORTED_MONTHS.indexOf(SORTED_MONTHS.stream()
                .filter(month -> str.replaceAll("(весь|до конца) ", "").matches(month)).toList().get(0)) + 1
        ).atEndOfMonth());
    FUNCTION_PARSING.put(
        Pattern.compile("(до \\d{2}" + pattern + ")"),
        (String str) -> LocalDate.of(
            LocalDate.now().getYear(),
            SORTED_MONTHS.indexOf(SORTED_MONTHS.stream().filter(str::matches).toList().get(0)) + 1,
            Integer.parseInt(str.replaceAll("до ","").replaceAll(pattern,""))
        ));
    FUNCTION_PARSING.put(
        Pattern.compile("(до \\d{2}.\\d{2})"),
        (String str) -> LocalDate.of(
            LocalDate.now().getYear(),
            Integer.parseInt(str.replaceAll("до \\d{2}.","")),
            Integer.parseInt(str.replaceAll(".\\d{2}$","").replaceAll("до ", ""))
        ));
  }
}
