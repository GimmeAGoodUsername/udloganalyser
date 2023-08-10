FROM eclipse-temurin:17-jdk-jammy
VOLUME /tmp
ARG JAR_FILE
COPY build/libs/*.jar app.jar
EXPOSE 8080:8080
ENTRYPOINT ["java","-jar","/app.jar"]