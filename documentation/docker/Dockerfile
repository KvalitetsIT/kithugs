FROM swaggerapi/swagger-ui:v4.12.0

# Definer bruger- og gruppe-id
ARG USER_ID=1000
ARG GROUP_ID=1000

# Opret non-root gruppe og bruger med specifikke id'er
RUN addgroup -g ${GROUP_ID} appgroup && \
    adduser -u ${USER_ID} -G appgroup -S appuser
    
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
    chmod +x /config.sh && \
    chown -R ${USER_ID}:${GROUP_ID} /kit /usr/share/nginx/html

USER ${USER_ID}:${GROUP_ID}

ENTRYPOINT ["sh", "/config.sh"]
