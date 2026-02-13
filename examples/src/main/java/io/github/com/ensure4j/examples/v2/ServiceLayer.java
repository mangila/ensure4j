package io.github.com.ensure4j.examples.v2;

import io.github.mangila.ensure4j.Ensure;
import java.util.UUID;

/**
 * Demonstrates using Ensure4j in a Service Layer to validate inputs
 * before processing business logic.
 * <p>
 * This follows the "fail-fast" principle, ensuring that the service
 * never operates on invalid data.
 */
public class ServiceLayer {

    public record Order(UUID id, int amount, String customerEmail) {
    }

    /**
     * Places an order after validating the input.
     *
     * @param order The order to place.
     * @throws io.github.mangila.ensure4j.EnsureException if validation fails.
     */
    public void placeOrder(Order order) {
        // 1. Basic null check
        Ensure.notNull(order, "Order cannot be null");

        // 2. State validation with custom messages
        Ensure.notNull(order.id(), "Order ID must be present");
        Ensure.min(1, order.amount(), "Order amount must be at least 1");
        Ensure.notBlank(order.customerEmail(), "Customer email is required");

        // 3. Complex logic validation
        Ensure.isTrue(order.customerEmail().contains("@"),
                () -> new IllegalArgumentException("Invalid email format: " + order.customerEmail()));

        System.out.println("Processing order: " + order.id());
        // ... business logic ...
    }

    public static void main(String[] args) {
        ServiceLayer service = new ServiceLayer();

        // Valid order
        service.placeOrder(new Order(UUID.randomUUID(), 100, "customer@example.com"));

        // This would throw EnsureException
        try {
            service.placeOrder(new Order(UUID.randomUUID(), 0, ""));
        } catch (RuntimeException e) {
            System.err.println("Validation failed as expected: " + e.getMessage());
        }
    }
}
