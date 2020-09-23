FROM openjdk
MAINTAINER KEFI Skander <kefiskander99@gmail.com>
ADD target/client1-service.jar client1-service.jar
ENTRYPOINT ["java", "-jar","-Dspring.profiles.active=prod","/SAGES-service.jar"]
EXPOSE 9003
