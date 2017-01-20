package de.stphngrtz.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @see <a href="https://spring.io/guides/gs/spring-boot/">Building an Application with Spring Boot</a>
 */
@RestController
public class GreetingsController {

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }
}
