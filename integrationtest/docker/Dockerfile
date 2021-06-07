FROM kvalitetsit/kithugs:dev

COPY /maven/integrationtest.jar /app/lib/integrationtest.jar
COPY /maven/org.jacoco.agent-*.jar /jacoco/jacoco-agent.jar

RUN apt-get update && \
    apt-get install -y unzip && \
    unzip /jacoco/jacoco-agent.jar jacocoagent.jar -d /jacoco/
