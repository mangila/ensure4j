package io.github.mangila.ensure4j;

import io.github.mangila.ensure4j.ops.*;
import java.util.function.Supplier;
import org.jetbrains.annotations.Contract;

/**
 * Acts as a utility hub for typed pre- and post-condition checks but keeps the most frequently used
 * ones in the top level class. Like null check and boolean checks.
 */
public final class Ensure {

  private static final EnsureNullOps NULL_OPS = EnsureNullOps.INSTANCE;
  private static final EnsureBooleanOps BOOLEAN_OPS = EnsureBooleanOps.INSTANCE;
  private static final EnsureArrayOps ARRAY_OPS = EnsureArrayOps.INSTANCE;
  private static final EnsureCollectionOps COLLECTION_OPS = EnsureCollectionOps.INSTANCE;
  private static final EnsureMapOps MAP_OPS = EnsureMapOps.INSTANCE;
  private static final EnsureNumberOps NUMBER_OPS = EnsureNumberOps.INSTANCE;
  private static final EnsureObjectOps OBJECT_OPS = EnsureObjectOps.INSTANCE;
  private static final EnsureStringOps STRING_OPS = EnsureStringOps.INSTANCE;
  private static final EnsureDateTimeOps DATE_TIME_OPS = EnsureDateTimeOps.INSTANCE;

  private Ensure() {
    throw new IllegalStateException("Utility class");
  }

  /**
   * Get the array operations.
   *
   * @return EnsureArrayOps instance
   * @see EnsureArrayOps
   */
  public static EnsureArrayOps arrays() {
    return ARRAY_OPS;
  }

  /**
   * Get the collection operations.
   *
   * @return EnsureCollectionOps instance
   * @see EnsureCollectionOps
   */
  public static EnsureCollectionOps collections() {
    return COLLECTION_OPS;
  }

  /**
   * Get the map operations.
   *
   * @return EnsureMapOps instance
   * @see EnsureMapOps
   */
  public static EnsureMapOps maps() {
    return MAP_OPS;
  }

  /**
   * Get the number operations.
   *
   * @return EnsureNumberOps instance
   * @see EnsureNumberOps
   */
  public static EnsureNumberOps numbers() {
    return NUMBER_OPS;
  }

  /**
   * Get the object operations.
   *
   * @return EnsureObjectOps instance
   * @see EnsureObjectOps
   */
  public static EnsureObjectOps objects() {
    return OBJECT_OPS;
  }

  /**
   * Get the date-time operations.
   *
   * @return EnsureDateTimeOps instance
   * @see EnsureDateTimeOps
   */
  public static EnsureDateTimeOps dates() {
    return DATE_TIME_OPS;
  }

  /**
   * Get the string operations.
   *
   * @return EnsureStringOps instance
   * @see EnsureStringOps
   */
  public static EnsureStringOps strings() {
    return STRING_OPS;
  }

  /**
   * Returns the provided object if it is not null; otherwise, it evaluates and returns the result
   * from the supplied {@link Supplier}.
   *
   * @param <T> the type of the object
   * @param object the object to check for non-nullity
   * @param fallbackSupplier the supplier to provide an alternative object if {@code object} is null
   * @return the non-null {@code object}, or the value provided by the {@code fallbackSupplier}
   * @throws EnsureException if the {@code fallbackSupplier} is null or produces a null value
   * @see EnsureNullOps#notNullOrElseGet(Object, Supplier)
   * @deprecated since 3.0.4, use {@link java.util.Objects#requireNonNullElseGet(Object, Supplier)}
   *     instead
   */
  @Deprecated(since = "3.0.4", forRemoval = true)
  public static <T> T notNullOrElseGet(T object, Supplier<T> fallbackSupplier) {
    return NULL_OPS.notNullOrElseGet(object, fallbackSupplier);
  }

  /**
   * Ensures that the specified object is not null. If the object is null, the default object is
   * returned.
   *
   * @param <T> the type of the object
   * @param object the object to check for nullity
   * @param defaultObject the default object to return if {@code object} is null
   * @return {@code object} if it is not null, otherwise {@code defaultObject}
   * @see EnsureNullOps#notNullOrElse(Object, Object)
   * @deprecated since 3.0.4, use {@link java.util.Objects#requireNonNullElse(Object, Object)}
   *     instead
   */
  @Contract("null, _ -> param2; !null, _ -> param1")
  @Deprecated(since = "3.0.4", forRemoval = true)
  public static <T> T notNullOrElse(T object, T defaultObject) {
    return NULL_OPS.notNullOrElse(object, defaultObject);
  }

  /**
   * Ensures that the provided object is not null.
   *
   * @param <T> the type of the object
   * @param object the object to check
   * @param exceptionSupplier the supplier that provides the exception to be thrown if validation
   *     fails
   * @return the provided object if it is not null
   * @throws RuntimeException if the object is null; the thrown exception is provided by {@code
   *     exceptionSupplier}
   * @see EnsureNullOps#notNull(Object, Supplier)
   */
  @Contract("null, _ -> fail; !null, _ -> param1")
  public static <T> T notNull(T object, Supplier<? extends RuntimeException> exceptionSupplier) {
    return NULL_OPS.notNull(object, exceptionSupplier);
  }

  /**
   * Ensures that the provided object is not null.
   *
   * @param <T> the type of the object
   * @param object the object to check
   * @param exceptionMessage the message to include in the exception if validation fails
   * @return the provided object if it is not null
   * @throws EnsureException if the object is null, with the provided message
   * @see EnsureNullOps#notNull(Object, String)
   */
  @Contract("null, _ -> fail; !null, _ -> param1")
  public static <T> T notNull(T object, String exceptionMessage) {
    return NULL_OPS.notNull(object, exceptionMessage);
  }

  /**
   * Ensures that the provided object is not null.
   *
   * @param <T> the type of the object
   * @param object the object to check
   * @return the provided object if it is not null
   * @throws EnsureException if the object is null, with the message {@code "object must not be
   *     null"}
   * @see EnsureNullOps#notNull(Object)
   */
  @Contract("null -> fail; !null -> param1")
  public static <T> T notNull(T object) {
    return NULL_OPS.notNull(object);
  }

  /**
   * Ensures that the provided boolean value is true.
   *
   * @param expression the boolean value to check
   * @param exceptionSupplier the supplier that provides the exception to be thrown if validation
   *     fails
   * @throws RuntimeException if the boolean value is false; the thrown exception is provided by
   *     {@code exceptionSupplier}
   * @see EnsureBooleanOps#isTrue(boolean, Supplier)
   */
  @Contract("false, _ -> fail")
  public static void isTrue(
      boolean expression, Supplier<? extends RuntimeException> exceptionSupplier) {
    BOOLEAN_OPS.isTrue(expression, exceptionSupplier);
  }

  /**
   * Ensures that the provided boolean value is true.
   *
   * @param expression the boolean value to check
   * @param exceptionMessage the message to include in the exception if validation fails
   * @throws EnsureException if the boolean value is false, with the provided message
   * @see EnsureBooleanOps#isTrue(boolean, String)
   */
  @Contract("false, _ -> fail")
  public static void isTrue(boolean expression, String exceptionMessage) {
    BOOLEAN_OPS.isTrue(expression, exceptionMessage);
  }

  /**
   * Ensures that the provided boolean value is true.
   *
   * @param expression the boolean value to check
   * @throws EnsureException if the boolean value is false, with the message {@code "boolean must be
   *     true"}
   * @see EnsureBooleanOps#isTrue(boolean)
   */
  @Contract("false -> fail")
  public static void isTrue(boolean expression) {
    BOOLEAN_OPS.isTrue(expression);
  }

  /**
   * Ensures that the provided boolean value is false.
   *
   * @param expression the boolean value to check
   * @param exceptionSupplier the supplier that provides the exception to be thrown if validation
   *     fails
   * @throws RuntimeException if the boolean value is true; the thrown exception is provided by
   *     {@code exceptionSupplier}
   * @see EnsureBooleanOps#isFalse(boolean, Supplier)
   */
  @Contract("true, _ -> fail")
  public static void isFalse(
      boolean expression, Supplier<? extends RuntimeException> exceptionSupplier) {
    BOOLEAN_OPS.isFalse(expression, exceptionSupplier);
  }

  /**
   * Ensures that the provided boolean value is false.
   *
   * @param expression the boolean value to check
   * @param exceptionMessage the message to include in the exception if validation fails
   * @throws EnsureException if the boolean value is true, with the provided message
   * @see EnsureBooleanOps#isFalse(boolean, String)
   */
  @Contract("true, _ -> fail")
  public static void isFalse(boolean expression, String exceptionMessage) {
    BOOLEAN_OPS.isFalse(expression, exceptionMessage);
  }

  /**
   * Ensures that the provided boolean value is false.
   *
   * @param expression the boolean value to check
   * @throws EnsureException if the boolean value is true, with the message {@code "boolean must be
   *     false"}
   * @see EnsureBooleanOps#isFalse(boolean)
   */
  @Contract("true -> fail")
  public static void isFalse(boolean expression) {
    BOOLEAN_OPS.isFalse(expression);
  }
}
