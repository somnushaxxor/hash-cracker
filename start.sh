#!/bin/bash

docker-compose up -d --build --force-recreate
docker exec hash-cracker-mongo-primary-1 /scripts/init.sh