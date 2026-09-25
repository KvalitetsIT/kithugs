#!/bin/sh
set -euo pipefail


# Update file only if changed
updateFile() {
    local f="$1"
    if cmp -s "$f" "$f.tmp"; then
        /bin/rm "$f.tmp"
    else
        /bin/mv "$f.tmp" "$f"
    fi
}

# Configuration
PREFIX="api"                     # The expected file prefix
ENV_FILE="/kit/env"
API_DIR="/usr/share/nginx/html"   # where the files are located

echo "[]" > "$ENV_FILE"

# Find all api*.yaml files in the folder
for api_file in "$API_DIR"/$PREFIX*.yaml; do
    [ -e "$api_file" ] || continue

    filename=$(basename "$api_file")
    api_name=$(echo "${filename#api_}" | sed 's/\.yaml$//')
    capitalized_api=$(echo "$api_name" | awk '{print toupper(substr($0,1,1)) substr($0,2)}')

    file_url="${BASE_URL}/${filename}"
    echo "Adding $capitalized_api -> $file_url"

    # Fix quotes in jq
    jq --arg n "$capitalized_api" --arg u "$file_url" \
       '. += [{"name": $n, "url": $u}]' "$ENV_FILE" > "$ENV_FILE.tmp"
    updateFile "$ENV_FILE"
done

echo "setDefinitions.sh completed. Generated $ENV_FILE"
