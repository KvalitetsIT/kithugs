#!/usr/bin/env bash

if [ -z $1 ]; then
  echo "Run setup.sh REPOSITORY_NAME"
  exit 1
fi

ignore_files=".git|setup.sh"

for input_file in `tree -I "${ignore_files}" -Ffai --noreport`
do
  if [ ! -d "${input_file}" ]; then
    echo "Processing file: ${input_file}"
    sed -i -e "s/kithugs/$1/g" "${input_file}"
  fi
done

# Clean up / implode
rm setup.sh