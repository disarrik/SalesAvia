package ru.disarra.salesavia.routerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.disarra.salesavia.routerservice.repository.RouteRepository;

import java.time.ZonedDateTime;

@SpringBootApplication
public class RouterServiceApplication {

    public static void main(String[] args) {
        var result = SpringApplication.run(RouterServiceApplication.class, args).getBean(RouteRepository.class).getRoutes("Delhi", "Madrid", 2, 8, ZonedDateTime.parse("2020-01-01T01:00:00Z"));
        System.out.println(result);
    }

}
