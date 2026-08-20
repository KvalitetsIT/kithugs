#!/bin/bash

if docker pull kvalitetsit/medcom-sdn-core-documentation:latest; then
    echo "Copy from old documentation image."
    docker cp $(docker create kvalitetsit/medcom-sdn-core-documentation:latest):/usr/share/nginx/html target/old
fi
