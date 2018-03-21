FROM openjdk:8-jre-alpine
COPY ./target/robots-store-command.jar /usr/src/robotssotre/
WORKDIR /usr/src/robotssotre/
EXPOSE 8181
CMD ["java", "-jar", "robots-store-command.jar"]