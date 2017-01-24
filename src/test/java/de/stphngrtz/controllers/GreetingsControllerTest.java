package de.stphngrtz.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.concurrent.atomic.AtomicLong;

import static org.hamcrest.Matchers.any;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @see <a href="https://spring.io/guides/gs/spring-boot/">Building an Application with Spring Boot</a>
 * @see <a href="https://spring.io/guides/gs/rest-service/">Building a RESTful Web Service</a>
 * @see <a href="https://spring.io/guides/gs/rest-hateoas/">Building a Hypermedia-Driven RESTful Web Service</a>
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class GreetingsControllerTest {

    @Autowired
    private MockMvc mvc;

    private final AtomicLong counter = new AtomicLong();

    @Test
    public void getIndex() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Greetings from Spring Boot!")));
    }

    @Test
    public void getDefaultGreeting() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/greeting").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", any(Integer.class)))
                .andExpect(jsonPath("$.content", equalTo("Hello, World!")));
    }

    @Test
    public void getPersonalizedGreeting() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/greeting?name=Stephan").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", any(Integer.class)))
                .andExpect(jsonPath("$.content", equalTo("Hello, Stephan!")));
    }

    @Test
    public void getPersonalizedGreetingWithHATEOAS() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/greetingWithHATEOAS?name=Stephan").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", equalTo("Hello, Stephan!")))
                .andExpect(jsonPath("$._links.self.href", equalTo("http://localhost/greetingWithHATEOAS?name=Stephan")));
    }
}
