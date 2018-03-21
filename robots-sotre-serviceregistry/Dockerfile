FROM openjdk:8-jre-alpine
COPY ./target/robots-store-serviceregistry.jar /usr/src/robotssotre/
WORKDIR /usr/src/robotssotre/
EXPOSE 8761
CMD ["java", "-jar", "robots-store-serviceregistry.jar"]