package de.stphngrtz.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.ResourceSupport;

/**
 * @see <a href="https://spring.io/guides/gs/rest-hateoas/">Building a Hypermedia-Driven RESTful Web Service</a>
 */
public class GreetingWithHATEOAS extends ResourceSupport {

    private final String content;

    @JsonCreator
    public GreetingWithHATEOAS(@JsonProperty String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
