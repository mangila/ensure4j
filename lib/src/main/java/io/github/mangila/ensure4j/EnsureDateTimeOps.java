/*
 * MIT License
 *
 * Copyright (c) 2025 Erik Olsson (olsson.erik1993@gmail.cm)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package io.github.mangila.ensure4j;

import static io.github.mangila.ensure4j.EnsureUtils.getSupplierOrThrow;

import java.time.Instant;
import java.util.function.Supplier;
import org.jetbrains.annotations.Contract;

final class EnsureDateTimeOps {

  static final String INSTANT_MUST_BE_IN_FUTURE = "instant must be in the future";
  static final String INSTANT_MUST_BE_PAST_OR_PRESENT = "instant must be in the past or present";

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

  private EnsureDateTimeOps() {
    throw new AssertionError("No Ensure4j for you!");
  }
}
