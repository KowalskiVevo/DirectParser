package urfu.example.yandexdirect.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@EnableWebMvc
public class SwaggerConfiguration implements WebMvcConfigurer {

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2).select()
        .apis(RequestHandlerSelectors.basePackage("urfu.example.yandexdirect"))
        .paths(PathSelectors.regex("/.*"))
        .build().apiInfo(new ApiInfoBuilder().title("Яндекс.Парсер")
            .description("API для получения/парсинга акций с Яндекс.Директ'а")
            .contact(new Contact("Direct-Parser-Team",
                "https://github.com/KowalskiVevo/DirectParser",
                "koval.dmitry@urfu.me"))
            .license("Apache 2.0")
            .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
            .version("1.0.0")
            .build());
  }
}
