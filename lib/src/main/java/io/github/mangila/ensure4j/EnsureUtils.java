package io.github.mangila.ensure4j;

import java.time.Instant;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.function.Supplier;
import java.util.regex.Pattern;
import org.intellij.lang.annotations.RegExp;

final class EnsureUtils {

  private EnsureUtils() {
    throw new AssertionError("Cannot instantiate utility class");
  }

  static boolean isBlankOrNull(String string) {
    return string == null || string.isBlank();
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

  static boolean equals(Object[] array, Object[] otherArray) {
    return Arrays.equals(array, otherArray);
  }

  static boolean deepEquals(Object[] array, Object[] otherArray) {
    return Arrays.deepEquals(array, otherArray);
  }

  static boolean contains(Collection<?> collection, Object element) {
    return collection.contains(element);
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

  static boolean isPastOrPresent(Instant instant, Instant boundary) {
    return instant != null && boundary != null && !instant.isAfter(boundary);
  }

  static boolean isAtLeast(int value, int boundary) {
    return value >= boundary;
  }

  static boolean isAtMost(int value, int boundary) {
    return value <= boundary;
  }

  static boolean hasPrefix(String string, String prefix) {
    return string != null && string.startsWith(prefix);
  }

  static boolean hasSuffix(String string, String suffix) {
    return string != null && string.endsWith(suffix);
  }

  static boolean matches(String string, @RegExp String regex) {
    return string != null && string.matches(regex);
  }

  static boolean isAtMost(long value, long boundary) {
    return value <= boundary;
  }

  static boolean isAtLeast(long value, long boundary) {
    return value >= boundary;
  }

  static boolean isPositive(long value) {
    return value > 0;
  }

  static boolean isPositiveWithZero(long value) {
    return value >= 0;
  }

  static boolean isNegative(long value) {
    return value < 0;
  }

  static boolean isNegativeWithZero(long value) {
    return value <= 0;
  }

  static boolean matches(String string, Pattern pattern) {
    return string != null && pattern.matcher(string).matches();
  }
}
