package ru.disarra;

import org.junit.Rule;
import org.junit.Test;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Record;
import org.neo4j.driver.Session;
import org.neo4j.harness.junit.rule.Neo4jRule;

import java.util.List;

import static org.junit.Assert.assertFalse;

public class RouterTest
{
    @Rule
    public Neo4jRule neo4j = new Neo4jRule()
            .withProcedure( Router.class );

    @Test
    public void findSingleRouteTest() throws Throwable
    {
        try( Driver driver = GraphDatabase.driver( neo4j.boltURI()) )
        {
            Session session = driver.session();
            session.run( "CREATE (:Airport {airportId:9, city:'Moscow', name:'Sheremetyevo International Airport'})");
            session.run( "CREATE (:Airport {airportId:7, city:'Dubai', name:'Dubai International Airport'})");
            session.run("MATCH (n1:Airport), (n2:Airport) WHERE n1.city='Moscow' AND n2.city='Dubai' " +
                    "CREATE (n1)-[r:Travel {arrival:'2020-01-02T04:19:00Z', departure:'2020-01-01T22:27:00Z', price:85}]->(n2)");

            List<Record> result = session.run("CALL route('Moscow', 'Dubai', 50, datetime('2020-01-01T00:00:00Z'), 3)").list();
            assertFalse(result.isEmpty());

        }
    }
}
