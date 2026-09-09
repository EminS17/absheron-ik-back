FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-Dspring.datasource.url=jdbc:postgresql://dpg-d9nq1rjncjis73an65i0-a.oregon-postgres.render.com:5432/absherondb?sslmode=require", "-Dspring.datasource.username=absheron_user", "-Dspring.datasource.password=UImsVxeeoo22RuwN1hPOtFjUq2NnmZZb", "-jar", "app.jar"]