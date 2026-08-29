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

import java.util.Objects;
import java.util.function.Supplier;
import org.jetbrains.annotations.Contract;

final class EnsureObjectOps {

  static final String OBJECT_MUST_BE_OF_TYPE_MESSAGE = "object must be a type of";
  static final String OBJECTS_DEEP_EQUAL_MESSAGE = "objects must be deeply equal";
  static final String OBJECTS_MUST_BE_EQUAL_MESSAGE = "objects must be equal";

  /**
   * Ensures that the provided objects are deeply equal.
   *
   * @param <T> the type of the object
   * @param actual the first object
   * @param expected the second object
   * @param exceptionSupplier the supplier that provides the exception to be thrown if validation
   *     fails
   * @return the first object if they are deeply equal
   * @throws RuntimeException if the objects are not deeply equal; the thrown exception is provided
   *     by {@code exceptionSupplier}
   */
  @Contract(
      "null, !null, _ -> fail; !null, null, _ -> fail; null, null, _ -> param1; !null, !null, _ ->"
          + " param1")
  static <T> T deepEqualTo(
      T actual, Object expected, Supplier<? extends RuntimeException> exceptionSupplier) {
    if (!Objects.deepEquals(actual, expected)) {
      throw getSupplierOrThrow(exceptionSupplier);
    }
    return actual;
  }

  /**
   * Ensures that the provided objects are equal.
   *
   * @param <T> the type of the object
   * @param actual the first object
   * @param expected the second object
   * @param exceptionSupplier the supplier that provides the exception to be thrown if validation
   *     fails
   * @return the first object if they are equal
   * @throws RuntimeException if the objects are not equal; the thrown exception is provided by
   *     {@code exceptionSupplier}
   */
  @Contract(
      "null, !null, _ -> fail; !null, null, _ -> fail; null, null, _ -> param1; !null, !null, _ ->"
          + " param1")
  static <T> T equalTo(
      T actual, Object expected, Supplier<? extends RuntimeException> exceptionSupplier) {
    if (!Objects.equals(actual, expected)) {
      throw getSupplierOrThrow(exceptionSupplier);
    }
    return actual;
  }

  /**
   * Ensures that the provided object is an instance of the specified class.
   *
   * @param <T> the type of the object
   * @param object the object to check
   * @param clazz the class to check against
   * @param exceptionSupplier the supplier that provides the exception to be thrown if validation
   *     fails
   * @return the provided object if it is an instance of the class
   * @throws RuntimeException if the object is not an instance of the class; the thrown exception is
   *     provided by {@code exceptionSupplier}
   */
  @Contract("_, null, _ -> fail; !null, !null, _ -> param1")
  static <T> T typeOf(
      Object object, Class<T> clazz, Supplier<? extends RuntimeException> exceptionSupplier) {
    if (clazz == null || !clazz.isInstance(object)) {
      throw getSupplierOrThrow(exceptionSupplier);
    }
    return clazz.cast(object);
  }

  private EnsureObjectOps() {
    throw new AssertionError("No Ensure4j for you!");
  }
}
