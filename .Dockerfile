FROM maven:3.8.4-openjdk-17-slim

WORKDIR /usr/src/app
COPY . .

CMD ["mvn","spring-boot:run"]
