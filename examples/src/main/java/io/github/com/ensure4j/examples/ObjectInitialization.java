package io.github.com.ensure4j.examples;

import io.github.mangila.ensure4j.Ensure;

public class ObjectInitialization {

    static class EnsuredObject {
        String key;
        String value;

        // Run some preconditions in the class constructor
        EnsuredObject(String key, String value) {
            Ensure.notBlank(key, () -> new IllegalArgumentException("key must not be blank"));
            Ensure.notBlank(value, () -> new IllegalArgumentException("value must not be blank"));
            this.key = key;
            this.value = value;
        }

    }

    static class EnsuredObject1 {
        String key;
        String value;

        // Run some preconditions in the class constructor
        // and fallback with a default value if the precondition fails
        // Eager Initialization
        EnsuredObject1(String key, String value) {
            this.key = Ensure.notNullOrElse(key, "default_key");
            this.value = Ensure.notNullOrElse(value, "default_value");
        }
    }

    static class EnsuredObject2 {
        String key;
        String value;

        // Run some preconditions in the class constructor
        // and fallback with a default value if the precondition fails
        // run with a Supplier instead. Lazy Initialization
        EnsuredObject2(String key, String value) {
            this.key = Ensure.notNullOrElseGet(key, () -> "default_key");
            this.value = Ensure.notNullOrElseGet(value, () -> "default_value");
        }
    }
}
