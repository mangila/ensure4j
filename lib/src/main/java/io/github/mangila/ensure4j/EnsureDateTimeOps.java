package io.github.mangila.ensure4j;

import static io.github.mangila.ensure4j.EnsureUtils.getSupplierOrThrow;

import java.time.Instant;
import java.util.function.Supplier;
import org.jetbrains.annotations.Contract;

final class EnsureDateTimeOps {

  static final String INSTANT_MUST_BE_IN_FUTURE = "instant must be in the future";
  static final String INSTANT_MUST_BE_PAST_OR_PRESENT = "instant must be in the past or present";

  private EnsureDateTimeOps() {
    throw new AssertionError("No Ensure4j for you!");
  }

  /**
   * Ensures that the provided instant is in the future.
   *
   * @param instant the instant to check
   * @param boundary the boundary instant
   * @param exceptionSupplier the supplier that provides the exception to be thrown if validation
   *     fails
   * @return the provided instant if it is in the future
   * @throws RuntimeException if the instant is not in the future; the thrown exception is provided
   *     by {@code exceptionSupplier}
   */
  @Contract("null, _, _ -> fail; _, null, _ -> fail; !null, !null, _ -> param1")
  static Instant future(
      Instant instant, Instant boundary, Supplier<? extends RuntimeException> exceptionSupplier) {
    if (!EnsureUtils.isFuture(instant, boundary)) {
      throw getSupplierOrThrow(exceptionSupplier);
    }
    return instant;
  }

  /**
   * Ensures that the provided instant is in the past or present.
   *
   * @param instant the instant to check
   * @param boundary the boundary instant
   * @param exceptionSupplier the supplier that provides the exception to be thrown if validation
   *     fails
   * @return the provided instant if it is in the past or present
   * @throws RuntimeException if the instant is not in the past or present; the thrown exception is
   *     provided by {@code exceptionSupplier}
   */
  @Contract("null, _, _ -> fail; _, null, _ -> fail; !null, !null, _ -> param1")
  static Instant pastOrPresent(
      Instant instant, Instant boundary, Supplier<? extends RuntimeException> exceptionSupplier) {
    if (!EnsureUtils.isPastOrPresent(instant, boundary)) {
      throw getSupplierOrThrow(exceptionSupplier);
    }
    return instant;
  }
}
