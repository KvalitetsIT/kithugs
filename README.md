![Build Status](https://github.com/KvalitetsIT/kithugs/workflows/CICD/badge.svg) ![Test Coverage](.github/badges/jacoco.svg)
# kithugs

Template repository showing how to be a good Java Spring Boot citizen in a k8s cluster.

## A good citizen

Below is a set of recommendations for being a good service. The recommendations are not tied to a specific language or 
framework.

1. Configuration through environment variables.
2. Expose readiness endpoint
3. Expose endpoint that Prometheus can scrape
4. Be stateless
5. Support multiple instances
6. Always be in a releasable state
7. Automate build and deployment.

Some of above recommendations are heavily inspired by [https://12factor.net/](https://12factor.net/). It is recommended 
read [https://12factor.net/](https://12factor.net/) for more inspiration and further details. Some points go 
further than just being a good service and also touches areas like operations.

## Getting started

Run `./setup.sh GIT_REPOSITORY_NAME`.

Above does a search/replace in relevant files. 

## Endpoints

The service is listening for connections on port 8080.

Spring boot actuator is listening for connections on port 8081. This is used as prometheus scrape endpoint and health monitoring. 

Prometheus scrape endpoint: `http://localhost:8081/actuator/prometheus`  
Health URL that can be used for readiness probe: `http://localhost:8081/actuator/health`

## Configuration

| Environment variable | Description | Required |
|----------------------|-------------|---------- |
| JDBC_URL | JDBC connection URL | Yes |
| JDBC_USER | JDBC user          | Yes |
| JDBC_PASS | JDBC password      | Yes |
| LOG_LEVEL | Log Level for applikation  log. Defaults to INFO. | No |
| LOG_LEVEL_FRAMEWORK | Log level for framework. Defaults to INFO. | No |
| CORRELATION_ID | HTTP header to take correlation id from. Used to correlate log messages. Defaults to "x-request-id". | No
