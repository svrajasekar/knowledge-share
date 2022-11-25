FROM adoptopenjdk/openjdk11:latest
RUN mkdir /opt/app
COPY target/product-manufacturers-service.jar /opt/app
CMD ["java", "-jar", "/opt/app/product-manufacturers-service.jar"]