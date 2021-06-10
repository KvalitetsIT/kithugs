#!/bin/sh

echo "${GITHUB_REPOSITORY}"
echo ""${DOCKER_SERVICE}"
if [ "${GITHUB_REPOSITORY}" != "kvalitetsit/kithugs" ] && [ "${DOCKER_SERVICE}" = "kvalitetsit/kithugs" ]; then
  echo "Please run setup.sh"
  exit 1
fi
