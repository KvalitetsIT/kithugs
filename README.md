![Build Status](https://github.com/KvalitetsIT/kithugs/workflows/CICD/badge.svg) ![Test Coverage](.github/badges/jacoco.svg)
# kithugs

Template repository showing how to be a good Java Spring Boot citizen in a k8s cluster.

## Getting started

Run `./setup.sh GIT_REPOSITORY_NAME`.

## Endpoints

The service is listening for connections on port 8080.

Spring boot actuator is listening for connections on port 8081. This is used as prometheus scrape endpoint. 

## Configuration

| Environment variable | Description | Required |
|----------------------|-------------|---------- |
| jdbc.url | JDBC connection URL | Yes |
| jdbc.user | JDBC user          | Yes |
| jdbc.pass | JDBC password      | Yes |
| LOG_LEVEL | Log Level for applikation  log. Defaults to INFO. | No |
| LOG_LEVEL_FRAMEWORK | Log level for framework. Defaults to INFO. | No |
| CORRELATION_ID | HTTP header to take correlation id from. Used to correlate log messages. Defaults to "x-request-id". | No
