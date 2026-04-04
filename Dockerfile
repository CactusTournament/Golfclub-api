FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
WORKDIR /app
COPY target/golfclub-1.0.0.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]
