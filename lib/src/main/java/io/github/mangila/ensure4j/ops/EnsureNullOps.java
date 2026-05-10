package io.github.mangila.ensure4j.ops;

import static io.github.mangila.ensure4j.internal.EnsureUtils.getSupplierOrThrow;
import static io.github.mangila.ensure4j.internal.EnsureUtils.isNull;

import io.github.mangila.ensure4j.EnsureException;
import java.util.function.Supplier;

/**
 * Provides utility methods for validating and operating on null. This enum implements singleton
 * behavior, ensuring a single instance is used throughout.
 */
public enum EnsureNullOps {

  /**
   * Access point for the {@code EnsureNullOps} singleton. Use this instance to perform null
   * operations.
   */
  INSTANCE;

  /**
   * Ensures that the specified object is not null. If the object is null, the default object is
   * returned.
   *
   * @param <T> the type of the object
   * @param object the object to check for nullity
   * @param defaultObject the default object to return if {@code object} is null
   * @return {@code object} if it is not null, otherwise {@code defaultObject}
   * @see #notNullOrElseGet(Object, Supplier)
   */
  public <T> T notNullOrElse(T object, T defaultObject) {
    if (isNull(object)) {
      return defaultObject;
    }
    return object;
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
   * @see #notNullOrElse(Object, Object)
   */
  public <T> T notNullOrElseGet(T object, Supplier<T> fallbackSupplier) throws EnsureException {
    if (isNull(object)) {
      return getSupplierOrThrow(fallbackSupplier);
    }
    return object;
  }

  /**
   * Ensures that the specified object is not null. If the object is null, a RuntimeException
   * provided by the given runtimeExceptionSupplier is thrown.
   *
   * @param <T> the type of the object being checked
   * @param object the object to check for nullity
   * @param runtimeExceptionSupplier the runtimeExceptionSupplier that provides a RuntimeException
   *     to be thrown if the object is null
   * @return the non-null object
   * @throws RuntimeException if the object is null and the runtimeExceptionSupplier provides an
   *     exception
   * @deprecated Use {@link #notNull(Object, Supplier)} instead.
   */
  @Deprecated(since = "3.0.2", forRemoval = true)
  public <T> T notNullOrElseThrow(
      T object, Supplier<? extends RuntimeException> runtimeExceptionSupplier)
      throws RuntimeException {
    return notNull(object, runtimeExceptionSupplier);
  }

  /**
   * Ensures that the given object is not null, and returns the object if it is non-null. If the
   * object is null, this method throws a {@link RuntimeException}.
   *
   * @param <T> the type of the object
   * @param object the object to be checked for nullity
   * @return the non-null object passed as input
   * @throws RuntimeException with the message "object must not be null" - if the object is null
   * @deprecated Use {@link #notNull(Object)} instead.
   */
  @Deprecated(since = "3.0.2", forRemoval = true)
  public <T> T notNullOrElseThrow(T object) throws RuntimeException {
    return notNull(object, () -> EnsureException.of("object must not be null"));
  }

  /**
   * Ensures that the provided object is not null.
   *
   * @param <T> the type of the object
   * @param object the object to check
   * @return the provided object if it is not null
   * @throws EnsureException if the object is null, with the message {@code "object must not be
   *     null"}
   * @see #notNull(Object, String)
   * @see #notNull(Object, Supplier)
   */
  public <T> T notNull(T object) throws EnsureException {
    return notNull(object, "object must not be null");
  }

  /**
   * Ensures that the provided object is not null.
   *
   * @param <T> the type of the object
   * @param object the object to check
   * @param exceptionMessage the message to include in the exception if validation fails
   * @return the provided object if it is not null
   * @throws EnsureException if the object is null, with the provided message
   * @see #notNull(Object)
   * @see #notNull(Object, Supplier)
   */
  public <T> T notNull(T object, String exceptionMessage) throws EnsureException {
    return notNull(object, () -> EnsureException.of(exceptionMessage));
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
   * @see #notNull(Object)
   * @see #notNull(Object, String)
   */
  public <T> T notNull(T object, Supplier<? extends RuntimeException> exceptionSupplier)
      throws RuntimeException {
    if (isNull(object)) {
      throw getSupplierOrThrow(exceptionSupplier);
    }
    return object;
  }
}
