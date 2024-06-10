FROM amazoncorretto:17.0.7-alpine
LABEL authors="allysonhalley"
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/random","-jar", "/app.jar"]