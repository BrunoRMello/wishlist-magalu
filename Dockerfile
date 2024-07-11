
FROM maven:3.9.4 AS build
WORKDIR /workspace
COPY . .
RUN mvn clean package -DskipTests


FROM openjdk:20
WORKDIR /app


COPY --from=build /workspace/target/wishlist-0.0.1-SNAPSHOT.jar /app/app.jar


EXPOSE 8080

CMD ["java", "-jar", "/app/app.jar"]
