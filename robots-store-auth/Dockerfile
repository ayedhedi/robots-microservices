FROM openjdk:8-jre-alpine
COPY ./target/robots-store-auth.jar /usr/src/robotssotre/
WORKDIR /usr/src/robotssotre/
EXPOSE 8383
CMD ["java", "-jar", "robots-store-auth.jar"]