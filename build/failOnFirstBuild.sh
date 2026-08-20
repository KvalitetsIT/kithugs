#!/bin/sh

echo "${GITHUB_REPOSITORY}"
echo "${DOCKER_SERVICE}"
if [ "${GITHUB_REPOSITORY}" != "KvalitetsIT/medcom-sdn-core" ] && [ "${DOCKER_SERVICE}" = "kvalitetsit/medcom-sdn-core" ]; then
  echo "Please run setup.sh REPOSITORY_NAME"
  exit 1
fi
