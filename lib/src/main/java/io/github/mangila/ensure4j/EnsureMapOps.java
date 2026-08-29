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

import java.util.Map;
import java.util.function.Supplier;
import org.jetbrains.annotations.Contract;

final class EnsureMapOps {

  static final String MAP_MUST_CONTAIN_KEY_MESSAGE = "map must contain key: %s";
  static final String MAP_MUST_CONTAIN_VALUE_MESSAGE = "map must contain value: %s";
  static final String MAP_MUST_NOT_BE_EMPTY_MESSAGE = "map must not be empty or null";

  /**
   * Ensures that the provided map contains the specified key.
   *
   * @param <T> the type of the map
   * @param map the map to check
   * @param key the key whose presence in the map is to be tested
   * @param exceptionSupplier the supplier that provides the exception to be thrown if validation
   *     fails
   * @return the provided map if it contains the specified key
   * @throws RuntimeException if the map is {@code null} or does not contain the specified key; the
   *     thrown exception is provided by {@code exceptionSupplier}
   */
  @Contract("null, _, _ -> fail; !null, _, _ -> param1")
  static <T extends Map<?, ?>> T containsKey(
      T map, Object key, Supplier<? extends RuntimeException> exceptionSupplier) {
    if (!EnsureUtils.containsKey(map, key)) {
      throw getSupplierOrThrow(exceptionSupplier);
    }
    return map;
  }

  /**
   * Ensures that the provided map contains the specified value.
   *
   * @param <T> the type of the map
   * @param map the map to check
   * @param value the value whose presence in the map is to be tested
   * @param exceptionSupplier the supplier that provides the exception to be thrown if validation
   *     fails
   * @return the provided map if it contains the specified value
   * @throws RuntimeException if the map is {@code null} or does not contain the specified value;
   *     the thrown exception is provided by {@code exceptionSupplier}
   */
  @Contract("null, _, _ -> fail; !null, _, _ -> param1")
  static <T extends Map<?, ?>> T containsValue(
      T map, Object value, Supplier<? extends RuntimeException> exceptionSupplier) {
    if (!EnsureUtils.containsValue(map, value)) {
      throw getSupplierOrThrow(exceptionSupplier);
    }
    return map;
  }

  /**
   * Ensures that the provided map is not {@code null} or empty.
   *
   * @param <T> the type of the map
   * @param map the map to check
   * @param exceptionSupplier the supplier that provides the exception to be thrown if validation
   *     fails
   * @return the provided map if it is not {@code null} or empty
   * @throws RuntimeException if the map is {@code null} or empty; the thrown exception is provided
   *     by {@code exceptionSupplier}
   */
  @Contract("null, _ -> fail; !null, _ -> param1")
  static <T extends Map<?, ?>> T notEmpty(
      T map, Supplier<? extends RuntimeException> exceptionSupplier) {
    if (EnsureUtils.isEmpty(map)) {
      throw getSupplierOrThrow(exceptionSupplier);
    }
    return map;
  }

  private EnsureMapOps() {
    throw new AssertionError("No Ensure4j for you!");
  }
}
