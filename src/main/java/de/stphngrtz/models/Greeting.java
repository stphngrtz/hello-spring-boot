package de.stphngrtz.models;

/**
 * @see <a href="https://spring.io/guides/gs/rest-service/">Building a RESTful Web Service</a>
 */
public class Greeting {

    private final long id;
    private final String content;

    public Greeting(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
