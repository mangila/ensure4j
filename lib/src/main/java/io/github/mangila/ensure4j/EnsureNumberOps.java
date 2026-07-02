package io.github.mangila.ensure4j;

import static io.github.mangila.ensure4j.EnsureUtils.getSupplierOrThrow;

import java.util.function.Supplier;
import org.jetbrains.annotations.Contract;

final class EnsureNumberOps {

  static final String NUMBER_MUST_BE_NEGATIVE_FORMAT = "number must be negative - %d";
  static final String NUMBER_MUST_BE_NEGATIVE_OR_ZERO_FORMAT =
      "number must be negative or zero - %d";
  static final String NUMBER_MUST_BE_POSITIVE_FORMAT = "number must be positive - %d";
  static final String NUMBER_MUST_BE_POSITIVE_OR_ZERO_FORMAT =
      "number must be positive or zero - %d";
  static final String NUMBER_NOT_GREATER_THAN_OR_EQUAL_FORMAT =
      "number must be greater than or equal to %d, but was %d";
  static final String NUMBER_NOT_LESS_THAN_OR_EQUAL_FORMAT =
      "number must be less than or equal to %d, but was %d";

  /**
   * Ensures that the provided number does not exceed the specified boundary.
   *
   * @param number the number to check
   * @param boundary the maximum allowed number
   * @param exceptionSupplier the supplier that provides the exception to be thrown if validation
   *     fails
   * @return the provided number if it does not exceed the boundary
   * @throws RuntimeException if the number exceeds the boundary; the thrown exception is provided
   *     by {@code exceptionSupplier}
   */
  @Contract("_, _, _ -> param1")
  static int max(int number, int boundary, Supplier<? extends RuntimeException> exceptionSupplier) {
    if (!EnsureUtils.isAtMost(number, boundary)) {
      throw getSupplierOrThrow(exceptionSupplier);
    }
    return number;
  }

  /**
   * Ensures that the provided number does not exceed the specified boundary.
   *
   * @param number the number to check
   * @param boundary the maximum allowed number
   * @param exceptionSupplier the supplier that provides the exception to be thrown if validation
   *     fails
   * @return the provided number if it does not exceed the boundary
   * @throws RuntimeException if the number exceeds the boundary; the thrown exception is provided
   *     by {@code exceptionSupplier}
   */
  @Contract("_, _, _ -> param1")
  static long max(
      long number, long boundary, Supplier<? extends RuntimeException> exceptionSupplier) {
    if (!EnsureUtils.isAtMost(number, boundary)) {
      throw getSupplierOrThrow(exceptionSupplier);
    }
    return number;
  }

  /**
   * Ensures that the provided number meets or exceeds the specified boundary.
   *
   * @param number the number to check
   * @param boundary the minimum allowed number
   * @param exceptionSupplier the supplier that provides the exception to be thrown if validation
   *     fails
   * @return the provided number if it meets or exceeds the boundary
   * @throws RuntimeException if the number is less than the boundary; the thrown exception is
   *     provided by {@code exceptionSupplier}
   */
  @Contract("_, _, _ -> param1")
  static int min(int number, int boundary, Supplier<? extends RuntimeException> exceptionSupplier) {
    if (!EnsureUtils.isAtLeast(number, boundary)) {
      throw getSupplierOrThrow(exceptionSupplier);
    }
    return number;
  }

  /**
   * Ensures that the provided number meets or exceeds the specified boundary.
   *
   * @param number the number to check
   * @param boundary the minimum allowed number
   * @param exceptionSupplier the supplier that provides the exception to be thrown if validation
   *     fails
   * @return the provided number if it meets or exceeds the boundary
   * @throws RuntimeException if the number is less than the boundary; the thrown exception is
   *     provided by {@code exceptionSupplier}
   */
  @Contract("_, _, _ -> param1")
  static long min(
      long number, long boundary, Supplier<? extends RuntimeException> exceptionSupplier) {
    if (!EnsureUtils.isAtLeast(number, boundary)) {
      throw getSupplierOrThrow(exceptionSupplier);
    }
    return number;
  }

  /**
   * Ensures that the provided number is negative.
   *
   * @param number the number to check
   * @param exceptionSupplier the supplier that provides the exception to be thrown if validation
   *     fails
   * @return the provided number if it is negative
   * @throws RuntimeException if the number is not negative; the thrown exception is provided by
   *     {@code exceptionSupplier}
   */
  @Contract("_, _ -> param1")
  static int negative(int number, Supplier<? extends RuntimeException> exceptionSupplier) {
    if (!EnsureUtils.isNegative(number)) {
      throw getSupplierOrThrow(exceptionSupplier);
    }
    return number;
  }

  /**
   * Ensures that the provided number is negative.
   *
   * @param number the number to check
   * @param exceptionSupplier the supplier that provides the exception to be thrown if validation
   *     fails
   * @return the provided number if it is negative
   * @throws RuntimeException if the number is not negative; the thrown exception is provided by
   *     {@code exceptionSupplier}
   */
  @Contract("_, _ -> param1")
  static long negative(long number, Supplier<? extends RuntimeException> exceptionSupplier) {
    if (!EnsureUtils.isNegative(number)) {
      throw getSupplierOrThrow(exceptionSupplier);
    }
    return number;
  }

  /**
   * Ensures that the provided number is negative or zero.
   *
   * @param number the number to check
   * @param exceptionSupplier the supplier that provides the exception to be thrown if validation
   *     fails
   * @return the provided number if it is negative or zero
   * @throws RuntimeException if the number is positive; the thrown exception is provided by {@code
   *     exceptionSupplier}
   */
  @Contract("_, _ -> param1")
  static int negativeWithZero(int number, Supplier<? extends RuntimeException> exceptionSupplier) {
    if (!EnsureUtils.isNegativeWithZero(number)) {
      throw getSupplierOrThrow(exceptionSupplier);
    }
    return number;
  }

  /**
   * Ensures that the provided number is negative or zero.
   *
   * @param number the number to check
   * @param exceptionSupplier the supplier that provides the exception to be thrown if validation
   *     fails
   * @return the provided number if it is negative or zero
   * @throws RuntimeException if the number is positive; the thrown exception is provided by {@code
   *     exceptionSupplier}
   */
  @Contract("_, _ -> param1")
  static long negativeWithZero(
      long number, Supplier<? extends RuntimeException> exceptionSupplier) {
    if (!EnsureUtils.isNegativeWithZero(number)) {
      throw getSupplierOrThrow(exceptionSupplier);
    }
    return number;
  }

  /**
   * Ensures that the provided number is positive.
   *
   * @param number the number to check
   * @param exceptionSupplier the supplier that provides the exception to be thrown if validation
   *     fails
   * @return the provided number if it is positive
   * @throws RuntimeException if the number is not positive; the thrown exception is provided by
   *     {@code exceptionSupplier}
   */
  @Contract("_, _ -> param1")
  static int positive(int number, Supplier<? extends RuntimeException> exceptionSupplier) {
    if (!EnsureUtils.isPositive(number)) {
      throw getSupplierOrThrow(exceptionSupplier);
    }
    return number;
  }

  /**
   * Ensures that the provided number is positive.
   *
   * @param number the number to check
   * @param exceptionSupplier the supplier that provides the exception to be thrown if validation
   *     fails
   * @return the provided number if it is positive
   * @throws RuntimeException if the number is not positive; the thrown exception is provided by
   *     {@code exceptionSupplier}
   */
  @Contract("_, _ -> param1")
  static long positive(long number, Supplier<? extends RuntimeException> exceptionSupplier) {
    if (!EnsureUtils.isPositive(number)) {
      throw getSupplierOrThrow(exceptionSupplier);
    }
    return number;
  }

  /**
   * Ensures that the provided number is positive or zero.
   *
   * @param number the number to check
   * @param exceptionSupplier the supplier that provides the exception to be thrown if validation
   *     fails
   * @return the provided number if it is positive or zero
   * @throws RuntimeException if the number is negative; the thrown exception is provided by {@code
   *     exceptionSupplier}
   */
  @Contract("_, _ -> param1")
  static int positiveWithZero(int number, Supplier<? extends RuntimeException> exceptionSupplier) {
    if (!EnsureUtils.isPositiveWithZero(number)) {
      throw getSupplierOrThrow(exceptionSupplier);
    }
    return number;
  }

  /**
   * Ensures that the provided number is positive or zero.
   *
   * @param number the number to check
   * @param exceptionSupplier the supplier that provides the exception to be thrown if validation
   *     fails
   * @return the provided number if it is positive or zero
   * @throws RuntimeException if the number is negative; the thrown exception is provided by {@code
   *     exceptionSupplier}
   */
  @Contract("_, _ -> param1")
  static long positiveWithZero(
      long number, Supplier<? extends RuntimeException> exceptionSupplier) {
    if (!EnsureUtils.isPositiveWithZero(number)) {
      throw getSupplierOrThrow(exceptionSupplier);
    }
    return number;
  }

  private EnsureNumberOps() {
    throw new AssertionError("No Ensure4j for you!");
  }
}
