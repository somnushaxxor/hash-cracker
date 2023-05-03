#!/bin/bash

./gradlew clean build
docker-compose up -d
docker exec hash-cracker-mongo-primary-1 /scripts/init.sh