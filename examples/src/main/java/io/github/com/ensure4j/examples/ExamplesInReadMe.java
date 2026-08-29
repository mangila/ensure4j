package io.github.com.ensure4j.examples;

import io.github.mangila.ensure4j.Ensure;

final class ExamplesInReadMe {

  public void placeOrder1(Order order) {
    Ensure.notNull(order, "Order cannot be null");
    Ensure.positive(order.getAmount(), "Order amount must be positive");
    // do business logic
  }

  public void placeOrder2(Order order) {
    Ensure.notNull(order, () -> new OrderException("Order cannot be null"));
    Ensure.positive(order.getAmount(), () -> new OrderException("Order amount must be positive"));
    Ensure.notEmpty(order.getItems(), () -> new OrderException("Order items cannot be empty"));
    // do business logic
  }

  // throws EnsureException with message:
  public void placeOrder3(Order order) {
    Ensure.notNull(order); // object must not be null
    Ensure.positive(order.getAmount()); // value must be positive - %s
    Ensure.notEmpty(order.getItems()); // collection must not be empty or null
    // do business logic
  }
}
