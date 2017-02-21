package de.stphngrtz.models;

import org.springframework.data.annotation.Id;

/**
 * @see <a href="https://spring.io/guides/gs/accessing-mongodb-data-rest/">Accessing MongoDB Data with REST</a>
 */
public class Person {
    @Id
    private String id;

    public String firstName;
    public String lastName;
}
