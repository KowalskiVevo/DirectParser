![alt text](https://memepedia.ru/wp-content/uploads/2023/05/rajan-gosling-mem-mne-poebat.jpg)

# Direct Parser
Сервис для парсинга рекламный акций с Яндекс.Директ.

# Запуск сервиса
Через Docker из корневой папки проекта: 
- docker build -t yandex_parser .
- docker run -dp 8081:8081 yandex_parser

Либо с помощью Java + Maven:
- Сборка: mvn clean install
- Запуск: mvn spring-boot:run

URL сервиса: http://localhost:8081

# Swagger-UI
URL сваггера: http://localhost:8081/swagger-ui/index.html

# H2
Сервис сохраняет распарсенные акции в легковесную БД H2. 
Доступ к ней осуществляется по ссылке: http://localhost:8081/h2

Авторизация:
- JDBC URL: jdbc:h2:file:./yandex_direct
- Логин и пароль по дефолту


# Предварительные требования
- JDK 17
- MAVEN 3.6.2 или более
- IDE для разработки
