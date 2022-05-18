package ru.disarra.salesavia.routerservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.disarra.salesavia.routerservice.node.Airport;

@Repository
public interface AirportRepository extends CrudRepository<Airport, Long> {

}
