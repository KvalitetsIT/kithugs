FROM adoptopenjdk:11-jre-openj9

RUN apt-get update && apt-get install -y tzdata

WORKDIR /
COPY entrypoint.sh .
RUN chmod +x entrypoint.sh

RUN mkdir /app
WORKDIR /app
COPY config/logback.xml ./logback-spring.xml
COPY /maven/web-exec.jar web.jar

EXPOSE 8080
EXPOSE 8081

ENTRYPOINT ["/entrypoint.sh"]
