#!/bin/bash

if docker pull kvalitetsit/kithugs-documentation-latest; then
    echo "Copy from old documentation image."
    docker cp $(docker create kvalitetsit/kithugs-documentation:latest):/usr/share/nginx/html target/hejhej
fi
