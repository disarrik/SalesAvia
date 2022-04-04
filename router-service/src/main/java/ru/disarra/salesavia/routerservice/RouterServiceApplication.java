package ru.disarra.salesavia.routerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.disarra.salesavia.routerservice.service.RouteService;

@SpringBootApplication
public class RouterServiceApplication {

    public static void main(String[] args) {
        var a = SpringApplication.run(RouterServiceApplication.class, args).getBean(RouteService.class).getRoutesByCitiesAndTransfersLimit("Delhi", "Madrid", 2);
        System.out.println(a);
    }

}
