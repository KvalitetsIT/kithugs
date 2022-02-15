FROM ibm-semeru-runtimes:open-11-jre

RUN apt-get update && apt-get install -y tzdata

RUN groupadd --gid 1000 appuser \
    && useradd --gid 1000 -m appuser

COPY entrypoint.sh /home/appuser
RUN chmod +x /home/appuser/entrypoint.sh

COPY config/logback.xml /home/appuser/logback-spring.xml
COPY /maven/web-exec.jar /home/appuser/web.jar
WORKDIR /home/appuser

EXPOSE 8080
EXPOSE 8081

RUN chmod a-w /etc
USER appuser
ENTRYPOINT ["/home/appuser/entrypoint.sh"]