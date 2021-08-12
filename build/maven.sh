#!/bin/sh

apt-get update
apt-get install -y docker.io

SRC_FOLDER=src

if [ -d $SRC_FOLDER ]; then
  cd $SRC_FOLDER

  mvn clean install -Pdocker-test
else
  echo "$SRC_FOLDER folder not found."
  exit 1
fi

