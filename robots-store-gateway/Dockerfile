FROM openjdk:8-jre-alpine
COPY ./target/robots-store-apigateway.jar /usr/src/robotssotre/
WORKDIR /usr/src/robotssotre/
EXPOSE 8000
CMD ["java", "-jar", "robots-store-apigateway.jar"]