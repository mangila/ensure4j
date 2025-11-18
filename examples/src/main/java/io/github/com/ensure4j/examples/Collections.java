package io.github.com.ensure4j.examples;

import io.github.mangila.ensure4j.Ensure;

import java.util.List;

/**
 * Ensure a domain object collection with a min and a max boundary also check if not null and not empty.
 */
public class Collections {

    record Person(String name, int age) {
        Person {
            Ensure.notBlank(name, "name must not be blank");
            Ensure.min(0, age, "age must be greater than or equal to 0");
        }

    }

    record PersonCollection(List<Person> people) {
        PersonCollection {
            Ensure.notNull(people, "people collection must not be null");
            Ensure.max(10, people.size(), "people must not exceed 10");
        }

        int size() {
            return people.size();
        }
    }

    static class ServiceLayer {
        public void processCollection(PersonCollection collection) {
            Ensure.notEmpty(collection.people, "collection must not be empty");
            Ensure.min(2, collection.size(), "collection must contain at least 2 people");
            Ensure.max(10, collection.size(), "collection must not exceed 10 people");
            // do processing
        }
    }

    public static void main(String[] args) {
        var service = new ServiceLayer();
        var collection = new PersonCollection(
                List.of(new Person("John", 18),
                        new Person("Jane", 25))
        );
        service.processCollection(collection);
    }

}
