FROM adoptopenjdk/openjdk11:latest
RUN mkdir /opt/app
COPY target/products-service.jar /opt/app
CMD ["java", "-jar", "/opt/app/products-service.jar"]