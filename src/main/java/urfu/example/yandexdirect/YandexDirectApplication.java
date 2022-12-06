package urfu.example.yandexdirect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class YandexDirectApplication {

	public static void main(String[] args) {
		SpringApplication.run(YandexDirectApplication.class, args);
	}

}
