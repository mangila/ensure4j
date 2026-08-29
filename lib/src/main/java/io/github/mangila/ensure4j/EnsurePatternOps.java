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
import java.util.regex.Pattern;

/**
 * EnsurePatternOps provides pattern-based validation for strings, such as email and alphanumeric
 * checks.
 */
final class EnsurePatternOps {

  /** Matches any alphanumeric character, including spaces. */
  static final Pattern ALPHANUMERIC_PATTERN = Pattern.compile("[a-zA-Z0-9\\s]+");

  /**
   * While RFC 5322 is notoriously complex, this is a highly reliable, performant pattern for 99.9%
   * of real-world application boundaries.
   */
  static final Pattern EMAIL_PATTERN =
      Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");

  static final String STRING_MUST_MATCH_ALPHANUMERIC_MESSAGE =
      "string must match alphanumeric pattern";

  static final String STRING_MUST_MATCH_EMAIL_MESSAGE = "string must match email pattern";

  static String matches(
      String string, Pattern pattern, Supplier<? extends RuntimeException> exceptionSupplier) {
    if (!EnsureUtils.matches(string, pattern)) {
      throw getSupplierOrThrow(exceptionSupplier);
    }
    return string;
  }

  /**
   * Ensures that the provided string matches the alphanumeric pattern (including spaces).
   *
   * @param string the string to check
   * @param exceptionSupplier the supplier that provides the exception to be thrown if validation
   *     fails
   * @return the provided string if it matches the alphanumeric pattern
   * @throws RuntimeException if the string does not match the alphanumeric pattern; the thrown
   *     exception is provided by {@code exceptionSupplier}
   */
  static String matchesAlphanumeric(
      String string, Supplier<? extends RuntimeException> exceptionSupplier) {
    return matches(string, ALPHANUMERIC_PATTERN, exceptionSupplier);
  }

  /**
   * Ensures that the provided string matches the email pattern.
   *
   * @param string the string to check
   * @param exceptionSupplier the supplier that provides the exception to be thrown if validation
   *     fails
   * @return the provided string if it matches the email pattern
   * @throws RuntimeException if the string does not match the email pattern; the thrown exception
   *     is provided by {@code exceptionSupplier}
   */
  static String matchesEmail(
      String string, Supplier<? extends RuntimeException> exceptionSupplier) {
    return matches(string, EMAIL_PATTERN, exceptionSupplier);
  }

  private EnsurePatternOps() {
    throw new AssertionError("No Ensure4j for you!");
  }
}
