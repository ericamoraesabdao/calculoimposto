FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /calculoimposto

COPY ./pom.xml ./
RUN mvn dependency:go-offline -B

COPY ./src ./src

RUN mvn test && mvn clean package -DskipTests

FROM openjdk:17-jdk-slim

WORKDIR /calculoimposto
COPY --from=build /calculoimposto/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]