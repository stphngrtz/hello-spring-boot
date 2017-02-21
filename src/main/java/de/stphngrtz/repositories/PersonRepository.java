package de.stphngrtz.repositories;

import de.stphngrtz.models.Person;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * @see <a href="https://spring.io/guides/gs/accessing-mongodb-data-rest/">Accessing MongoDB Data with REST</a>
 */
@RepositoryRestResource(collectionResourceRel = "people", path = "people")
public interface PersonRepository extends MongoRepository<Person, String> {
    List<Person> findByLastName(@Param("name") String name);
}
