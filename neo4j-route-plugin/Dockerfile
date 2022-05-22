#
# Build stage
#
FROM maven:3.6.0-jdk-11-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

#
# Package stage
#
FROM neo4j:4.4.6
EXPOSE 7474
EXPOSE 7687
COPY --from=build /home/app/target/neo4j-route-plugin-1.0.jar /var/lib/neo4j/plugins/router.jar