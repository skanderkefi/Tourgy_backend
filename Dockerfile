FROM openjdk
MAINTAINER KEFI Skander <kefiskander99@gmail.com>
ADD target/tourgy-service.jar tourgy-service.jar
ENTRYPOINT ["java", "-jar","-Dspring.profiles.active=prod","/SAGES-service.jar"]
EXPOSE 9200

