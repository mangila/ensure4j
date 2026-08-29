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

final class EnsureArrayOps {

  static final String ARRAY_MUST_NOT_BE_EMPTY_MESSAGE = "array must not be empty";
  static final String ARRAYS_MUST_BE_DEEP_EQUAL_MESSAGE = "arrays must be deep equal";
  static final String ARRAYS_MUST_BE_EQUAL_MESSAGE = "arrays must be equal";

  /**
   * Ensures that the provided arrays are deeply equal.
   *
   * @param <T> the component type of the arrays
   * @param array the first array
   * @param otherArray the second array
   * @param exceptionSupplier the supplier that provides the exception to be thrown if validation
   *     fails
   * @return the first array if they are deeply equal
   * @throws RuntimeException if the arrays are not deeply equal; the thrown exception is provided
   *     by {@code exceptionSupplier}
   */
  @Contract(
      "null, null, _ -> param1; null, !null, _ -> fail; !null, null, _ -> fail; !null, !null, _ ->"
          + " param1")
  static <T> T[] deepEqualTo(
      T[] array, T[] otherArray, Supplier<? extends RuntimeException> exceptionSupplier) {
    if (EnsureUtils.deepEquals(array, otherArray)) {
      return array;
    }
    throw getSupplierOrThrow(exceptionSupplier);
  }

  /**
   * Ensures that the provided arrays are equal.
   *
   * @param <T> the component type of the arrays
   * @param array the first array
   * @param otherArray the second array
   * @param exceptionSupplier the supplier that provides the exception to be thrown if validation
   *     fails
   * @return the first array if they are equal
   * @throws RuntimeException if the arrays are not equal; the thrown exception is provided by
   *     {@code exceptionSupplier}
   */
  @Contract(
      "null, null, _ -> param1; null, !null, _ -> fail; !null, null, _ -> fail; !null, !null, _ ->"
          + " param1")
  static <T> T[] equalTo(
      T[] array, T[] otherArray, Supplier<? extends RuntimeException> exceptionSupplier) {
    if (EnsureUtils.equals(array, otherArray)) {
      return array;
    }
    throw getSupplierOrThrow(exceptionSupplier);
  }

  /**
   * Ensures that the provided array is not {@code null} or empty.
   *
   * @param <T> the component type of the array
   * @param array the array to check
   * @param exceptionSupplier the supplier that provides the exception to be thrown if validation
   *     fails
   * @return the provided array if it is not {@code null} or empty
   * @throws RuntimeException if the array is {@code null} or empty; the thrown exception is
   *     provided by {@code exceptionSupplier}
   */
  @Contract("null, _ -> fail; !null, _ -> param1")
  static <T> T[] notEmpty(T[] array, Supplier<? extends RuntimeException> exceptionSupplier) {
    if (EnsureUtils.isEmpty(array)) {
      throw getSupplierOrThrow(exceptionSupplier);
    }
    return array;
  }

  private EnsureArrayOps() {
    throw new AssertionError("No Ensure4j for you!");
  }
}
