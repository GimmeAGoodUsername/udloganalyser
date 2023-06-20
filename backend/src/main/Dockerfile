FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
COPY build/libs/*.jar app.jar
EXPOSE 1337
ENTRYPOINT ["java","-jar","/app.jar"]