#!/bin/bash

/var/lib/neo4j/bin/neo4j-admin import --database=neo4j --force --nodes="/var/lib/neo4j/import/airports.csv" --relationships="/var/lib/neo4j/import/travels.csv" --trim-strings=true