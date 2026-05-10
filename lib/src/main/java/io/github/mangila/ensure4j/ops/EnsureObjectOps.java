package io.github.mangila.ensure4j.ops;

import static io.github.mangila.ensure4j.Ensure.notNull;
import static io.github.mangila.ensure4j.internal.EnsureUtils.getSupplierOrThrow;
import static io.github.mangila.ensure4j.internal.EnsureUtils.isNull;

import io.github.mangila.ensure4j.EnsureException;
import java.util.function.Supplier;
import org.jetbrains.annotations.Contract;

/**
 * Provides utility methods for validating and operating on Objects. This enum implements singleton
 * behavior, ensuring a single instance is used throughout.
 */
public enum EnsureObjectOps {

  /**
   * Access point for the {@code EnsureObjectOps} singleton. Use this instance to perform object
   * operations.
   */
  INSTANCE;

  /**
   * Ensures that the provided object is an instance of the specified class.
   *
   * @param <T> the type of the object
   * @param clazz the class to check against
   * @param object the object to check
   * @return the provided object if it is an instance of the class
   * @throws EnsureException if the object is not an instance of the class, with the message {@code
   *     "object must be an instance of %s"}
   * @see #isInstanceOf(Class, Object, String)
   * @see #isInstanceOf(Class, Object, Supplier)
   */
  @Contract("_, null -> fail; !null, !null -> param2")
  public <T> T isInstanceOf(Class<T> clazz, Object object) throws EnsureException {
    notNull(clazz, "class must not be null");
    return isInstanceOf(
        clazz, object, "object must be an instance of %s".formatted(clazz.getName()));
  }

  /**
   * Ensures that the provided object is an instance of the specified class.
   *
   * @param <T> the type of the object
   * @param clazz the class to check against
   * @param object the object to check
   * @param exceptionMessage the message to include in the exception if validation fails
   * @return the provided object if it is an instance of the class
   * @throws EnsureException if the object is not an instance of the class, with the provided
   *     message
   * @see #isInstanceOf(Class, Object)
   * @see #isInstanceOf(Class, Object, Supplier)
   */
  @Contract("_, null, _ -> fail; !null, !null, _ -> param2")
  public <T> T isInstanceOf(Class<T> clazz, Object object, String exceptionMessage)
      throws EnsureException {
    return isInstanceOf(clazz, object, () -> EnsureException.of(exceptionMessage));
  }

  /**
   * Ensures that the provided object is an instance of the specified class.
   *
   * @param <T> the type of the object
   * @param clazz the class to check against
   * @param object the object to check
   * @param exceptionSupplier the supplier that provides the exception to be thrown if validation
   *     fails
   * @return the provided object if it is an instance of the class
   * @throws RuntimeException if the object is not an instance of the class; the thrown exception is
   *     provided by {@code exceptionSupplier}
   * @see #isInstanceOf(Class, Object)
   * @see #isInstanceOf(Class, Object, String)
   */
  @Contract("null, _, _ -> fail; !null, null, _ -> fail; !null, !null, _ -> param2")
  @SuppressWarnings("unchecked")
  public <T> T isInstanceOf(
      Class<T> clazz, Object object, Supplier<? extends RuntimeException> exceptionSupplier)
      throws RuntimeException {
    if (isNull(clazz) || !clazz.isInstance(object)) {
      throw getSupplierOrThrow(exceptionSupplier);
    }
    return (T) object;
  }

  /**
   * Ensures that the provided enum values are equal.
   *
   * @param <T> the type of the enum
   * @param enum1 the first enum value
   * @param enum2 the second enum value
   * @return the first enum value if they are equal
   * @throws EnsureException if the enum values are not equal, with the message {@code "enums must
   *     be equal"}
   * @see #isEquals(Enum, Enum, String)
   * @see #isEquals(Enum, Enum, Supplier)
   */
  @Contract(
      "null, !null -> fail; !null, null -> fail; null, null -> param1; !null, !null -> param1")
  public <T extends Enum<T>> T isEquals(T enum1, T enum2) throws EnsureException {
    return isEquals(enum1, enum2, "enums must be equal");
  }

  /**
   * Ensures that the provided enum values are equal.
   *
   * @param <T> the type of the enum
   * @param enum1 the first enum value
   * @param enum2 the second enum value
   * @param exceptionMessage the message to include in the exception if validation fails
   * @return the first enum value if they are equal
   * @throws EnsureException if the enum values are not equal, with the provided message
   * @see #isEquals(Enum, Enum)
   * @see #isEquals(Enum, Enum, Supplier)
   */
  @Contract(
      "null, !null, _ -> fail; !null, null, _ -> fail; null, null, _ -> param1; !null, !null, _ -> param1")
  public <T extends Enum<T>> T isEquals(T enum1, T enum2, String exceptionMessage)
      throws EnsureException {
    return isEquals(enum1, enum2, () -> EnsureException.of(exceptionMessage));
  }

  /**
   * Ensures that the provided enum values are equal.
   *
   * @param <T> the type of the enum
   * @param enum1 the first enum value
   * @param enum2 the second enum value
   * @param exceptionSupplier the supplier that provides the exception to be thrown if validation
   *     fails
   * @return the first enum value if they are equal
   * @throws RuntimeException if the enum values are not equal; the thrown exception is provided by
   *     {@code exceptionSupplier}
   * @see #isEquals(Enum, Enum)
   * @see #isEquals(Enum, Enum, String)
   */
  @Contract(
      "null, !null, _ -> fail; !null, null, _ -> fail; null, null, _ -> param1; !null, !null, _ -> param1")
  public <T extends Enum<T>> T isEquals(
      T enum1, T enum2, Supplier<? extends RuntimeException> exceptionSupplier)
      throws RuntimeException {
    if (enum1 != enum2) {
      throw getSupplierOrThrow(exceptionSupplier);
    }
    return enum1;
  }

  /**
   * Ensures that the provided objects are equal.
   *
   * @param <T> the type of the object
   * @param object the first object
   * @param otherObject the second object
   * @return the first object if they are equal
   * @throws EnsureException if the objects are not equal, with the message {@code "objects must be
   *     equal"}
   * @see #isEquals(Object, Object, String)
   * @see #isEquals(Object, Object, Supplier)
   */
  @Contract(
      "null, !null -> fail; !null, null -> fail; null, null -> param1; !null, !null -> param1")
  public <T> T isEquals(T object, Object otherObject) throws EnsureException {
    return isEquals(object, otherObject, "objects must be equal");
  }

  /**
   * Ensures that the provided objects are equal.
   *
   * @param <T> the type of the object
   * @param object the first object
   * @param otherObject the second object
   * @param exceptionMessage the message to include in the exception if validation fails
   * @return the first object if they are equal
   * @throws EnsureException if the objects are not equal, with the provided message
   * @see #isEquals(Object, Object)
   * @see #isEquals(Object, Object, Supplier)
   */
  @Contract(
      "null, !null, _ -> fail; !null, null, _ -> fail; null, null, _ -> param1; !null, !null, _ -> param1")
  public <T> T isEquals(T object, Object otherObject, String exceptionMessage)
      throws EnsureException {
    return isEquals(object, otherObject, () -> EnsureException.of(exceptionMessage));
  }

  /**
   * Ensures that the provided objects are equal.
   *
   * @param <T> the type of the object
   * @param object the first object
   * @param otherObject the second object
   * @param exceptionSupplier the supplier that provides the exception to be thrown if validation
   *     fails
   * @return the first object if they are equal
   * @throws RuntimeException if the objects are not equal; the thrown exception is provided by
   *     {@code exceptionSupplier}
   * @see #isEquals(Object, Object)
   * @see #isEquals(Object, Object, String)
   */
  @Contract(
      "null, !null, _ -> fail; !null, null, _ -> fail; null, null, _ -> param1; !null, !null, _ -> param1")
  public <T> T isEquals(
      T object, Object otherObject, Supplier<? extends RuntimeException> exceptionSupplier)
      throws RuntimeException {
    if (object == otherObject) {
      return object;
    }
    if (isNull(object)) {
      throw getSupplierOrThrow(exceptionSupplier);
    }
    if (object.equals(otherObject)) {
      return object;
    }
    throw getSupplierOrThrow(exceptionSupplier);
  }
}
