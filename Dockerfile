FROM gradle:8-jdk17-alpine AS builder

WORKDIR /app

COPY build.gradle settings.gradle ./
COPY src ./src

RUN gradle clean build -x test

FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

COPY --from=builder /app/build/libs/*-SNAPSHOT.jar app.jar

EXPOSE 8000

ENTRYPOINT ["java", "-jar", "app.jar"]