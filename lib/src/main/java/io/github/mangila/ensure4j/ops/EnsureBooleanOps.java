package io.github.mangila.ensure4j.ops;

import static io.github.mangila.ensure4j.internal.EnsureUtils.getSupplierOrThrow;

import io.github.mangila.ensure4j.EnsureException;
import java.util.function.Supplier;

/**
 * Provides utility methods for validating and operating on booleans. This enum implements singleton
 * behavior, ensuring a single instance is used throughout.
 */
public enum EnsureBooleanOps {

  /**
   * Access point for the {@code EnsureBooleanOps} singleton. Use this instance to perform boolean
   * operations.
   */
  INSTANCE;

  /**
   * Ensures that the provided boolean value is true.
   *
   * @param expression the boolean value to check
   * @throws EnsureException if the boolean value is false, with the message {@code "boolean must be
   *     true"}
   * @see #isTrue(boolean, String)
   * @see #isTrue(boolean, Supplier)
   */
  public void isTrue(boolean expression) throws EnsureException {
    isTrue(expression, "boolean must be true");
  }

  /**
   * Ensures that the provided boolean value is true.
   *
   * @param expression the boolean value to check
   * @param exceptionMessage the message to include in the exception if validation fails
   * @throws EnsureException if the boolean value is false, with the provided message
   * @see #isTrue(boolean)
   * @see #isTrue(boolean, Supplier)
   */
  public void isTrue(boolean expression, String exceptionMessage) throws EnsureException {
    isTrue(expression, () -> EnsureException.of(exceptionMessage));
  }

  /**
   * Ensures that the provided boolean value is true.
   *
   * @param expression the boolean value to check
   * @param exceptionSupplier the supplier that provides the exception to be thrown if validation
   *     fails
   * @throws RuntimeException if the boolean value is false; the thrown exception is provided by
   *     {@code exceptionSupplier}
   * @see #isTrue(boolean)
   * @see #isTrue(boolean, String)
   */
  public void isTrue(boolean expression, Supplier<? extends RuntimeException> exceptionSupplier) {
    if (!expression) {
      throw getSupplierOrThrow(exceptionSupplier);
    }
  }

  /**
   * Ensures that the provided boolean value is false.
   *
   * @param expression the boolean value to check
   * @throws EnsureException if the boolean value is true, with the message {@code "boolean must be
   *     false"}
   * @see #isFalse(boolean, String)
   * @see #isFalse(boolean, Supplier)
   */
  public void isFalse(boolean expression) throws EnsureException {
    isFalse(expression, "boolean must be false");
  }

  /**
   * Ensures that the provided boolean value is false.
   *
   * @param expression the boolean value to check
   * @param exceptionMessage the message to include in the exception if validation fails
   * @throws EnsureException if the boolean value is true, with the provided message
   * @see #isFalse(boolean)
   * @see #isFalse(boolean, Supplier)
   */
  public void isFalse(boolean expression, String exceptionMessage) throws EnsureException {
    isFalse(expression, () -> EnsureException.of(exceptionMessage));
  }

  /**
   * Ensures that the provided boolean value is false.
   *
   * @param expression the boolean value to check
   * @param exceptionSupplier the supplier that provides the exception to be thrown if validation
   *     fails
   * @throws RuntimeException if the boolean value is true; the thrown exception is provided by
   *     {@code exceptionSupplier}
   * @see #isFalse(boolean)
   * @see #isFalse(boolean, String)
   */
  public void isFalse(boolean expression, Supplier<? extends RuntimeException> exceptionSupplier) {
    if (expression) {
      throw getSupplierOrThrow(exceptionSupplier);
    }
  }
}
