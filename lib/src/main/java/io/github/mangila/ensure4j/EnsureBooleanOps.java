package io.github.mangila.ensure4j;

import static io.github.mangila.ensure4j.EnsureUtils.getSupplierOrThrow;

import java.util.function.Supplier;
import org.jetbrains.annotations.Contract;

final class EnsureBooleanOps {

  static final String BOOLEAN_MUST_BE_TRUE_MESSAGE = "boolean must be true";
  static final String BOOLEAN_MUST_BE_FALSE_MESSAGE = "boolean must be false";

  private EnsureBooleanOps() {
    throw new AssertionError("No Ensure4j for you!");
  }

  /**
   * Ensures that the provided boolean expression is {@code true}.
   *
   * @param expression the boolean expression to check
   * @param exceptionSupplier the supplier that provides the exception to be thrown if validation
   *     fails
   * @throws RuntimeException if the expression is {@code false}; the thrown exception is provided
   *     by {@code exceptionSupplier}
   */
  @Contract("false, _ -> fail")
  static void isTrue(boolean expression, Supplier<? extends RuntimeException> exceptionSupplier) {
    if (!expression) {
      throw getSupplierOrThrow(exceptionSupplier);
    }
  }

  /**
   * Ensures that the provided boolean expression is {@code false}.
   *
   * @param expression the boolean expression to check
   * @param exceptionSupplier the supplier that provides the exception to be thrown if validation
   *     fails
   * @throws RuntimeException if the expression is {@code true}; the thrown exception is provided by
   *     {@code exceptionSupplier}
   */
  @Contract("true, _ -> fail")
  static void isFalse(boolean expression, Supplier<? extends RuntimeException> exceptionSupplier) {
    if (expression) {
      throw getSupplierOrThrow(exceptionSupplier);
    }
  }
}
