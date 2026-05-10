package io.github.mangila.ensure4j.ops;

import static io.github.mangila.ensure4j.internal.EnsureUtils.getSupplierOrThrow;
import static io.github.mangila.ensure4j.internal.EnsureUtils.isNull;

import io.github.mangila.ensure4j.EnsureException;
import java.util.function.Supplier;

/**
 * Provides utility methods for validating and operating on primitive arrays. This enum implements
 * singleton behavior, ensuring a single instance is used throughout.
 */
public enum EnsureArrayOps {
  INSTANCE;

  /**
   * Ensures that the provided array is not null or empty.
   *
   * @param <T> the component type of the array
   * @param array the array to check
   * @return the provided array if it is not null or empty
   * @throws EnsureException if the array is null or empty, with the message {@code "array must not
   *     be empty"}
   * @see #notEmpty(Object[], String)
   * @see #notEmpty(Object[], Supplier)
   */
  public <T> T[] notEmpty(T[] array) throws EnsureException {
    return notEmpty(array, "array must not be empty");
  }

  /**
   * Ensures that the provided array is not null or empty.
   *
   * @param <T> the component type of the array
   * @param array the array to check
   * @param exceptionMessage the message to include in the exception if validation fails
   * @return the provided array if it is not null or empty
   * @throws EnsureException if the array is null or empty, with the provided message
   * @see #notEmpty(Object[])
   * @see #notEmpty(Object[], Supplier)
   */
  public <T> T[] notEmpty(T[] array, String exceptionMessage) throws EnsureException {
    return notEmpty(array, () -> EnsureException.of(exceptionMessage));
  }

  /**
   * Ensures that the provided array is not null or empty.
   *
   * @param <T> the component type of the array
   * @param array the array to check
   * @param exceptionSupplier the supplier that provides the exception to be thrown if validation
   *     fails
   * @return the provided array if it is not null or empty
   * @throws RuntimeException if the array is null or empty; the thrown exception is provided by
   *     {@code exceptionSupplier}
   * @see #notEmpty(Object[])
   * @see #notEmpty(Object[], String)
   */
  public <T> T[] notEmpty(T[] array, Supplier<? extends RuntimeException> exceptionSupplier) {
    if (isNull(array) || array.length == 0) {
      throw getSupplierOrThrow(exceptionSupplier);
    }
    return array;
  }
}
