FROM adoptopenjdk/openjdk11:latest
RUN mkdir /opt/app
COPY target/product-categories-service.jar /opt/app
CMD ["java", "-jar", "/opt/app/product-categories-service.jar"]