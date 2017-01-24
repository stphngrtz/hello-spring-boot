package de.stphngrtz.controllers;

import de.stphngrtz.models.Greeting;
import de.stphngrtz.models.GreetingWithHATEOAS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * @see <a href="https://spring.io/guides/gs/spring-boot/">Building an Application with Spring Boot</a>
 * @see <a href="https://spring.io/guides/gs/rest-service/">Building a RESTful Web Service</a>
 * @see <a href="https://spring.io/guides/gs/rest-service-cors/">Enabling Cross Origin Requests for a RESTful Web Service</a>
 * @see <a href="https://spring.io/guides/gs/rest-hateoas/">Building a Hypermedia-Driven RESTful Web Service</a>
 */
@RestController
public class GreetingsController {

    private final AtomicLong counter = new AtomicLong();

    @Autowired
    private CounterService counterService;

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @CrossOrigin(origins = "http://localhost:9000")
    @RequestMapping(value = "/greeting", method = RequestMethod.GET)
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        counterService.increment("greeting");

        return new Greeting(counter.incrementAndGet(), String.format("Hello, %s!", name));
    }

    @RequestMapping(value = "/greetingWithHATEOAS", method = RequestMethod.GET)
    public HttpEntity<GreetingWithHATEOAS> greetingWithHATEOAS(@RequestParam(value = "name", required = false, defaultValue = "World") String name) {
        counterService.increment("greetingWithHATEOAS");

        GreetingWithHATEOAS greeting = new GreetingWithHATEOAS(String.format("Hello, %s!", name));
        greeting.add(linkTo(methodOn(GreetingsController.class).greetingWithHATEOAS(name)).withSelfRel());

        return new ResponseEntity<>(greeting, HttpStatus.OK);
    }
}
