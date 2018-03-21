FROM openjdk:8-jre-alpine
COPY ./target/robots-store-query.jar /usr/src/robotssotre/
WORKDIR /usr/src/robotssotre/
EXPOSE 8282
CMD ["java", "-jar", "robots-store-query.jar"]