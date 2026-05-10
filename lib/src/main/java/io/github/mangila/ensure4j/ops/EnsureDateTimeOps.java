package io.github.mangila.ensure4j.ops;

import static io.github.mangila.ensure4j.internal.EnsureUtils.getSupplierOrThrow;
import static io.github.mangila.ensure4j.internal.EnsureUtils.isNull;

import io.github.mangila.ensure4j.EnsureException;
import java.time.Instant;
import java.util.function.Supplier;
import org.jetbrains.annotations.Contract;

/**
 * Provides utility methods for validating and operating on java date time API This enum implements
 * singleton behavior, ensuring a single instance is used throughout.
 */
public enum EnsureDateTimeOps {

  /**
   * Access point for the {@code EnsureDateTimeOps} singleton. Use this instance to perform date
   * time operations.
   */
  INSTANCE;

  /**
   * Ensures that the provided instant is in the future.
   *
   * @param instant the instant to check
   * @return the provided instant if it is in the future
   * @throws EnsureException if the instant is not in the future, with the message {@code "instant
   *     must be in the future"}
   * @see #isFuture(Instant, String)
   * @see #isFuture(Instant, Supplier)
   */
  @Contract("null -> fail; !null -> param1")
  public Instant isFuture(Instant instant) throws EnsureException {
    return isFuture(instant, "instant must be in the future");
  }

  /**
   * Ensures that the provided instant is in the future.
   *
   * @param instant the instant to check
   * @param exceptionMessage the message to include in the exception if validation fails
   * @return the provided instant if it is in the future
   * @throws EnsureException if the instant is not in the future, with the provided message
   * @see #isFuture(Instant)
   * @see #isFuture(Instant, Supplier)
   */
  @Contract("null, _ -> fail; !null, _ -> param1")
  public Instant isFuture(Instant instant, String exceptionMessage) throws EnsureException {
    return isFuture(instant, () -> EnsureException.of(exceptionMessage));
  }

  /**
   * Ensures that the provided instant is in the future.
   *
   * @param instant the instant to check
   * @param exceptionSupplier the supplier that provides the exception to be thrown if validation
   *     fails
   * @return the provided instant if it is in the future
   * @throws RuntimeException if the instant is not in the future; the thrown exception is provided
   *     by {@code exceptionSupplier}
   * @see #isFuture(Instant)
   * @see #isFuture(Instant, String)
   */
  @Contract("null, _ -> fail; !null, _ -> param1")
  public Instant isFuture(Instant instant, Supplier<? extends RuntimeException> exceptionSupplier) {
    if (isNull(instant) || !instant.isAfter(Instant.now())) {
      throw getSupplierOrThrow(exceptionSupplier);
    }
    return instant;
  }

  /**
   * Ensures that the provided instant is in the past or present.
   *
   * @param instant the instant to check
   * @return the provided instant if it is in the past or present
   * @throws EnsureException if the instant is not in the past or present, with the message {@code
   *     "instant must be in the past or present"}
   * @see #isPastOrPresent(Instant, String)
   * @see #isPastOrPresent(Instant, Supplier)
   */
  @Contract("null -> fail; !null -> param1")
  public Instant isPastOrPresent(Instant instant) throws EnsureException {
    return isPastOrPresent(instant, "instant must be in the past or present");
  }

  /**
   * Ensures that the provided instant is in the past or present.
   *
   * @param instant the instant to check
   * @param exceptionMessage the message to include in the exception if validation fails
   * @return the provided instant if it is in the past or present
   * @throws EnsureException if the instant is not in the past or present, with the provided message
   * @see #isPastOrPresent(Instant)
   * @see #isPastOrPresent(Instant, Supplier)
   */
  @Contract("null, _ -> fail; !null, _ -> param1")
  public Instant isPastOrPresent(Instant instant, String exceptionMessage) throws EnsureException {
    return isPastOrPresent(instant, () -> EnsureException.of(exceptionMessage));
  }

  /**
   * Ensures that the provided instant is in the past or present.
   *
   * @param instant the instant to check
   * @param exceptionSupplier the supplier that provides the exception to be thrown if validation
   *     fails
   * @return the provided instant if it is in the past or present
   * @throws RuntimeException if the instant is not in the past or present; the thrown exception is
   *     provided by {@code exceptionSupplier}
   * @see #isPastOrPresent(Instant)
   * @see #isPastOrPresent(Instant, String)
   */
  @Contract("null, _ -> fail; !null, _ -> param1")
  public Instant isPastOrPresent(
      Instant instant, Supplier<? extends RuntimeException> exceptionSupplier) {
    if (isNull(instant) || instant.isAfter(Instant.now())) {
      throw getSupplierOrThrow(exceptionSupplier);
    }
    return instant;
  }
}
