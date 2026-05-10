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
   * Checks if the provided array is not empty. If the array is empty, throws an EnsureException
   * with the provided exception message. NOTE: Also check if null
   *
   * @param <T> the type of the array
   * @param array the array to check for non-emptiness
   * @param exceptionMessage the message to include in the exception if the array is empty
   * @return the array passed as input
   * @throws EnsureException if the array is null or empty
   */
  public <T> T[] notEmpty(T[] array, String exceptionMessage) throws EnsureException {
    return notEmpty(array, () -> EnsureException.of(exceptionMessage));
  }

  /**
   * Ensures that the provided array is not empty. If the array is empty, an EnsureException is
   * thrown. NOTE: Also check if null
   *
   * @param <T> the type of the array
   * @param array the array to be checked
   * @return the array passed as input
   * @throws EnsureException with the message "array must not be empty" - if the array is empty
   */
  public <T> T[] notEmpty(T[] array) throws EnsureException {
    return notEmpty(array, "array must not be empty");
  }

  /**
   * Validates that the given array is not empty. If the array is null or empty, the provided
   * runtimeExceptionSupplier is used to throw an exception. NOTE: Also check if null
   *
   * @param <T> the type of the array
   * @param array the array to check for non-emptiness
   * @param runtimeExceptionSupplier the runtimeExceptionSupplier providing the exception to be
   *     thrown if validation fails
   * @return the array passed as input
   * @throws RuntimeException if the array is null or empty
   */
  public <T> T[] notEmpty(T[] array, Supplier<RuntimeException> runtimeExceptionSupplier) {
    if (isNull(array) || array.length == 0) {
      throw getSupplierOrThrow(runtimeExceptionSupplier);
    }
    return array;
  }
}
