#!/bin/sh

if [ "${GITHUB_REPOSITORY}" != "kvalitetsit/kithugs" ] && [ "${DOCKER_SERVICE}" = "kvalitetsit/kithugs" ]; then
  echo "Please run setup.sh"
  exit 1
fi
