FROM neo4j

VOLUME /var/lib/neo4j/import
VOLUME /data
VOLUME /logs
VOLUME /plugins
RUN echo "apoc.export.file.enabled=true" >> /var/lib/neo4j/conf/neo4j.conf
RUN echo "apoc.import.file.enabled=true" >> /var/lib/neo4j/conf/neo4j.conf
RUN echo "dbms.security.procedures.unrestricted=apoc.*" >> /var/lib/neo4j/conf/neo4j.conf
ENV EXTENSION_SCRIPT="/var/lib/neo4j/import/migration.bash"