package ru.disarra.salesavia.routerservice;

import ac.simons.neo4j.migrations.springframework.boot.autoconfigure.MigrationsAutoConfiguration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.neo4j.driver.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.neo4j.DataNeo4jTest;
import org.springframework.data.neo4j.core.Neo4jClient;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.Neo4jContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.TestcontainersConfiguration;
import ru.disarra.salesavia.routerservice.dto.AirportDTO;
import ru.disarra.salesavia.routerservice.dto.mapper.AirportDTOMapper;

import java.util.Collection;

@Testcontainers(disabledWithoutDocker = true)
@DataNeo4jTest
@ImportAutoConfiguration(MigrationsAutoConfiguration.class)
public class UsingDataNeo4jTest {

    @Autowired
    private AirportDTOMapper airportDTOMapper;

    @Autowired
    private Neo4jClient neo4jClient;

    @Container
    private final static Neo4jContainer<?> neo4j = new Neo4jContainer<>("neo4j:4.2")
            .withReuse(TestcontainersConfiguration.getInstance().environmentSupportsReuse());

    @DynamicPropertySource
    static void neo4jProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.neo4j.uri", neo4j::getBoltUrl);
        registry.add("spring.neo4j.authentication.username", () -> "neo4j");
        registry.add("spring.neo4j.authentication.password", neo4j::getAdminPassword);
    }

    @Test
    @Order(0)
    void containerUpTest(@Autowired Driver driver) {
    }

    @Test
    @Order(0)
    void testMigrations() {
        Collection<AirportDTO> airports= neo4jClient
                .query("MATCH (a:Airport) RETURN a")
                .fetchAs(AirportDTO.class)
                .mappedBy(airportDTOMapper)
                .all();
        Assertions.assertNotNull(airports);
        Assertions.assertFalse(airports.isEmpty());
    }
}
