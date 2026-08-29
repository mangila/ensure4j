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

import java.util.function.Supplier;
import org.jetbrains.annotations.Contract;

final class EnsureBooleanOps {

  static final String BOOLEAN_MUST_BE_FALSE_MESSAGE = "boolean must be false";
  static final String BOOLEAN_MUST_BE_TRUE_MESSAGE = "boolean must be true";

  /**
   * Ensures that the provided boolean expression is {@code false}.
   *
   * @param expression the boolean expression to check
   * @param exceptionSupplier the supplier that provides the exception to be thrown if validation
   *     fails
   * @throws RuntimeException if the expression is {@code true}; the thrown exception is provided by
   *     {@code exceptionSupplier}
   */
  @Contract("true, _ -> fail")
  static void isFalse(boolean expression, Supplier<? extends RuntimeException> exceptionSupplier) {
    if (expression) {
      throw getSupplierOrThrow(exceptionSupplier);
    }
  }

  /**
   * Ensures that the provided boolean expression is {@code true}.
   *
   * @param expression the boolean expression to check
   * @param exceptionSupplier the supplier that provides the exception to be thrown if validation
   *     fails
   * @throws RuntimeException if the expression is {@code false}; the thrown exception is provided
   *     by {@code exceptionSupplier}
   */
  @Contract("false, _ -> fail")
  static void isTrue(boolean expression, Supplier<? extends RuntimeException> exceptionSupplier) {
    if (!expression) {
      throw getSupplierOrThrow(exceptionSupplier);
    }
  }

  private EnsureBooleanOps() {
    throw new AssertionError("No Ensure4j for you!");
  }
}
