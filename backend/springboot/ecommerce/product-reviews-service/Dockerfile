FROM adoptopenjdk/openjdk11:latest
RUN mkdir /opt/app
COPY target/product-reviews-service.jar /opt/app
CMD ["java", "-jar", "/opt/app/product-reviews-service.jar"]