package io.github.com.ensure4j.examples;

import io.github.mangila.ensure4j.Ensure;

/**
 * Domain object instantiation pre-condition within a compact record constructor.
 * And throw a custom domain exception if the pre-condition fails.
 */
public class RecordCompactConstructor {

    record Person(String name, int age) {
        public Person {
            Ensure.notBlank(name, () -> new PersonException("name must not be blank"));
            Ensure.min(0, age, () -> new PersonException("age must be greater than or equal to 0"));
        }
    }


    static class PersonException extends RuntimeException {
        public PersonException(String message) {
            super(message);
        }
    }

    public static void main(String[] args) {
        var person = new Person("John", -1);
        System.out.println(person);
    }

}
