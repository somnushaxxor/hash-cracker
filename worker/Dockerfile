FROM gradle:8.7.0-jdk17-alpine AS builder
WORKDIR /app
COPY . .
RUN gradle clean bootJar

FROM openjdk:17-alpine
WORKDIR /app
COPY --from=builder /app/build/libs/worker-*.jar worker.jar
ENTRYPOINT ["java","-jar","worker.jar"]
EXPOSE 8080