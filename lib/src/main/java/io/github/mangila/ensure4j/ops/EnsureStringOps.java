package io.github.mangila.ensure4j.ops;

import static io.github.mangila.ensure4j.internal.EnsureUtils.getSupplierOrThrow;
import static io.github.mangila.ensure4j.internal.EnsureUtils.isBlank;
import static java.util.Objects.isNull;

import io.github.mangila.ensure4j.EnsureException;
import java.util.function.Supplier;
import org.intellij.lang.annotations.RegExp;
import org.jetbrains.annotations.Contract;

/**
 * Provides utility methods for validating and operating on strings. This enum implements singleton
 * behavior, ensuring a single instance is used throughout.
 */
public enum EnsureStringOps {

  /**
   * Access point for the {@code EnsureStringOps} singleton. Use this instance to perform string
   * operations.
   */
  INSTANCE;

  /**
   * Returns the provided string if it is not null or blank; otherwise, it evaluates and returns the
   * result from the supplied {@link Supplier}.
   *
   * @param string the string to check
   * @param fallbackSupplier the supplier to provide an alternative string if {@code string} is null
   *     or blank
   * @return the non-blank {@code string}, or the value provided by the {@code fallbackSupplier}
   * @throws EnsureException if the {@code fallbackSupplier} is null or produces a null value
   * @see #notBlankOrElse(String, String)
   * @deprecated
   */
  @Contract("null, _ -> fail; !null, _ -> param1")
  @Deprecated(since = "3.0.4", forRemoval = true)
  public String notBlankOrElseGet(String string, Supplier<String> fallbackSupplier) {
    if (isNull(string) || isBlank(string)) {
      return getSupplierOrThrow(fallbackSupplier);
    }
    return string;
  }

  /**
   * Returns the provided string if it is not null or blank; otherwise, returns the given default
   * value.
   *
   * @param string the string to check
   * @param fallbackValue the default value to return if {@code string} is null or blank
   * @return {@code string} if it is not null or blank, otherwise {@code fallbackValue}
   * @see #notBlankOrElseGet(String, Supplier)
   * @deprecated
   */
  @Contract("null, _ -> param2; !null, _ -> param1")
  @Deprecated(since = "3.0.4", forRemoval = true)
  public String notBlankOrElse(String string, String fallbackValue) {
    if (isNull(string) || isBlank(string)) {
      return fallbackValue;
    }
    return string;
  }

  /**
   * Ensures that the provided string is not null or blank.
   *
   * @param string the string to check
   * @return the provided string if it is not null or blank
   * @throws EnsureException if the string is null or blank, with the message {@code "string must
   *     not be blank"}
   * @see #notBlank(String, String)
   * @see #notBlank(String, Supplier)
   */
  @Contract("null -> fail; !null -> param1")
  public String notBlank(String string) {
    return notBlank(string, "string must not be blank");
  }

  /**
   * Ensures that the provided string is not null or blank.
   *
   * @param string the string to check
   * @param exceptionMessage the message to include in the exception if validation fails
   * @return the provided string if it is not null or blank
   * @throws EnsureException if the string is null or blank, with the provided message
   * @see #notBlank(String)
   * @see #notBlank(String, Supplier)
   */
  @Contract("null, _ -> fail; !null, _ -> param1")
  public String notBlank(String string, String exceptionMessage) {
    return notBlank(string, () -> EnsureException.of(exceptionMessage));
  }

  /**
   * Ensures that the provided string is not null or blank.
   *
   * @param string the string to check
   * @param exceptionSupplier the supplier that provides the exception to be thrown if validation
   *     fails
   * @return the provided string if it is not null or blank
   * @throws RuntimeException if the string is null or blank; the thrown exception is provided by
   *     {@code exceptionSupplier}
   * @see #notBlank(String)
   * @see #notBlank(String, String)
   */
  @Contract("null, _ -> fail; !null, _ -> param1")
  public String notBlank(String string, Supplier<? extends RuntimeException> exceptionSupplier) {
    if (isNull(string) || isBlank(string)) {
      throw getSupplierOrThrow(exceptionSupplier);
    }
    return string;
  }

  /**
   * Ensures that the provided string has at least the specified minimum length.
   *
   * @param min the minimum length
   * @param string the string to check
   * @return the provided string if it has at least the minimum length
   * @throws EnsureException if the string length is less than the minimum, with the message {@code
   *     "string length must be at least %d"}
   * @see #minLength(int, String, String)
   * @see #minLength(int, String, Supplier)
   */
  @Contract("_, null -> fail; _, !null -> param2")
  public String minLength(int min, String string) {
    return minLength(min, string, "string length must be at least %d".formatted(min));
  }

  /**
   * Ensures that the provided string has at least the specified minimum length.
   *
   * @param min the minimum length
   * @param string the string to check
   * @param exceptionMessage the message to include in the exception if validation fails
   * @return the provided string if it has at least the minimum length
   * @throws EnsureException if the string length is less than the minimum, with the provided
   *     message
   * @see #minLength(int, String)
   * @see #minLength(int, String, Supplier)
   */
  @Contract("_, null, _ -> fail; _, !null, _ -> param2")
  public String minLength(int min, String string, String exceptionMessage) {
    return minLength(min, string, () -> EnsureException.of(exceptionMessage));
  }

  /**
   * Ensures that the provided string has at least the specified minimum length.
   *
   * @param min the minimum length
   * @param string the string to check
   * @param exceptionSupplier the supplier that provides the exception to be thrown if validation
   *     fails
   * @return the provided string if it has at least the minimum length
   * @throws RuntimeException if the string length is less than the minimum; the thrown exception is
   *     provided by {@code exceptionSupplier}
   * @see #minLength(int, String)
   * @see #minLength(int, String, String)
   */
  @Contract("_, null, _ -> fail; _, !null, _ -> param2")
  public String minLength(
      int min, String string, Supplier<? extends RuntimeException> exceptionSupplier) {
    if (isNull(string) || string.length() < min) {
      throw getSupplierOrThrow(exceptionSupplier);
    }
    return string;
  }

  /**
   * Ensures that the provided string does not exceed the specified maximum length.
   *
   * @param max the maximum length
   * @param string the string to check
   * @return the provided string if it does not exceed the maximum length
   * @throws EnsureException if the string length exceeds the maximum, with the message {@code
   *     "string length must be at most %d"}
   * @see #maxLength(int, String, String)
   * @see #maxLength(int, String, Supplier)
   */
  @Contract("_, null -> fail; _, !null -> param2")
  public String maxLength(int max, String string) {
    return maxLength(max, string, "string length must be at most %d".formatted(max));
  }

  /**
   * Ensures that the provided string does not exceed the specified maximum length.
   *
   * @param max the maximum length
   * @param string the string to check
   * @param exceptionMessage the message to include in the exception if validation fails
   * @return the provided string if it does not exceed the maximum length
   * @throws EnsureException if the string length exceeds the maximum, with the provided message
   * @see #maxLength(int, String)
   * @see #maxLength(int, String, Supplier)
   */
  @Contract("_, null, _ -> fail; _, !null, _ -> param2")
  public String maxLength(int max, String string, String exceptionMessage) {
    return maxLength(max, string, () -> EnsureException.of(exceptionMessage));
  }

  /**
   * Ensures that the provided string does not exceed the specified maximum length.
   *
   * @param max the maximum length
   * @param string the string to check
   * @param exceptionSupplier the supplier that provides the exception to be thrown if validation
   *     fails
   * @return the provided string if it does not exceed the maximum length
   * @throws RuntimeException if the string length exceeds the maximum; the thrown exception is
   *     provided by {@code exceptionSupplier}
   * @see #maxLength(int, String)
   * @see #maxLength(int, String, String)
   */
  @Contract("_, null, _ -> fail; _, !null, _ -> param2")
  public String maxLength(
      int max, String string, Supplier<? extends RuntimeException> exceptionSupplier) {
    if (isNull(string) || string.length() > max) {
      throw getSupplierOrThrow(exceptionSupplier);
    }
    return string;
  }

  /**
   * Ensures that the provided string starts with the specified prefix.
   *
   * @param prefix the prefix to check for
   * @param string the string to check
   * @return the provided string if it starts with the prefix
   * @throws EnsureException if the string does not start with the prefix, with the message {@code
   *     "string must start with %s"}
   * @see #startsWith(String, String, String)
   * @see #startsWith(String, String, Supplier)
   */
  @Contract("_, null -> fail; _, !null -> param2")
  public String startsWith(String prefix, String string) {
    return startsWith(prefix, string, "string must start with %s".formatted(prefix));
  }

  /**
   * Ensures that the provided string starts with the specified prefix.
   *
   * @param prefix the prefix to check for
   * @param string the string to check
   * @param exceptionMessage the message to include in the exception if validation fails
   * @return the provided string if it starts with the prefix
   * @throws EnsureException if the string does not start with the prefix, with the provided message
   * @see #startsWith(String, String)
   * @see #startsWith(String, String, Supplier)
   */
  @Contract("_, null, _ -> fail; _, !null, _ -> param2")
  public String startsWith(String prefix, String string, String exceptionMessage) {
    return startsWith(prefix, string, () -> EnsureException.of(exceptionMessage));
  }

  /**
   * Ensures that the provided string starts with the specified prefix.
   *
   * @param prefix the prefix to check for
   * @param string the string to check
   * @param exceptionSupplier the supplier that provides the exception to be thrown if validation
   *     fails
   * @return the provided string if it starts with the prefix
   * @throws RuntimeException if the string does not start with the prefix; the thrown exception is
   *     provided by {@code exceptionSupplier}
   * @see #startsWith(String, String)
   * @see #startsWith(String, String, String)
   */
  @Contract("null, _, _ -> fail; !null, null, _ -> fail; !null, !null, _ -> param2")
  public String startsWith(
      String prefix, String string, Supplier<? extends RuntimeException> exceptionSupplier) {
    if (isNull(prefix)) {
      throw EnsureException.of("prefix must not be null");
    }
    if (isNull(string) || !string.startsWith(prefix)) {
      throw getSupplierOrThrow(exceptionSupplier);
    }
    return string;
  }

  /**
   * Ensures that the provided string ends with the specified suffix.
   *
   * @param suffix the suffix to check for
   * @param string the string to check
   * @return the provided string if it ends with the suffix
   * @throws EnsureException if the string does not end with the suffix, with the message {@code
   *     "string must end with %s"}
   * @see #endsWith(String, String, String)
   * @see #endsWith(String, String, Supplier)
   */
  @Contract("_, null -> fail; _, !null -> param2")
  public String endsWith(String suffix, String string) {
    return endsWith(suffix, string, "string must end with %s".formatted(suffix));
  }

  /**
   * Ensures that the provided string ends with the specified suffix.
   *
   * @param suffix the suffix to check for
   * @param string the string to check
   * @param exceptionMessage the message to include in the exception if validation fails
   * @return the provided string if it ends with the suffix
   * @throws EnsureException if the string does not end with the suffix, with the provided message
   * @see #endsWith(String, String)
   * @see #endsWith(String, String, Supplier)
   */
  @Contract("_, null, _ -> fail; _, !null, _ -> param2")
  public String endsWith(String suffix, String string, String exceptionMessage) {
    return endsWith(suffix, string, () -> EnsureException.of(exceptionMessage));
  }

  /**
   * Ensures that the provided string ends with the specified suffix.
   *
   * @param suffix the suffix to check for
   * @param string the string to check
   * @param exceptionSupplier the supplier that provides the exception to be thrown if validation
   *     fails
   * @return the provided string if it ends with the suffix
   * @throws RuntimeException if the string does not end with the suffix; the thrown exception is
   *     provided by {@code exceptionSupplier}
   * @see #endsWith(String, String)
   * @see #endsWith(String, String, String)
   */
  @Contract("null, _, _ -> fail; !null, null, _ -> fail; !null, !null, _ -> param2")
  public String endsWith(
      String suffix, String string, Supplier<? extends RuntimeException> exceptionSupplier) {
    if (isNull(suffix)) {
      throw EnsureException.of("suffix must not be null");
    }
    if (isNull(string) || !string.endsWith(suffix)) {
      throw getSupplierOrThrow(exceptionSupplier);
    }
    return string;
  }

  /**
   * Ensures that the provided string matches the specified regular expression.
   *
   * @param regex the regular expression to match against
   * @param string the string to check
   * @return the provided string if it matches the regular expression
   * @throws EnsureException if the string does not match the regular expression, with the message
   *     {@code "string must match regex '%s'"}
   * @see #matches(String, String, String)
   * @see #matches(String, String, Supplier)
   */
  @Contract("_, null -> fail; _, !null -> param2")
  public String matches(@RegExp String regex, String string) {
    return matches(regex, string, "string must match regex '%s'".formatted(regex));
  }

  /**
   * Ensures that the provided string matches the specified regular expression.
   *
   * @param regex the regular expression to match against
   * @param string the string to check
   * @param exceptionMessage the message to include in the exception if validation fails
   * @return the provided string if it matches the regular expression
   * @throws EnsureException if the string does not match the regular expression, with the provided
   *     message
   * @see #matches(String, String)
   * @see #matches(String, String, Supplier)
   */
  @Contract("_, null, _ -> fail; _, !null, _ -> param2")
  public String matches(@RegExp String regex, String string, String exceptionMessage) {
    return matches(regex, string, () -> EnsureException.of(exceptionMessage));
  }

  /**
   * Ensures that the provided string matches the specified regular expression.
   *
   * @param regex the regular expression to match against
   * @param string the string to check
   * @param exceptionSupplier the supplier that provides the exception to be thrown if validation
   *     fails
   * @return the provided string if it matches the regular expression
   * @throws RuntimeException if the string does not match the regular expression; the thrown
   *     exception is provided by {@code exceptionSupplier}
   * @see #matches(String, String)
   * @see #matches(String, String, String)
   */
  @Contract("null, _, _ -> fail; !null, null, _ -> fail; !null, !null, _ -> param2")
  public String matches(
      @RegExp String regex, String string, Supplier<? extends RuntimeException> exceptionSupplier) {
    if (isNull(regex)) {
      throw EnsureException.of("regex must not be null");
    }
    if (isNull(string) || !string.matches(regex)) {
      throw getSupplierOrThrow(exceptionSupplier);
    }
    return string;
  }
}
