FROM gradle:8.7.0-jdk17-alpine AS builder
WORKDIR /app
COPY . .
RUN gradle clean bootJar

FROM openjdk:17-alpine
WORKDIR /app
COPY --from=builder /app/build/libs/manager-*.jar manager.jar
ENTRYPOINT ["java","-jar","manager.jar"]
EXPOSE 8080