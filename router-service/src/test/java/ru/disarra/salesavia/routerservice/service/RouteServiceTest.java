package ru.disarra.salesavia.routerservice.service;

import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class RouteServiceTest {

    @Test
    public void getRoutesTest() {
        System.out.println(ZonedDateTime.parse("2020-01-01T01:00:00Z").format(DateTimeFormatter.ISO_INSTANT));
    }
}
