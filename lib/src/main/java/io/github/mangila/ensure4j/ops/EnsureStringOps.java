package io.github.mangila.ensure4j.ops;

import static io.github.mangila.ensure4j.internal.EnsureUtils.*;

import io.github.mangila.ensure4j.EnsureException;
import java.util.function.Supplier;

/**
 * Provides utility methods for validating and operating on strings. This enum implements singleton
 * behavior, ensuring a single instance is used throughout.
 */
public enum EnsureStringOps {
  INSTANCE;

  /**
   * Returns the provided string if it is not blank (non-null and containing non-whitespace
   * characters), otherwise retrieves and returns a value from the provided fallback supplier.
   *
   * @param string the string to check for blankness
   * @param fallbackSupplier the supplier to retrieve an alternative value if the string is blank
   * @return the original string if it is not blank, or the value supplied by the fallback supplier
   *     if the string is blank
   */
  public String notBlankOrElseGet(String string, Supplier<String> fallbackSupplier) {
    if (isNull(string) || isBlank(string)) {
      return getSupplierOrThrow(fallbackSupplier);
    }
    return string;
  }

  /**
   * Returns the given string if it is not blank; otherwise, returns the specified fallback value. A
   * string is considered blank if it is null, empty, or contains only whitespace characters.
   *
   * @param string the input string to check
   * @param fallbackValue the fallback value to return if the input string is blank
   * @return the input string if it is not blank; otherwise, the fallback value
   */
  public String notBlankOrElse(String string, String fallbackValue) {
    if (isNull(string) || isBlank(string)) {
      return fallbackValue;
    }
    return string;
  }

  /**
   * Ensures that the provided string is not blank. If the string is blank or null, the specified
   * exception supplied by the runtimeExceptionSupplier is thrown.
   *
   * @param string the string to be validated as not blank
   * @param runtimeExceptionSupplier the runtimeExceptionSupplier that provides the exception to
   *     throw if the string is blank
   * @return the validated string if it is not blank
   * @throws RuntimeException if the string is blank or null, provided by the
   *     runtimeExceptionSupplier
   */
  public String notBlank(String string, Supplier<RuntimeException> runtimeExceptionSupplier)
      throws RuntimeException {
    if (isNull(string) || isBlank(string)) {
      throw getSupplierOrThrow(runtimeExceptionSupplier);
    }
    return string;
  }

  /**
   * Ensures that the provided string is not blank. If the string is blank or null, an exception is
   * thrown with the provided exception message.
   *
   * @param string the string to check for being non-blank
   * @param exceptionMessage the message to include in the thrown exception if the string is blank
   * @return the original string if it is not blank
   * @throws EnsureException if the string is blank
   */
  public String notBlank(String string, String exceptionMessage) throws EnsureException {
    return notBlank(string, () -> EnsureException.of(exceptionMessage));
  }

  /**
   * Ensures that the provided string is not blank. If the string is blank or null, an exception is
   * thrown with a default message.
   *
   * @param string the string to validate
   * @return the original string if it is not blank
   * @throws EnsureException with the message - "string must not be blank" if the string is blank
   */
  public String notBlank(String string) throws EnsureException {
    return notBlank(string, "string must not be blank");
  }

  /**
   * Ensures that the provided string has a minimum length.
   *
   * @param min the minimum length allowed
   * @param string the string to check
   * @param runtimeExceptionSupplier a supplier that provides the exception to be thrown if the
   *     string length is less than the minimum
   * @return the input string if its length is greater than or equal to the minimum
   * @throws RuntimeException if the string length is less than the minimum
   */
  public String minLength(
      int min, String string, Supplier<RuntimeException> runtimeExceptionSupplier)
      throws RuntimeException {
    if (isNull(string) || string.length() < min) {
      throw getSupplierOrThrow(runtimeExceptionSupplier);
    }
    return string;
  }

  /**
   * Ensures that the provided string has a minimum length.
   *
   * @param min the minimum length allowed
   * @param string the string to check
   * @param exceptionMessage the message to include in the EnsureException if the string length is
   *     less than the minimum
   * @return the input string if its length is greater than or equal to the minimum
   * @throws EnsureException if the string length is less than the minimum
   */
  public String minLength(int min, String string, String exceptionMessage) throws EnsureException {
    return minLength(min, string, () -> EnsureException.of(exceptionMessage));
  }

  /**
   * Ensures that the provided string has a minimum length.
   *
   * @param min the minimum length allowed
   * @param string the string to check
   * @return the input string if its length is greater than or equal to the minimum
   * @throws EnsureException with the message "string length must be at least %d" - if the string
   *     length is less than the minimum
   */
  public String minLength(int min, String string) throws EnsureException {
    return minLength(min, string, "string length must be at least %d".formatted(min));
  }

  /**
   * Ensures that the provided string has a maximum length.
   *
   * @param max the maximum length allowed
   * @param string the string to check
   * @param runtimeExceptionSupplier a supplier that provides the exception to be thrown if the
   *     string length is greater than the maximum
   * @return the input string if its length is less than or equal to the maximum
   * @throws RuntimeException if the string length is greater than the maximum
   */
  public String maxLength(
      int max, String string, Supplier<RuntimeException> runtimeExceptionSupplier)
      throws RuntimeException {
    if (isNull(string) || string.length() > max) {
      throw getSupplierOrThrow(runtimeExceptionSupplier);
    }
    return string;
  }

  /**
   * Ensures that the provided string has a maximum length.
   *
   * @param max the maximum length allowed
   * @param string the string to check
   * @param exceptionMessage the message to include in the EnsureException if the string length is
   *     greater than the maximum
   * @return the input string if its length is less than or equal to the maximum
   * @throws EnsureException if the string length is greater than the maximum
   */
  public String maxLength(int max, String string, String exceptionMessage) throws EnsureException {
    return maxLength(max, string, () -> EnsureException.of(exceptionMessage));
  }

  /**
   * Ensures that the provided string has a maximum length.
   *
   * @param max the maximum length allowed
   * @param string the string to check
   * @return the input string if its length is less than or equal to the maximum
   * @throws EnsureException with the message "string length must be at most %d".formatted(max) - if
   *     the string length is greater than the maximum
   */
  public String maxLength(int max, String string) throws EnsureException {
    return maxLength(max, string, "string length must be at most %d".formatted(max));
  }

  /**
   * Ensures that the provided string starts with the specified prefix.
   *
   * @param prefix the prefix that the string must start with
   * @param string the string to check
   * @param runtimeExceptionSupplier a supplier that provides the exception to be thrown if the
   *     string does not start with the prefix
   * @return the input string if it starts with the prefix
   * @throws RuntimeException if the string does not start with the prefix or if either parameter is
   *     null
   */
  public String startsWith(
      String prefix, String string, Supplier<RuntimeException> runtimeExceptionSupplier)
      throws RuntimeException {
    if (isNull(prefix)) {
      throw EnsureException.of("prefix must not be null");
    }
    if (isNull(string) || !string.startsWith(prefix)) {
      throw getSupplierOrThrow(runtimeExceptionSupplier);
    }
    return string;
  }

  /**
   * Ensures that the provided string starts with the specified prefix.
   *
   * @param prefix the prefix that the string must start with
   * @param string the string to check
   * @param exceptionMessage the message to include in the EnsureException if the string does not
   *     start with the prefix
   * @return the input string if it starts with the prefix
   * @throws EnsureException if the string does not start with the prefix
   */
  public String startsWith(String prefix, String string, String exceptionMessage)
      throws EnsureException {
    return startsWith(prefix, string, () -> EnsureException.of(exceptionMessage));
  }

  /**
   * Ensures that the provided string starts with the specified prefix.
   *
   * @param prefix the prefix that the string must start with
   * @param string the string to check
   * @return the input string if it starts with the prefix
   * @throws EnsureException with the message "string must start with %s" - if the string does not
   *     start with the prefix
   */
  public String startsWith(String prefix, String string) throws EnsureException {
    return startsWith(prefix, string, "string must start with %s".formatted(prefix));
  }

  /**
   * Ensures that the provided string ends with the specified suffix.
   *
   * @param suffix the suffix that the string must end with
   * @param string the string to check
   * @param runtimeExceptionSupplier a supplier that provides the exception to be thrown if the
   *     string does not end with the suffix
   * @return the input string if it ends with the suffix
   * @throws RuntimeException if the string does not end with the suffix or if either parameter is
   *     null
   */
  public String endsWith(
      String suffix, String string, Supplier<RuntimeException> runtimeExceptionSupplier)
      throws RuntimeException {
    if (isNull(suffix)) {
      throw EnsureException.of("suffix must not be null");
    }
    if (isNull(string) || !string.endsWith(suffix)) {
      throw getSupplierOrThrow(runtimeExceptionSupplier);
    }
    return string;
  }

  /**
   * Ensures that the provided string ends with the specified suffix.
   *
   * @param suffix the suffix that the string must end with
   * @param string the string to check
   * @param exceptionMessage the message to include in the EnsureException if the string does not
   *     end with the suffix
   * @return the input string if it ends with the suffix
   * @throws EnsureException if the string does not end with the suffix
   */
  public String endsWith(String suffix, String string, String exceptionMessage)
      throws EnsureException {
    return endsWith(suffix, string, () -> EnsureException.of(exceptionMessage));
  }

  /**
   * Ensures that the provided string ends with the specified suffix.
   *
   * @param suffix the suffix that the string must end with
   * @param string the string to check
   * @return the input string if it ends with the suffix
   * @throws EnsureException with the message "string must end with %s" - if the string does not end
   *     with the suffix
   */
  public String endsWith(String suffix, String string) throws EnsureException {
    return endsWith(suffix, string, "string must end with %s".formatted(suffix));
  }
}
