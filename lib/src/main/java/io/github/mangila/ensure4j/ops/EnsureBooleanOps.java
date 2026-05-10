package io.github.mangila.ensure4j.ops;

import static io.github.mangila.ensure4j.internal.EnsureUtils.getSupplierOrThrow;

import io.github.mangila.ensure4j.EnsureException;
import java.util.function.Supplier;

/**
 * Provides utility methods for validating and operating on booleans. This enum implements singleton
 * behavior, ensuring a single instance is used throughout.
 */
public enum EnsureBooleanOps {
  INSTANCE;

  /**
   * Validates that the given boolean expression is true. If the expression evaluates to false, a
   * {@link RuntimeException} provided by the specified {@link Supplier} is thrown.
   *
   * @param expression the boolean expression to be evaluated
   * @param runtimeExceptionSupplier the runtimeExceptionSupplier responsible for providing the
   *     {@link RuntimeException} to be thrown if {@code expression} evaluates to false
   * @throws RuntimeException if {@code expression} evaluates to false, with the exception derived
   *     from the {@code runtimeExceptionSupplier}
   */
  public void isTrue(boolean expression, Supplier<RuntimeException> runtimeExceptionSupplier)
      throws RuntimeException {
    if (!expression) {
      throw getSupplierOrThrow(runtimeExceptionSupplier);
    }
  }

  /**
   * Ensures that the provided expression evaluates to {@code true}. If the expression evaluates to
   * {@code false}, an {@link EnsureException} is thrown with the provided exception message.
   *
   * @param expression the boolean expression to be evaluated
   * @param exceptionMessage the exception message to be included if the expression evaluates to
   *     {@code false}
   * @throws EnsureException if {@code expression} evaluates to {@code false}
   */
  public void isTrue(boolean expression, String exceptionMessage) throws EnsureException {
    isTrue(expression, () -> EnsureException.of(exceptionMessage));
  }

  /**
   * Ensures that the provided boolean value is true. If the value is false, an {@link
   * EnsureException} with a default message is thrown.
   *
   * @param expression the boolean value to check; must be true
   * @throws EnsureException with the message "boolean must be true" - if the provided value is
   *     false
   */
  public void isTrue(boolean expression) throws EnsureException {
    isTrue(expression, "boolean must be true");
  }

  /**
   * Ensures that the provided boolean value is false. If the value is true, a {@link
   * RuntimeException} provided by the given {@link Supplier} is thrown.
   *
   * @param expression the boolean value to check
   * @param runtimeExceptionSupplier the runtimeExceptionSupplier responsible for providing the
   *     {@link RuntimeException} to be thrown if {@code expression} is true
   * @throws RuntimeException if {@code expression} is true, with the exception derived from the
   *     {@code runtimeExceptionSupplier}
   */
  public void isFalse(boolean expression, Supplier<RuntimeException> runtimeExceptionSupplier)
      throws RuntimeException {
    if (expression) {
      throw getSupplierOrThrow(runtimeExceptionSupplier);
    }
  }

  /**
   * Ensures that the provided boolean value is false. If the value is true, an {@link
   * EnsureException} with the provided exception message is thrown.
   *
   * @param expression the boolean value to be checked
   * @param exceptionMessage the exception message to include if {@code expression} is true
   * @throws EnsureException if {@code expression} is true
   */
  public void isFalse(boolean expression, String exceptionMessage) throws EnsureException {
    isFalse(expression, () -> EnsureException.of(exceptionMessage));
  }

  /**
   * Ensures that the provided boolean value is false. If the value is true, an {@link
   * EnsureException} with a default message is thrown.
   *
   * @param expression the boolean value to be checked
   * @throws EnsureException with the message "boolean must be false" - if {@code expression} is
   *     true
   */
  public void isFalse(boolean expression) throws EnsureException {
    isFalse(expression, "boolean must be false");
  }
}
