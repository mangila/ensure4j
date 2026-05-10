package io.github.mangila.ensure4j;

import io.github.mangila.ensure4j.ops.*;
import java.util.function.Supplier;
import org.jspecify.annotations.NonNull;

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

  private Ensure() {
    throw new IllegalStateException("Utility class");
  }

  /**
   * @see EnsureArrayOps
   */
  @NonNull
  public static EnsureArrayOps arrays() {
    return ARRAY_OPS;
  }

  /**
   * @see EnsureCollectionOps
   */
  @NonNull
  public static EnsureCollectionOps collections() {
    return COLLECTION_OPS;
  }

  /**
   * @see EnsureMapOps
   */
  @NonNull
  public static EnsureMapOps maps() {
    return MAP_OPS;
  }

  /**
   * @see EnsureNumberOps
   */
  @NonNull
  public static EnsureNumberOps numbers() {
    return NUMBER_OPS;
  }

  /**
   * @see EnsureObjectOps
   */
  @NonNull
  public static EnsureObjectOps objects() {
    return OBJECT_OPS;
  }

  /**
   * @see EnsureStringOps
   */
  @NonNull
  public static EnsureStringOps strings() {
    return STRING_OPS;
  }

  /**
   * @see EnsureNullOps#notNullOrElseGet(Object, Supplier)
   */
  public static <T> T notNullOrElseGet(T object, Supplier<T> fallbackSupplier)
      throws EnsureException {
    return NULL_OPS.notNullOrElseGet(object, fallbackSupplier);
  }

  /**
   * @see EnsureNullOps#notNullOrElse(Object, Object)
   */
  public static <T> T notNullOrElse(T object, T defaultObject) {
    return NULL_OPS.notNullOrElse(object, defaultObject);
  }

  /**
   * @see EnsureNullOps#notNullOrElseThrow(Object, Supplier)
   */
  @Deprecated(since = "3.0.2", forRemoval = true)
  public static <T> T notNullOrElseThrow(
      T object, Supplier<RuntimeException> runtimeExceptionSupplier) throws RuntimeException {
    return NULL_OPS.notNullOrElseThrow(object, runtimeExceptionSupplier);
  }

  /**
   * @see EnsureNullOps#notNullOrElseThrow(Object)
   */
  @Deprecated(since = "3.0.2", forRemoval = true)
  public static <T> T notNullOrElseThrow(T object) throws RuntimeException {
    return NULL_OPS.notNullOrElseThrow(object);
  }

  /**
   * @see EnsureNullOps#notNull(Object, Supplier)
   */
  public static <T> T notNull(T object, Supplier<RuntimeException> runtimeExceptionSupplier)
      throws RuntimeException {
    return NULL_OPS.notNull(object, runtimeExceptionSupplier);
  }

  /**
   * @see EnsureNullOps#notNull(Object, String)
   */
  public static <T> T notNull(T object, String message) throws EnsureException {
    return NULL_OPS.notNull(object, message);
  }

  /**
   * @see EnsureNullOps#notNull(Object)
   */
  public static <T> T notNull(T object) throws EnsureException {
    return NULL_OPS.notNull(object);
  }

  /**
   * @see EnsureBooleanOps#isTrue(boolean, Supplier)
   */
  public static void isTrue(boolean expression, Supplier<RuntimeException> runtimeExceptionSupplier)
      throws RuntimeException {
    BOOLEAN_OPS.isTrue(expression, runtimeExceptionSupplier);
  }

  /**
   * @see EnsureBooleanOps#isTrue(boolean, String)
   */
  public static void isTrue(boolean expression, String exceptionMessage) throws EnsureException {
    BOOLEAN_OPS.isTrue(expression, exceptionMessage);
  }

  /**
   * @see EnsureBooleanOps#isTrue(boolean)
   */
  public static void isTrue(boolean expression) throws EnsureException {
    BOOLEAN_OPS.isTrue(expression);
  }

  /**
   * @see EnsureBooleanOps#isFalse(boolean, Supplier)
   */
  public static void isFalse(
      boolean expression, Supplier<RuntimeException> runtimeExceptionSupplier)
      throws RuntimeException {
    BOOLEAN_OPS.isFalse(expression, runtimeExceptionSupplier);
  }

  /**
   * @see EnsureBooleanOps#isFalse(boolean, String)
   */
  public static void isFalse(boolean expression, String exceptionMessage) throws EnsureException {
    BOOLEAN_OPS.isFalse(expression, exceptionMessage);
  }

  /**
   * @see EnsureBooleanOps#isFalse(boolean)
   */
  public static void isFalse(boolean expression) throws EnsureException {
    BOOLEAN_OPS.isFalse(expression);
  }
}
