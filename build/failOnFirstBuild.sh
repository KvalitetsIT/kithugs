#!/bin/sh

echo "${GITHUB_REPOSITORY}"
echo "${DOCKER_SERVICE}"
if [ "${GITHUB_REPOSITORY}" != "KvalitetsIT/kithugs" ] && [ "${DOCKER_SERVICE}" = "kvalitetsit/kithugs" ]; then
  echo "Please run setup.sh REPOSITORY_NAME"
  exit 1
fi
