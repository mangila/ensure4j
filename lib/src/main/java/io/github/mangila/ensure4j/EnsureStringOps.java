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
import org.intellij.lang.annotations.RegExp;
import org.jetbrains.annotations.Contract;

final class EnsureStringOps {

  static final String STRING_LENGTH_AT_LEAST_FORMAT =
      "string length must be at least %d characters";
  static final String STRING_LENGTH_AT_MOST_FORMAT = "string length must be at most %d characters";
  static final String STRING_MUST_END_WITH_FORMAT = "string must end with: %s";
  static final String STRING_MUST_MATCH_REGEX_FORMAT = "string must match regex: %s";
  static final String STRING_MUST_NOT_BE_BLANK_MESSAGE = "string must not be blank";
  static final String STRING_MUST_START_WITH_FORMAT = "string must start with: %s";

  /**
   * Ensures that the provided string ends with the specified suffix.
   *
   * @param string the string to check
   * @param suffix the suffix to check for
   * @param exceptionSupplier the supplier that provides the exception to be thrown if validation
   *     fails
   * @return the provided string if it ends with the suffix
   * @throws RuntimeException if the string does not end with the suffix; the thrown exception is
   *     provided by {@code exceptionSupplier}
   */
  @Contract("null, _, _ -> fail; _, null, _ -> fail; !null, !null, _ -> param1")
  static String endsWith(
      String string, String suffix, Supplier<? extends RuntimeException> exceptionSupplier) {
    if (!EnsureUtils.hasSuffix(string, suffix)) {
      throw getSupplierOrThrow(exceptionSupplier);
    }
    return string;
  }

  /**
   * Ensures that the provided string matches the specified regular expression.
   *
   * @param string the string to check
   * @param regex the regular expression to match against
   * @param exceptionSupplier the supplier that provides the exception to be thrown if validation
   *     fails
   * @return the provided string if it matches the regular expression
   * @throws RuntimeException if the string does not match the regular expression; the thrown
   *     exception is provided by {@code exceptionSupplier}
   */
  @Contract("null, _, _ -> fail; _, null, _ -> fail; !null, !null, _ -> param1")
  static String matches(
      String string, @RegExp String regex, Supplier<? extends RuntimeException> exceptionSupplier) {
    if (!EnsureUtils.matches(string, regex)) {
      throw getSupplierOrThrow(exceptionSupplier);
    }
    return string;
  }

  /**
   * Ensures that the provided string does not exceed the specified maximum length.
   *
   * @param string the string to check
   * @param boundary the maximum length
   * @param exceptionSupplier the supplier that provides the exception to be thrown if validation
   *     fails
   * @return the provided string if it does not exceed the maximum length
   * @throws RuntimeException if the string length exceeds the maximum; the thrown exception is
   *     provided by {@code exceptionSupplier}
   */
  @Contract("null, _, _ -> fail; !null, _, _ -> param1")
  static String maxLength(
      String string, int boundary, Supplier<? extends RuntimeException> exceptionSupplier) {
    if (string == null || !EnsureUtils.isAtMost(string.length(), boundary)) {
      throw getSupplierOrThrow(exceptionSupplier);
    }
    return string;
  }

  /**
   * Ensures that the provided string has at least the specified minimum length.
   *
   * @param string the string to check
   * @param boundary the minimum length
   * @param exceptionSupplier the supplier that provides the exception to be thrown if validation
   *     fails
   * @return the provided string if it has at least the minimum length
   * @throws RuntimeException if the string length is less than the minimum; the thrown exception is
   *     provided by {@code exceptionSupplier}
   */
  @Contract("null, _, _ -> fail; !null, _, _ -> param1")
  static String minLength(
      String string, int boundary, Supplier<? extends RuntimeException> exceptionSupplier) {
    if (string == null || !EnsureUtils.isAtLeast(string.length(), boundary)) {
      throw getSupplierOrThrow(exceptionSupplier);
    }
    return string;
  }

  /**
   * Ensures that the provided string is not {@code null} or blank.
   *
   * @param string the string to check
   * @param exceptionSupplier the supplier that provides the exception to be thrown if validation
   *     fails
   * @return the provided string if it is not {@code null} or blank
   * @throws RuntimeException if the string is {@code null} or blank; the thrown exception is
   *     provided by {@code exceptionSupplier}
   */
  @Contract("null, _ -> fail; !null, _ -> param1")
  static String notBlank(String string, Supplier<? extends RuntimeException> exceptionSupplier) {
    if (EnsureUtils.isBlankOrNull(string)) {
      throw getSupplierOrThrow(exceptionSupplier);
    }
    return string;
  }

  /**
   * Ensures that the provided string starts with the specified prefix.
   *
   * @param string the string to check
   * @param prefix the prefix to check for
   * @param exceptionSupplier the supplier that provides the exception to be thrown if validation
   *     fails
   * @return the provided string if it starts with the prefix
   * @throws RuntimeException if the string does not start with the prefix; the thrown exception is
   *     provided by {@code exceptionSupplier}
   */
  @Contract("null, _, _ -> fail; _, null, _ -> fail; !null, !null, _ -> param1")
  static String startsWith(
      String string, String prefix, Supplier<? extends RuntimeException> exceptionSupplier) {
    if (!EnsureUtils.hasPrefix(string, prefix)) {
      throw getSupplierOrThrow(exceptionSupplier);
    }
    return string;
  }

  private EnsureStringOps() {
    throw new AssertionError("No Ensure4j for you!");
  }
}
