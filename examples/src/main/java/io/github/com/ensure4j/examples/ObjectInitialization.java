package io.github.com.ensure4j.examples;

import io.github.mangila.ensure4j.Ensure;

public class ObjectInitialization {

    static class EnsuredObject {
        String key;
        String value;
        int number;

        // Run some preconditions in the class constructor
        EnsuredObject(String key, String value, int number) {
            Ensure.notBlank(key, () -> new IllegalArgumentException("key must not be blank"));
            Ensure.notBlank(value, () -> new IllegalArgumentException("value must not be blank"));
            Ensure.min(1, number, () -> new IllegalArgumentException("number must be greater than or equal to 0"));
            this.key = key;
            this.value = value;
            this.number = number;
        }

    }

    static class EnsuredObject1 {
        String key;
        String value;
        int number;

        // Run some preconditions in the class constructor
        // and fallback with a default value if the precondition fails
        // Eager Initialization
        EnsuredObject1(String key, String value, int number) {
            this.key = Ensure.notNullOrElse(key, "default_key");
            this.value = Ensure.notNullOrElse(value, "default_value");
            this.number = Ensure.min(1, number, "number must be greater than or equal to 0");
        }
    }

    static class EnsuredObject2 {
        String key;
        String value;
        int number;

        // Run some preconditions in the class constructor
        // and fallback with a default value if the precondition fails
        // run with a Supplier instead. Lazy Initialization
        EnsuredObject2(String key, String value, int number) {
            this.key = Ensure.notNullOrElseGet(key, () -> "default_key");
            this.value = Ensure.notNullOrElseGet(value, () -> "default_value");
            this.number = Ensure.min(1, number, () -> new IllegalArgumentException("number must be greater than or equal to 0"));
        }
    }

    static class EnsuredObject3 {
        String key;
        String value;
        int number;

        // Run some preconditions in the class constructor
        // use the value, but only if it's not null
        EnsuredObject3(String key, String value, int number) {
            this.key = Ensure.notNullOrElseThrow(key);
            this.value = Ensure.notNullOrElseThrow(value);
            this.number = Ensure.min(1, number, () -> new IllegalArgumentException("number must be greater than or equal to 0"));
        }
    }
}
