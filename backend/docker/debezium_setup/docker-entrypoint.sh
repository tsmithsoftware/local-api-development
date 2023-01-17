#!/bin/sh

# Abort on any error (including if wait-for-it fails).
set -e

#apt update
#apt install -y curl

#curl http://localhost:8083 || curl http://127.0.0.1:8083 || curl http://connect:8083

#echo "entering waiting phase"

# Wait for the backend to be up
#  echo "waiting for host..."
#  ls
#  exec ./wait-for-it.sh "127.0.0.1:8083"

#echo "host up!"

# add the connectors
curl --location --request POST 'localhost:8083/connectors' \
--header 'Content-Type: application/json' \
--data-raw '{ "name": "inventory-connector", "config": { "connector.class": "io.debezium.connector.mysql.MySqlConnector", "tasks.max": "1", "database.hostname": "mysql", "database.port": "3306", "database.user": "debezium", "database.password": "dbz", "database.server.id": "184054", "topic.prefix": "dbserver1", "database.include.list": "inventory", "schema.history.internal.kafka.bootstrap.servers": "kafka:9092", "schema.history.internal.kafka.topic": "schemahistory.inventory" } }'

# Run the main container command.
#exec "$@"
