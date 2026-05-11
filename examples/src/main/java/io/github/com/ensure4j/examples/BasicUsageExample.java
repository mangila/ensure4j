package io.github.com.ensure4j.examples;

import io.github.mangila.ensure4j.Ensure;
import io.github.mangila.ensure4j.EnsureException;
import java.util.List;
import java.util.Map;

/**
 * A comprehensive overview of how to use Ensure4j for various types of validations.
 */
public class BasicUsageExample {

  public static void main(String[] args) {
    // 1. Basic null checks
    String name = "Junie";
    Ensure.notNull(name); // Throws EnsureException if null

    // 2. String validations
    Ensure.strings().notBlank(name);
    Ensure.strings().minLength(3, name);
    Ensure.strings().matches("^[A-Z][a-z]+$", name);

    // 3. Number validations
    int age = 25;
    Ensure.numbers().min(18, age);
    Ensure.numbers().positive(age);

    // 4. Collection and Map validations
    List<String> roles = List.of("ADMIN", "USER");
    Ensure.collections().notEmpty(roles);
    Ensure.collections().containsElement(roles, "ADMIN");

    Map<String, String> config = Map.of("env", "prod");
    Ensure.maps().notEmpty(config);

    // 5. Fluent API: Methods return the validated value for easy assignment
    String validatedName = Ensure.strings().notBlank(name);
    System.out.println("Validated user: " + validatedName);

    // 6. Custom error messages
    try {
      Ensure.numbers().max(10, age, "Age cannot be greater than 10");
    } catch (EnsureException e) {
      System.out.println("Expected failure: " + e.getMessage());
    }
  }
}
