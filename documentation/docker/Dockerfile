FROM swaggerapi/swagger-ui:v3.27.0

COPY /maven/*.yaml /usr/share/nginx/html/

COPY /versions /kit/versions
COPY /config/setVersion.sh /kit/setVersion.sh
COPY /config/setServers.sh /kit/setServers.sh
COPY /config/config.sh /config.sh

COPY /maven/runningVersion.json /kit/runningVersion.json

RUN apk update && \
    apk add jq && \
    wget -O /usr/bin/yq "https://github.com/mikefarah/yq/releases/download/3.3.2/yq_linux_amd64" && \
    chmod +x /usr/bin/yq && \
    chmod +x /kit/setVersion.sh && \
    chmod +x /kit/setServers.sh && \
    chmod +x /config.sh

CMD ["sh", "/config.sh"]