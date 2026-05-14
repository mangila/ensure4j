package io.github.mangila.ensure4j.ops;

import static io.github.mangila.ensure4j.internal.EnsureUtils.getSupplierOrThrow;
import static java.util.Objects.isNull;

import io.github.mangila.ensure4j.EnsureException;
import java.util.Arrays;
import java.util.function.Supplier;
import org.jetbrains.annotations.Contract;

/**
 * Provides utility methods for validating and operating on primitive arrays. This enum implements
 * singleton behavior, ensuring a single instance is used throughout.
 */
public enum EnsureArrayOps {
  /**
   * Access point for the {@code EnsureArrayOps} singleton. Use this instance to perform array
   * operations.
   */
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
  @Contract("null -> fail; !null -> param1")
  public <T> T[] notEmpty(T[] array) {
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
  @Contract("null, _ -> fail; !null, _ -> param1")
  public <T> T[] notEmpty(T[] array, String exceptionMessage) {
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
  @Contract("null, _ -> fail; !null, _ -> param1")
  public <T> T[] notEmpty(T[] array, Supplier<? extends RuntimeException> exceptionSupplier) {
    if (isNull(array) || array.length == 0) {
      throw getSupplierOrThrow(exceptionSupplier);
    }
    return array;
  }

  /**
   * Ensures that the provided arrays are equal.
   *
   * @param <T> the component type of the array
   * @param array the first array
   * @param otherArray the second array
   * @return the first array if they are equal
   * @throws EnsureException if the arrays are not equal, with the message {@code "arrays must be
   *     equal"}
   * @see #equalTo(Object[], Object[], String)
   * @see #equalTo(Object[], Object[], Supplier)
   */
  @Contract(
      "null, !null -> fail; !null, null -> fail; null, null -> param1; !null, !null -> param1")
  public <T> T[] equalTo(T[] array, T[] otherArray) {
    return equalTo(array, otherArray, "arrays must be equal");
  }

  /**
   * Ensures that the provided arrays are equal.
   *
   * @param <T> the component type of the array
   * @param array the first array
   * @param otherArray the second array
   * @param exceptionMessage the message to include in the exception if validation fails
   * @return the first array if they are equal
   * @throws EnsureException if the arrays are not equal, with the provided message
   * @see #equalTo(Object[], Object[])
   * @see #equalTo(Object[], Object[], Supplier)
   */
  @Contract(
      "null, !null, _ -> fail; !null, null, _ -> fail; null, null, _ -> param1; !null, !null, _ -> param1")
  public <T> T[] equalTo(T[] array, T[] otherArray, String exceptionMessage) {
    return equalTo(array, otherArray, () -> EnsureException.of(exceptionMessage));
  }

  /**
   * Ensures that the provided arrays are equal.
   *
   * @param <T> the component type of the array
   * @param array the first array
   * @param otherArray the second array
   * @param exceptionSupplier the supplier that provides the exception to be thrown if validation
   *     fails
   * @return the first array if they are equal
   * @throws RuntimeException if the arrays are not equal; the thrown exception is provided by
   *     {@code exceptionSupplier}
   * @see #equalTo(Object[], Object[])
   * @see #equalTo(Object[], Object[], String)
   */
  @Contract(
      "null, !null, _ -> fail; !null, null, _ -> fail; null, null, _ -> param1; !null, !null, _ -> param1")
  public <T> T[] equalTo(
      T[] array, T[] otherArray, Supplier<? extends RuntimeException> exceptionSupplier) {
    final boolean eq = Arrays.equals(array, otherArray);
    if (eq) {
      return array;
    } else {
      throw getSupplierOrThrow(exceptionSupplier);
    }
  }

  /**
   * Ensures that the provided arrays are deeply equal.
   *
   * @param <T> the component type of the array
   * @param array the first array
   * @param otherArray the second array
   * @return the first array if they are deeply equal
   * @throws EnsureException if the arrays are not deeply equal, with the message {@code "arrays
   *     must be deeply equal"}
   * @see #deepEqualTo(Object[], Object[], String)
   * @see #deepEqualTo(Object[], Object[], Supplier)
   */
  @Contract(
      "null, !null -> fail; !null, null -> fail; null, null -> param1; !null, !null -> param1")
  public <T> T[] deepEqualTo(T[] array, T[] otherArray) {
    return deepEqualTo(array, otherArray, "arrays must be deeply equal");
  }

  /**
   * Ensures that the provided arrays are deeply equal.
   *
   * @param <T> the component type of the array
   * @param array the first array
   * @param otherArray the second array
   * @param exceptionMessage the message to include in the exception if validation fails
   * @return the first array if they are deeply equal
   * @throws EnsureException if the arrays are not deeply equal, with the provided message
   * @see #deepEqualTo(Object[], Object[])
   * @see #deepEqualTo(Object[], Object[], Supplier)
   */
  @Contract(
      "null, !null, _ -> fail; !null, null, _ -> fail; null, null, _ -> param1; !null, !null, _ -> param1")
  public <T> T[] deepEqualTo(T[] array, T[] otherArray, String exceptionMessage) {
    return deepEqualTo(array, otherArray, () -> EnsureException.of(exceptionMessage));
  }

  /**
   * Ensures that the provided arrays are deeply equal.
   *
   * @param <T> the component type of the array
   * @param array the first array
   * @param otherArray the second array
   * @param exceptionSupplier the supplier that provides the exception to be thrown if validation
   *     fails
   * @return the first array if they are deeply equal
   * @throws RuntimeException if the arrays are not deeply equal; the thrown exception is provided
   *     by {@code exceptionSupplier}
   * @see #deepEqualTo(Object[], Object[])
   * @see #deepEqualTo(Object[], Object[], String)
   */
  @Contract(
      "null, !null, _ -> fail; !null, null, _ -> fail; null, null, _ -> param1; !null, !null, _ -> param1")
  public <T> T[] deepEqualTo(
      T[] array, T[] otherArray, Supplier<? extends RuntimeException> exceptionSupplier) {
    final boolean eq = Arrays.deepEquals(array, otherArray);
    if (eq) {
      return array;
    } else {
      throw getSupplierOrThrow(exceptionSupplier);
    }
  }
}
