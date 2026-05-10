package io.github.com.ensure4j.examples.v3;

import io.github.mangila.ensure4j.Ensure;
import io.github.mangila.ensure4j.ops.EnsureNumberOps;

/**
 * Demonstrates how to throw custom exceptions using Suppliers.
 */
public class CustomExceptionExample {

  private static final EnsureNumberOps NUMBERS = Ensure.numbers();

  public void updateStock(int quantity) {
    // Throw a specific domain exception instead of EnsureException
    NUMBERS.positive(quantity, () -> new StockException("Negative stock: " + quantity));

    System.out.println("Stock updated: " + quantity);
  }

  public static void main(String[] args) {
    CustomExceptionExample example = new CustomExceptionExample();

    try {
      example.updateStock(-5);
    } catch (StockException e) {
      System.err.println("Caught domain exception: " + e.getMessage());
    }
  }

  // Domain-specific exception
  public static class StockException extends RuntimeException {
    public StockException(String message) {
      super(message);
    }
  }
}
