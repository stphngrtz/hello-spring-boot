package de.stphngrtz.controllers;

import com.jayway.jsonpath.JsonPath;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URL;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * @see <a href="https://spring.io/guides/gs/spring-boot/">Building an Application with Spring Boot</a>
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GreetingsControllerIT {

    @LocalServerPort
    private int port;

    @Value("${local.management.port}")
    private int managementPort;

    private URL base;

    @Autowired
    private TestRestTemplate template;

    @Before
    public void setUp() throws Exception {
        this.base = new URL("http://localhost:"+port+"/");
    }

    @Test
    public void getIndex() throws Exception {
        ResponseEntity<String> response = template.getForEntity(base.toString(), String.class);
        assertThat(response.getBody(), equalTo("Greetings from Spring Boot!"));
    }

    @Test
    public void getHealth() throws Exception {
        ResponseEntity<String> response = template.getForEntity("http://localhost:" + managementPort + "/health", String.class);
        assertThat(JsonPath.parse(response.getBody()).read("$.status", String.class), equalTo("UP"));
    }
}
