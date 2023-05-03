#!/bin/bash

DELAY=25

mongo <<EOF
var config = {
    "_id": "rs0",
    "version": 1,
    "members": [
        {
            "_id": 0,
            "host": "mongo-primary:27017",
            "priority": 5
        },
        {
            "_id": 1,
            "host": "mongo-secondary-1:27017",
            "priority": 3
        },
        {
            "_id": 2,
            "host": "mongo-secondary-2:27017",
            "priority": 1
        }
    ]
};
rs.initiate(config, { force: true });
EOF

sleep $DELAY

mongo <<EOF
rs.status();
EOF
