package de.stphngrtz.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @see <a href="https://spring.io/guides/gs/consuming-rest/">Consuming a RESTful Web Service</a>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Value {

    private Long id;
    private String quote;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    @Override
    public String toString() {
        return "Value{" +
                "id=" + id +
                ", quote='" + quote + '\'' +
                '}';
    }
}
