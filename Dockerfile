FROM openjdk:13
VOLUME /tmp
EXPOSE 8010
ADD ./target/spring-service-aforo255-security-0.0.1-SNAPSHOT.jar   security-server.jar
ENTRYPOINT ["java","-jar","/security-server.jar"]