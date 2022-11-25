FROM adoptopenjdk/openjdk11:latest
RUN mkdir /opt/app
COPY target/frontend-service.jar /opt/app
CMD ["java", "-jar", "/opt/app/frontend-service.jar"]