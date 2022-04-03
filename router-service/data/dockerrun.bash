#!/bin/bash

docker run -d -p 7474:7474 -p 7687:7687 -v "$(pwd)/volumes/import:/var/lib/neo4j/import" -v "$(pwd)/volumes/data:/data" -v "$(pwd)/volumes/logs:/logs" -v "$(pwd)/volumes/plugins:/plugins" -e NEO4J_AUTH=none -e NEO4JLABS_PLUGINS=\[\"apoc\"\] --name my_neo4j $1