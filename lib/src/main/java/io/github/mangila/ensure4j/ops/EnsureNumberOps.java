package io.github.mangila.ensure4j.ops;

import static io.github.mangila.ensure4j.internal.EnsureUtils.getSupplierOrThrow;

import io.github.mangila.ensure4j.EnsureException;
import java.util.function.Supplier;

/**
 * Provides utility methods for validating and operating on numbers. This enum implements singleton
 * behavior, ensuring a single instance is used throughout.
 */
public enum EnsureNumberOps {
  INSTANCE;

  /**
   * Ensures that the provided value does not exceed the specified boundary.
   *
   * @param boundary the maximum allowed value
   * @param value the value to check
   * @return the provided value if it does not exceed the boundary
   * @throws EnsureException if the value exceeds the boundary, with the message {@code "value must
   *     be less than or equal to %d, but was %d"}
   * @see #max(long, long, String)
   * @see #max(long, long, Supplier)
   */
  public long max(long boundary, long value) throws EnsureException {
    return max(
        boundary,
        value,
        "value must be less than or equal to %d, but was %d".formatted(boundary, value));
  }

  /**
   * Ensures that the provided value does not exceed the specified boundary.
   *
   * @param boundary the maximum allowed value
   * @param value the value to check
   * @param exceptionMessage the message to include in the exception if validation fails
   * @return the provided value if it does not exceed the boundary
   * @throws EnsureException if the value exceeds the boundary, with the provided message
   * @see #max(long, long)
   * @see #max(long, long, Supplier)
   */
  public long max(long boundary, long value, String exceptionMessage) throws EnsureException {
    return max(boundary, value, () -> EnsureException.of(exceptionMessage));
  }

  /**
   * Ensures that the provided value does not exceed the specified boundary.
   *
   * @param boundary the maximum allowed value
   * @param value the value to check
   * @param exceptionSupplier the supplier that provides the exception to be thrown if validation
   *     fails
   * @return the provided value if it does not exceed the boundary
   * @throws RuntimeException if the value exceeds the boundary; the thrown exception is provided by
   *     {@code exceptionSupplier}
   * @see #max(long, long)
   * @see #max(long, long, String)
   */
  public long max(long boundary, long value, Supplier<? extends RuntimeException> exceptionSupplier)
      throws RuntimeException {
    if (value > boundary) {
      throw getSupplierOrThrow(exceptionSupplier);
    }
    return value;
  }

  /**
   * Ensures that the provided value does not exceed the specified boundary.
   *
   * @param boundary the maximum allowed value
   * @param value the value to check
   * @return the provided value if it does not exceed the boundary
   * @throws EnsureException if the value exceeds the boundary, with the message {@code "value must
   *     be less than or equal to %d, but was %d"}
   * @see #max(int, int, String)
   * @see #max(int, int, Supplier)
   */
  public int max(int boundary, int value) throws EnsureException {
    return max(
        boundary,
        value,
        "value must be less than or equal to %d, but was %d".formatted(boundary, value));
  }

  /**
   * Ensures that the provided value does not exceed the specified boundary.
   *
   * @param boundary the maximum allowed value
   * @param value the value to check
   * @param exceptionMessage the message to include in the exception if validation fails
   * @return the provided value if it does not exceed the boundary
   * @throws EnsureException if the value exceeds the boundary, with the provided message
   * @see #max(int, int)
   * @see #max(int, int, Supplier)
   */
  public int max(int boundary, int value, String exceptionMessage) throws EnsureException {
    return max(boundary, value, () -> EnsureException.of(exceptionMessage));
  }

  /**
   * Ensures that the provided value does not exceed the specified boundary.
   *
   * @param boundary the maximum allowed value
   * @param value the value to check
   * @param exceptionSupplier the supplier that provides the exception to be thrown if validation
   *     fails
   * @return the provided value if it does not exceed the boundary
   * @throws RuntimeException if the value exceeds the boundary; the thrown exception is provided by
   *     {@code exceptionSupplier}
   * @see #max(int, int)
   * @see #max(int, int, String)
   */
  public int max(int boundary, int value, Supplier<? extends RuntimeException> exceptionSupplier)
      throws RuntimeException {
    if (value > boundary) {
      throw getSupplierOrThrow(exceptionSupplier);
    }
    return value;
  }

  /**
   * Ensures that the provided value meets or exceeds the specified boundary.
   *
   * @param boundary the minimum allowed value
   * @param value the value to check
   * @return the provided value if it meets or exceeds the boundary
   * @throws EnsureException if the value is less than the boundary, with the message {@code "value
   *     must be greater than or equal to %d, but was %d"}
   * @see #min(long, long, String)
   * @see #min(long, long, Supplier)
   */
  public long min(long boundary, long value) throws EnsureException {
    return min(
        boundary,
        value,
        "value must be greater than or equal to %d, but was %d".formatted(boundary, value));
  }

  /**
   * Ensures that the provided value meets or exceeds the specified boundary.
   *
   * @param boundary the minimum allowed value
   * @param value the value to check
   * @param exceptionMessage the message to include in the exception if validation fails
   * @return the provided value if it meets or exceeds the boundary
   * @throws EnsureException if the value is less than the boundary, with the provided message
   * @see #min(long, long)
   * @see #min(long, long, Supplier)
   */
  public long min(long boundary, long value, String exceptionMessage) throws EnsureException {
    return min(boundary, value, () -> EnsureException.of(exceptionMessage));
  }

  /**
   * Ensures that the provided value meets or exceeds the specified boundary.
   *
   * @param boundary the minimum allowed value
   * @param value the value to check
   * @param exceptionSupplier the supplier that provides the exception to be thrown if validation
   *     fails
   * @return the provided value if it meets or exceeds the boundary
   * @throws RuntimeException if the value is less than the boundary; the thrown exception is
   *     provided by {@code exceptionSupplier}
   * @see #min(long, long)
   * @see #min(long, long, String)
   */
  public long min(long boundary, long value, Supplier<? extends RuntimeException> exceptionSupplier)
      throws RuntimeException {
    if (value < boundary) {
      throw getSupplierOrThrow(exceptionSupplier);
    }
    return value;
  }

  /**
   * Ensures that the provided value meets or exceeds the specified boundary.
   *
   * @param boundary the minimum allowed value
   * @param value the value to check
   * @return the provided value if it meets or exceeds the boundary
   * @throws EnsureException if the value is less than the boundary, with the message {@code "value
   *     must be greater than or equal to %d, but was %d"}
   * @see #min(int, int, String)
   * @see #min(int, int, Supplier)
   */
  public int min(int boundary, int value) throws EnsureException {
    return min(
        boundary,
        value,
        "value must be greater than or equal to %d, but was %d".formatted(boundary, value));
  }

  /**
   * Ensures that the provided value meets or exceeds the specified boundary.
   *
   * @param boundary the minimum allowed value
   * @param value the value to check
   * @param exceptionMessage the message to include in the exception if validation fails
   * @return the provided value if it meets or exceeds the boundary
   * @throws EnsureException if the value is less than the boundary, with the provided message
   * @see #min(int, int)
   * @see #min(int, int, Supplier)
   */
  public int min(int boundary, int value, String exceptionMessage) throws EnsureException {
    return min(boundary, value, () -> EnsureException.of(exceptionMessage));
  }

  /**
   * Ensures that the provided value meets or exceeds the specified boundary.
   *
   * @param boundary the minimum allowed value
   * @param value the value to check
   * @param exceptionSupplier the supplier that provides the exception to be thrown if validation
   *     fails
   * @return the provided value if it meets or exceeds the boundary
   * @throws RuntimeException if the value is less than the boundary; the thrown exception is
   *     provided by {@code exceptionSupplier}
   * @see #min(int, int)
   * @see #min(int, int, String)
   */
  public int min(int boundary, int value, Supplier<? extends RuntimeException> exceptionSupplier)
      throws RuntimeException {
    if (value < boundary) {
      throw getSupplierOrThrow(exceptionSupplier);
    }
    return value;
  }

  /**
   * Ensures that the provided value is positive.
   *
   * @param value the value to check
   * @return the provided value if it is positive
   * @throws EnsureException if the value is not positive, with the message {@code "value must be
   *     positive - (%s)"}
   * @see #positive(long, String)
   * @see #positive(long, Supplier)
   */
  public long positive(long value) throws EnsureException {
    return positive(value, "value must be positive - (%s)".formatted(value));
  }

  /**
   * Ensures that the provided value is positive.
   *
   * @param value the value to check
   * @param exceptionMessage the message to include in the exception if validation fails
   * @return the provided value if it is positive
   * @throws EnsureException if the value is not positive, with the provided message
   * @see #positive(long)
   * @see #positive(long, Supplier)
   */
  public long positive(long value, String exceptionMessage) throws EnsureException {
    return positive(value, () -> EnsureException.of(exceptionMessage));
  }

  /**
   * Ensures that the provided value is positive.
   *
   * @param value the value to check
   * @param exceptionSupplier the supplier that provides the exception to be thrown if validation
   *     fails
   * @return the provided value if it is positive
   * @throws RuntimeException if the value is not positive; the thrown exception is provided by
   *     {@code exceptionSupplier}
   * @see #positive(long)
   * @see #positive(long, String)
   */
  public long positive(long value, Supplier<? extends RuntimeException> exceptionSupplier)
      throws RuntimeException {
    if (value <= 0) {
      throw getSupplierOrThrow(exceptionSupplier);
    }
    return value;
  }

  /**
   * Ensures that the provided value is positive.
   *
   * @param value the value to check
   * @return the provided value if it is positive
   * @throws EnsureException if the value is not positive, with the message {@code "value must be
   *     positive - (%s)"}
   * @see #positive(int, String)
   * @see #positive(int, Supplier)
   */
  public int positive(int value) throws EnsureException {
    return positive(value, "value must be positive - (%s)".formatted(value));
  }

  /**
   * Ensures that the provided value is positive.
   *
   * @param value the value to check
   * @param exceptionMessage the message to include in the exception if validation fails
   * @return the provided value if it is positive
   * @throws EnsureException if the value is not positive, with the provided message
   * @see #positive(int)
   * @see #positive(int, Supplier)
   */
  public int positive(int value, String exceptionMessage) throws EnsureException {
    return positive(value, () -> EnsureException.of(exceptionMessage));
  }

  /**
   * Ensures that the provided value is positive.
   *
   * @param value the value to check
   * @param exceptionSupplier the supplier that provides the exception to be thrown if validation
   *     fails
   * @return the provided value if it is positive
   * @throws RuntimeException if the value is not positive; the thrown exception is provided by
   *     {@code exceptionSupplier}
   * @see #positive(int)
   * @see #positive(int, String)
   */
  public int positive(int value, Supplier<? extends RuntimeException> exceptionSupplier)
      throws RuntimeException {
    if (value <= 0) {
      throw getSupplierOrThrow(exceptionSupplier);
    }
    return value;
  }

  /**
   * Ensures that the provided value is positive or zero.
   *
   * @param value the value to check
   * @return the provided value if it is positive or zero
   * @throws EnsureException if the value is negative, with the message {@code "value must be
   *     positive or zero - (%s)"}
   * @see #positiveWithZero(long, String)
   * @see #positiveWithZero(long, Supplier)
   */
  public long positiveWithZero(long value) throws EnsureException {
    return positiveWithZero(value, "value must be positive or zero - (%s)".formatted(value));
  }

  /**
   * Ensures that the provided value is positive or zero.
   *
   * @param value the value to check
   * @param exceptionMessage the message to include in the exception if validation fails
   * @return the provided value if it is positive or zero
   * @throws EnsureException if the value is negative, with the provided message
   * @see #positiveWithZero(long)
   * @see #positiveWithZero(long, Supplier)
   */
  public long positiveWithZero(long value, String exceptionMessage) throws EnsureException {
    return positiveWithZero(value, () -> EnsureException.of(exceptionMessage));
  }

  /**
   * Ensures that the provided value is positive or zero.
   *
   * @param value the value to check
   * @param exceptionSupplier the supplier that provides the exception to be thrown if validation
   *     fails
   * @return the provided value if it is positive or zero
   * @throws RuntimeException if the value is negative; the thrown exception is provided by {@code
   *     exceptionSupplier}
   * @see #positiveWithZero(long)
   * @see #positiveWithZero(long, String)
   */
  public long positiveWithZero(long value, Supplier<? extends RuntimeException> exceptionSupplier)
      throws RuntimeException {
    if (value < 0) {
      throw getSupplierOrThrow(exceptionSupplier);
    }
    return value;
  }

  /**
   * Ensures that the provided value is positive or zero.
   *
   * @param value the value to check
   * @return the provided value if it is positive or zero
   * @throws EnsureException if the value is negative, with the message {@code "value must be
   *     positive or zero - (%s)"}
   * @see #positiveWithZero(int, String)
   * @see #positiveWithZero(int, Supplier)
   */
  public int positiveWithZero(int value) throws EnsureException {
    return positiveWithZero(value, "value must be positive or zero - (%s)".formatted(value));
  }

  /**
   * Ensures that the provided value is positive or zero.
   *
   * @param value the value to check
   * @param exceptionMessage the message to include in the exception if validation fails
   * @return the provided value if it is positive or zero
   * @throws EnsureException if the value is negative, with the provided message
   * @see #positiveWithZero(int)
   * @see #positiveWithZero(int, Supplier)
   */
  public int positiveWithZero(int value, String exceptionMessage) throws EnsureException {
    return positiveWithZero(value, () -> EnsureException.of(exceptionMessage));
  }

  /**
   * Ensures that the provided value is positive or zero.
   *
   * @param value the value to check
   * @param exceptionSupplier the supplier that provides the exception to be thrown if validation
   *     fails
   * @return the provided value if it is positive or zero
   * @throws RuntimeException if the value is negative; the thrown exception is provided by {@code
   *     exceptionSupplier}
   * @see #positiveWithZero(int)
   * @see #positiveWithZero(int, String)
   */
  public int positiveWithZero(int value, Supplier<? extends RuntimeException> exceptionSupplier)
      throws RuntimeException {
    if (value < 0) {
      throw getSupplierOrThrow(exceptionSupplier);
    }
    return value;
  }

  /**
   * Ensures that the provided value is negative.
   *
   * @param value the value to check
   * @return the provided value if it is negative
   * @throws EnsureException if the value is not negative, with the message {@code "value must be
   *     negative - (%s)"}
   * @see #negative(long, String)
   * @see #negative(long, Supplier)
   */
  public long negative(long value) throws EnsureException {
    return negative(value, "value must be negative - (%s)".formatted(value));
  }

  /**
   * Ensures that the provided value is negative.
   *
   * @param value the value to check
   * @param exceptionMessage the message to include in the exception if validation fails
   * @return the provided value if it is negative
   * @throws EnsureException if the value is not negative, with the provided message
   * @see #negative(long)
   * @see #negative(long, Supplier)
   */
  public long negative(long value, String exceptionMessage) throws EnsureException {
    return negative(value, () -> EnsureException.of(exceptionMessage));
  }

  /**
   * Ensures that the provided value is negative.
   *
   * @param value the value to check
   * @param exceptionSupplier the supplier that provides the exception to be thrown if validation
   *     fails
   * @return the provided value if it is negative
   * @throws RuntimeException if the value is not negative; the thrown exception is provided by
   *     {@code exceptionSupplier}
   * @see #negative(long)
   * @see #negative(long, String)
   */
  public long negative(long value, Supplier<? extends RuntimeException> exceptionSupplier)
      throws RuntimeException {
    if (value >= 0) {
      throw getSupplierOrThrow(exceptionSupplier);
    }
    return value;
  }

  /**
   * Ensures that the provided value is negative.
   *
   * @param value the value to check
   * @return the provided value if it is negative
   * @throws EnsureException if the value is not negative, with the message {@code "value must be
   *     negative - (%s)"}
   * @see #negative(int, String)
   * @see #negative(int, Supplier)
   */
  public int negative(int value) throws EnsureException {
    return negative(value, "value must be negative - (%s)".formatted(value));
  }

  /**
   * Ensures that the provided value is negative.
   *
   * @param value the value to check
   * @param exceptionMessage the message to include in the exception if validation fails
   * @return the provided value if it is negative
   * @throws EnsureException if the value is not negative, with the provided message
   * @see #negative(int)
   * @see #negative(int, Supplier)
   */
  public int negative(int value, String exceptionMessage) throws EnsureException {
    return negative(value, () -> EnsureException.of(exceptionMessage));
  }

  /**
   * Ensures that the provided value is negative.
   *
   * @param value the value to check
   * @param exceptionSupplier the supplier that provides the exception to be thrown if validation
   *     fails
   * @return the provided value if it is negative
   * @throws RuntimeException if the value is not negative; the thrown exception is provided by
   *     {@code exceptionSupplier}
   * @see #negative(int)
   * @see #negative(int, String)
   */
  public int negative(int value, Supplier<? extends RuntimeException> exceptionSupplier)
      throws RuntimeException {
    if (value >= 0) {
      throw getSupplierOrThrow(exceptionSupplier);
    }
    return value;
  }

  /**
   * Ensures that the provided value is negative or zero.
   *
   * @param value the value to check
   * @return the provided value if it is negative or zero
   * @throws EnsureException if the value is positive, with the message {@code "value must be
   *     negative or zero - (%s)"}
   * @see #negativeWithZero(long, String)
   * @see #negativeWithZero(long, Supplier)
   */
  public long negativeWithZero(long value) throws EnsureException {
    return negativeWithZero(value, "value must be negative or zero - (%s)".formatted(value));
  }

  /**
   * Ensures that the provided value is negative or zero.
   *
   * @param value the value to check
   * @param exceptionMessage the message to include in the exception if validation fails
   * @return the provided value if it is negative or zero
   * @throws EnsureException if the value is positive, with the provided message
   * @see #negativeWithZero(long)
   * @see #negativeWithZero(long, Supplier)
   */
  public long negativeWithZero(long value, String exceptionMessage) throws EnsureException {
    return negativeWithZero(value, () -> EnsureException.of(exceptionMessage));
  }

  /**
   * Ensures that the provided value is negative or zero.
   *
   * @param value the value to check
   * @param exceptionSupplier the supplier that provides the exception to be thrown if validation
   *     fails
   * @return the provided value if it is negative or zero
   * @throws RuntimeException if the value is positive; the thrown exception is provided by {@code
   *     exceptionSupplier}
   * @see #negativeWithZero(long)
   * @see #negativeWithZero(long, String)
   */
  public long negativeWithZero(long value, Supplier<? extends RuntimeException> exceptionSupplier)
      throws RuntimeException {
    if (value > 0) {
      throw getSupplierOrThrow(exceptionSupplier);
    }
    return value;
  }

  /**
   * Ensures that the provided value is negative or zero.
   *
   * @param value the value to check
   * @return the provided value if it is negative or zero
   * @throws EnsureException if the value is positive, with the message {@code "value must be
   *     negative or zero - (%s)"}
   * @see #negativeWithZero(int, String)
   * @see #negativeWithZero(int, Supplier)
   */
  public int negativeWithZero(int value) throws EnsureException {
    return negativeWithZero(value, "value must be negative or zero - (%s)".formatted(value));
  }

  /**
   * Ensures that the provided value is negative or zero.
   *
   * @param value the value to check
   * @param exceptionMessage the message to include in the exception if validation fails
   * @return the provided value if it is negative or zero
   * @throws EnsureException if the value is positive, with the provided message
   * @see #negativeWithZero(int)
   * @see #negativeWithZero(int, Supplier)
   */
  public int negativeWithZero(int value, String exceptionMessage) throws EnsureException {
    return negativeWithZero(value, () -> EnsureException.of(exceptionMessage));
  }

  /**
   * Ensures that the provided value is negative or zero.
   *
   * @param value the value to check
   * @param exceptionSupplier the supplier that provides the exception to be thrown if validation
   *     fails
   * @return the provided value if it is negative or zero
   * @throws RuntimeException if the value is positive; the thrown exception is provided by {@code
   *     exceptionSupplier}
   * @see #negativeWithZero(int)
   * @see #negativeWithZero(int, String)
   */
  public int negativeWithZero(int value, Supplier<? extends RuntimeException> exceptionSupplier)
      throws RuntimeException {
    if (value > 0) {
      throw getSupplierOrThrow(exceptionSupplier);
    }
    return value;
  }
}
