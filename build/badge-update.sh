#!/bin/bash

if [[ $(git status -s) != '' ]]; then
  git config user.name "Github Actions"
  git config user.email "development@kvalitetsit.dk"
  git add .github/badges/jacoco.svg
  git commit -m "Update coverage badge"
  git push
else
  echo 'Badge not updated. No work to do.'
fi
