FROM adoptopenjdk/openjdk11:latest
RUN mkdir /opt/app
COPY target/product-taxes-service.jar /opt/app
CMD ["java", "-jar", "/opt/app/product-taxes-service.jar"]