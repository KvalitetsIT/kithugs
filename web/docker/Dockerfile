FROM ibm-semeru-runtimes:open-21-jre

RUN apt-get update && apt-get upgrade -y && apt-get install -y tzdata

RUN groupadd --gid 11000 appuser && \
    useradd --gid 11000 --uid 11001 -m appuser

WORKDIR /opt/kvalitetsit
COPY /maven/application.cdx.json ./

WORKDIR /home/appuser
COPY entrypoint.sh .

COPY config/logback.xml logback-spring.xml
COPY /maven/web-exec.jar web.jar

EXPOSE 8080
EXPOSE 8081

USER 11001
ENTRYPOINT ["/home/appuser/entrypoint.sh"]
