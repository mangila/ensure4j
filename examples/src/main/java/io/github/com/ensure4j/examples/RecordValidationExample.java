package io.github.com.ensure4j.examples;

import io.github.mangila.ensure4j.Ensure;
import io.github.mangila.ensure4j.ops.EnsureNumberOps;
import io.github.mangila.ensure4j.ops.EnsureStringOps;

/**
 * Demonstrates how to use Ensure4j with Java Records and compact constructors.
 */
public class RecordValidationExample {

    private static final EnsureStringOps STRINGS = Ensure.strings();
    private static final EnsureNumberOps NUMBERS = Ensure.numbers();

    public record User(String username, int age) {
        public User {
            // Validate using static members for better performance in tight loops
            STRINGS.notBlank(username, "Username must not be blank");
            NUMBERS.min(18, age);
            NUMBERS.max(150, age);
        }
    }

    // Using primitive types
    public record Product(String sku, int price) {
        public Product {
            Ensure.strings().notBlank(sku);
            Ensure.numbers().positive(price);
        }
    }

    public static void main(String[] args) {
        User user = new User("john_doe", 25);
        System.out.println("Created user: " + user);

        try {
            new User("", 17);
        } catch (RuntimeException e) {
            System.err.println("Failed to create user: " + e.getMessage());
        }
    }
}
