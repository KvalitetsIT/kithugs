#! /bin/bash

if [ "$TZ" = "" ]
then
   echo "Using default timezone (UTC)"
fi

if [[ -z $LOGGING_CONFIG ]]; then
  echo "Default logback configuration file: /app/logback-spring.xml"
  export LOGGING_CONFIG=/app/logback-spring.xml
fi

if [[ -z $LOG_LEVEL ]]; then
  echo "Default LOG_LEVEL = INFO"
  export LOG_LEVEL=INFO
fi

if [[ -z $LOG_LEVEL_FRAMEWORK ]]; then
  echo "Default LOG_LEVEL_FRAMEWORK = INFO"
  export LOG_LEVEL_FRAMEWORK=INFO
fi

if [[ -z $CORRELATION_ID ]]; then
  echo "Default CORRELATION_ID = correlation-id"
  export CORRELATION_ID=x-request-id
fi

if [[ -z $SERVICE_ID ]]; then
  echo "Default SERVICE_ID = kithugs"
  export SERVICE_ID=kithugs
fi

JAR_FILE=web.jar

echo "Starting service with the following command."
echo "java $JVM_OPTS -jar $JAR_FILE"

# start the application
exec java $JVM_OPTS -jar $JAR_FILE
