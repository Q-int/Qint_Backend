FROM amazoncorretto:17-alpine AS build
WORKDIR /app
COPY . .
RUN ./gradlew bootJar -x test

FROM amazoncorretto:17-alpine
COPY --from=build /app/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
