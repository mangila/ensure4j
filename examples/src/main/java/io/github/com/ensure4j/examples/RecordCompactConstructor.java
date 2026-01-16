package io.github.com.ensure4j.examples;

import io.github.mangila.ensure4j.Ensure;

/**
 * Demonstrates using Ensure4j in a compact record constructor with custom exceptions.
 * <p>
 * This is a powerful pattern for ensuring domain objects are always in a valid state
 * upon creation, while using domain-specific exceptions.
 */
public class RecordCompactConstructor {

    public record User(String username, int age) {
        public User {
            // Using a supplier to throw a domain-specific exception
            Ensure.notBlank(username, () -> new DomainException("Username cannot be empty"));
            
            // Ensuring age is within reasonable bounds
            Ensure.min(13, age, () -> new DomainException("User must be at least 13 years old"));
            Ensure.max(120, age, () -> new DomainException("Age exceeds maximum limit"));
        }
    }

    /**
     * A custom exception representing a violation of domain rules.
     */
    public static class DomainException extends RuntimeException {
        public DomainException(String message) {
            super(message);
        }
    }

    public static void main(String[] args) {
        // Valid user
        User user = new User("alice_java", 25);
        System.out.println("Created user: " + user);

        // Invalid user (too young)
        try {
            new User("child", 10);
        } catch (DomainException e) {
            System.err.println("Domain violation: " + e.getMessage());
        }
    }
}
