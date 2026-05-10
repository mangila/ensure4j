package io.github.com.ensure4j.examples;

import io.github.mangila.ensure4j.Ensure;
import io.github.mangila.ensure4j.ops.EnsureCollectionOps;
import java.util.List;

/**
 * Demonstrates how to use Ensure4j in a business service for argument validation.
 */
public class ServiceValidationExample {

  private final EnsureCollectionOps collections = Ensure.collections();

  public void processOrder(String orderId, List<String> items) {
    // 1. Validate ID
    Ensure.strings().notBlank(orderId, "Order ID must not be empty");

    // 2. Validate items
    collections.notEmpty(items, "At least one item is required");
    collections.notContainsNull(items, "Order items cannot contain nulls");

    // 3. Business logic...
    System.out.println("Processing order: " + orderId + " with " + items.size() + " items");
  }

  public static void main(String[] args) {
    ServiceValidationExample service = new ServiceValidationExample();

    // Success
    service.processOrder("ORD-123", List.of("Laptop", "Mouse"));

    // Failure
    try {
      service.processOrder("ORD-456", null);
    } catch (RuntimeException e) {
      System.err.println("Service validation failed: " + e.getMessage());
    }
  }
}
