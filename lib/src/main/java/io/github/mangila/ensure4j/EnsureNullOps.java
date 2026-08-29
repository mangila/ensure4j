/*
 * MIT License
 *
 * Copyright (c) 2025 Erik Olsson (olsson.erik1993@gmail.com)
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

import java.util.function.Supplier;
import org.jetbrains.annotations.Contract;

final class EnsureNullOps {

  static final String OBJECT_MUST_NOT_BE_NULL_MESSAGE = "object must not be null";

  /**
   * Ensures that the provided object is not {@code null}.
   *
   * @param <T> the type of the object
   * @param object the object to check
   * @param exceptionSupplier the supplier that provides the exception to be thrown if validation
   *     fails
   * @return the provided object if it is not {@code null}
   * @throws RuntimeException if the object is {@code null}; the thrown exception is provided by
   *     {@code exceptionSupplier}
   */
  @Contract("null, _ -> fail; !null, _ -> param1")
  static <T> T notNull(T object, Supplier<? extends RuntimeException> exceptionSupplier) {
    if (object == null) {
      throw getSupplierOrThrow(exceptionSupplier);
    }
    return object;
  }

  private EnsureNullOps() {
    throw new AssertionError("No Ensure4j for you!");
  }
}
