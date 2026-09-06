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

import java.time.Instant;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.function.Supplier;
import java.util.regex.Pattern;
import org.intellij.lang.annotations.RegExp;

final class EnsureUtils {

  static boolean contains(Collection<?> collection, Object element) {
    return collection.contains(element);
  }

  static boolean containsKey(Map<?, ?> map, Object key) {
    return map != null && key != null && map.containsKey(key);
  }

  static boolean containsValue(Map<?, ?> map, Object value) {
    return map != null && value != null && map.containsValue(value);
  }

  static boolean deepEquals(Object[] array, Object[] otherArray) {
    return Arrays.deepEquals(array, otherArray);
  }

  static boolean equals(Object[] array, Object[] otherArray) {
    return Arrays.equals(array, otherArray);
  }

  static <T> T getSupplierOrThrow(Supplier<T> supplier) {
    if (supplier == null) {
      throw new EnsureException("supplier was null");
    }
    final T t = supplier.get();
    if (t == null) {
      throw new EnsureException("supplier was given a null value");
    }
    return t;
  }

  static boolean hasPrefix(String string, String prefix) {
    return string != null && prefix != null && string.startsWith(prefix);
  }

  static boolean hasSuffix(String string, String suffix) {
    return string != null && suffix != null && string.endsWith(suffix);
  }

  static boolean isAtLeast(int value, int boundary) {
    return value >= boundary;
  }

  static boolean isAtLeast(long value, long boundary) {
    return value >= boundary;
  }

  static boolean isAtMost(int value, int boundary) {
    return value <= boundary;
  }

  static boolean isAtMost(long value, long boundary) {
    return value <= boundary;
  }

  static boolean isBlankOrNull(String string) {
    return string == null || string.isBlank();
  }

  static boolean isEmpty(Collection<?> collection) {
    return collection == null || collection.isEmpty();
  }

  static boolean isEmpty(Map<?, ?> map) {
    return map == null || map.isEmpty();
  }

  static boolean isEmpty(Object[] array) {
    return array == null || array.length == 0;
  }

  static boolean isFuture(Instant instant, Instant boundary) {
    return instant != null && boundary != null && instant.isAfter(boundary);
  }

  static boolean isInRange(int value, int start, int end) {
    return value >= start && value <= end;
  }

  static boolean isInRange(long value, long start, long end) {
    return value >= start && value <= end;
  }

  static boolean isNegative(long value) {
    return value < 0;
  }

  static boolean isNegativeWithZero(long value) {
    return value <= 0;
  }

  static boolean isPastOrPresent(Instant instant, Instant boundary) {
    return instant != null && boundary != null && !instant.isAfter(boundary);
  }

  static boolean isPositive(long value) {
    return value > 0;
  }

  static boolean isPositiveWithZero(long value) {
    return value >= 0;
  }

  static boolean matches(String string, Pattern pattern) {
    return string != null && pattern != null && pattern.matcher(string).matches();
  }

  static boolean matches(String string, @RegExp String regex) {
    return string != null && regex != null && string.matches(regex);
  }

  private EnsureUtils() {
    throw new AssertionError("Cannot instantiate utility class");
  }
}
