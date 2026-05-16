package io.github.mangila.ensure4j;

import static io.github.mangila.ensure4j.EnsureUtils.getSupplierOrThrow;

import java.util.function.Supplier;
import java.util.regex.Pattern;

/**
 * EnsurePatternOps provides pattern-based validation for strings, such as email and alphanumeric
 * checks.
 */
final class EnsurePatternOps {

  static final String STRING_MUST_MATCH_EMAIL_MESSAGE = "string must match email pattern";
  static final String STRING_MUST_MATCH_ALPHANUMERIC_MESSAGE =
      "string must match alphanumeric pattern";
  static final String PATTERN_MUST_NOT_BE_NULL_MESSAGE = "pattern must not be null";

  /**
   * While RFC 5322 is notoriously complex, this is a highly reliable, performant pattern for 99.9%
   * of real-world application boundaries.
   */
  static final Pattern EMAIL_PATTERN =
      Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");

  /** Matches any alphanumeric character, including spaces. */
  static final Pattern ALPHANUMERIC_PATTERN = Pattern.compile("[a-zA-Z0-9\\s]+");

  private EnsurePatternOps() {
    throw new AssertionError("No Ensure4j for you!");
  }

  static String matches(
      String string, Pattern pattern, Supplier<? extends RuntimeException> exceptionSupplier) {
    if (pattern == null) {
      throw EnsureException.from(PATTERN_MUST_NOT_BE_NULL_MESSAGE);
    }
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
    if (!EnsureUtils.matches(string, ALPHANUMERIC_PATTERN)) {
      throw getSupplierOrThrow(exceptionSupplier);
    }

    return string;
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
    if (!EnsureUtils.matches(string, EMAIL_PATTERN)) {
      throw getSupplierOrThrow(exceptionSupplier);
    }

    return string;
  }
}
