FROM openjdk:17
LABEL maintainer="joeraymond"
ADD target/config-server-0.0.1-SNAPSHOT.jar config-server-0.0.1-SNAPSHOT.jar
EXPOSE 8888
ENTRYPOINT ["java", "-jar", "config-server-0.0.1-SNAPSHOT.jar"]