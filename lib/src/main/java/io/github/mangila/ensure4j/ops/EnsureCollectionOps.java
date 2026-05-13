package io.github.mangila.ensure4j.ops;

import static io.github.mangila.ensure4j.internal.EnsureUtils.getSupplierOrThrow;
import static java.util.Objects.isNull;

import io.github.mangila.ensure4j.EnsureException;
import java.util.Collection;
import java.util.function.Supplier;
import org.jetbrains.annotations.Contract;

/**
 * Provides utility methods for validating and operating on collections. This enum implements
 * singleton behavior, ensuring a single instance is used throughout.
 */
public enum EnsureCollectionOps {

  /**
   * Access point for the {@code EnsureCollectionOps} singleton. Use this instance to perform
   * collection operations.
   */
  INSTANCE;

  /**
   * Ensures that the provided collection contains the specified element.
   *
   * @param <T> the type of the collection
   * @param collection the collection to check
   * @param element the element to check for
   * @return the provided collection if it contains the element
   * @throws RuntimeException if the collection does not contain the element, with the message
   *     {@code "collection must contain element '%s'"}
   * @see #containsElement(Collection, Object, String)
   * @see #containsElement(Collection, Object, Supplier)
   */
  @Contract("null, _ -> fail; !null, _ -> param1")
  public <T extends Collection<?>> T containsElement(T collection, Object element) {
    return containsElement(
        collection, element, "collection must contain element '%s'".formatted(element));
  }

  /**
   * Ensures that the provided collection contains the specified element.
   *
   * @param <T> the type of the collection
   * @param collection the collection to check
   * @param element the element to check for
   * @param exceptionMessage the message to include in the exception if validation fails
   * @return the provided collection if it contains the element
   * @throws RuntimeException if the collection does not contain the element, with the provided
   *     message
   * @see #containsElement(Collection, Object)
   * @see #containsElement(Collection, Object, Supplier)
   */
  @Contract("null, _, _ -> fail; !null, _, _ -> param1")
  public <T extends Collection<?>> T containsElement(
      T collection, Object element, String exceptionMessage) {
    return containsElement(collection, element, () -> EnsureException.of(exceptionMessage));
  }

  /**
   * Ensures that the provided collection contains the specified element.
   *
   * @param <T> the type of the collection
   * @param collection the collection to check
   * @param element the element to check for
   * @param exceptionSupplier the supplier that provides the exception to be thrown if validation
   *     fails
   * @return the provided collection if it contains the element
   * @throws RuntimeException if the collection does not contain the element; the thrown exception
   *     is provided by {@code exceptionSupplier}
   * @see #containsElement(Collection, Object)
   * @see #containsElement(Collection, Object, String)
   */
  @Contract("null, _, _ -> fail; !null, _, _ -> param1")
  public <T extends Collection<?>> T containsElement(
      T collection, Object element, Supplier<? extends RuntimeException> exceptionSupplier) {
    if (isNull(collection) || !collection.contains(element)) {
      throw getSupplierOrThrow(exceptionSupplier);
    }
    return collection;
  }

  /**
   * Ensures that the provided collection does not contain any null elements.
   *
   * @param <T> the type of the collection
   * @param collection the collection to check
   * @return the provided collection if it does not contain any null elements
   * @throws EnsureException if the collection contains a null element, with the message {@code
   *     "collection must not contain null elements"}
   * @see #notContainsNullIterate(Collection, String)
   * @see #notContainsNullIterate(Collection, Supplier)
   */
  @Contract("null -> fail; !null -> param1")
  public <T extends Collection<?>> T notContainsNullIterate(T collection) {
    return notContainsNullIterate(collection, "collection must not contain null elements");
  }

  /**
   * Ensures that the provided collection does not contain any null elements.
   *
   * @param <T> the type of the collection
   * @param collection the collection to check
   * @param exceptionMessage the message to include in the exception if validation fails
   * @return the provided collection if it does not contain any null elements
   * @throws EnsureException if the collection contains a null element, with the provided message
   * @see #notContainsNullIterate(Collection)
   * @see #notContainsNullIterate(Collection, Supplier)
   */
  @Contract("null, _ -> fail; !null, _ -> param1")
  public <T extends Collection<?>> T notContainsNullIterate(T collection, String exceptionMessage) {
    return notContainsNullIterate(collection, () -> EnsureException.of(exceptionMessage));
  }

  /**
   * Ensures that the provided collection does not contain any null elements.
   *
   * @param <T> the type of the collection
   * @param collection the collection to check
   * @param exceptionSupplier the supplier that provides the exception to be thrown if validation
   *     fails
   * @return the provided collection if it does not contain any null elements
   * @throws RuntimeException if the collection contains a null element; the thrown exception is
   *     provided by {@code exceptionSupplier}
   * @see #notContainsNullIterate(Collection)
   * @see #notContainsNullIterate(Collection, String)
   */
  @Contract("null, _ -> fail; !null, _ -> param1")
  public <T extends Collection<?>> T notContainsNullIterate(
      T collection, Supplier<? extends RuntimeException> exceptionSupplier) {
    if (isNull(collection)) {
      throw getSupplierOrThrow(exceptionSupplier);
    }
    for (final Object element : collection) {
      if (isNull(element)) {
        throw getSupplierOrThrow(exceptionSupplier);
      }
    }
    return collection;
  }

  /**
   * Ensures that the provided collection does not contain any null elements.
   *
   * @param <T> the type of the collection
   * @param collection the collection to check
   * @return the provided collection if it does not contain any null elements
   * @throws EnsureException if the collection contains a null element, with the message {@code
   *     "collection must not contain null elements"}
   * @see #notContainsNull(Collection, String)
   * @see #notContainsNull(Collection, Supplier)
   */
  @Contract("null -> fail; !null -> param1")
  public <T extends Collection<?>> T notContainsNull(T collection) {
    return notContainsNull(collection, "collection must not contain null elements");
  }

  /**
   * Ensures that the provided collection does not contain any null elements.
   *
   * @param <T> the type of the collection
   * @param collection the collection to check
   * @param exceptionMessage the message to include in the exception if validation fails
   * @return the provided collection if it does not contain any null elements
   * @throws EnsureException if the collection contains a null element, with the provided message
   * @see #notContainsNull(Collection)
   * @see #notContainsNull(Collection, Supplier)
   */
  @Contract("null, _ -> fail; !null, _ -> param1")
  public <T extends Collection<?>> T notContainsNull(T collection, String exceptionMessage) {
    return notContainsNull(collection, () -> EnsureException.of(exceptionMessage));
  }

  /**
   * Ensures that the provided collection does not contain any null elements.
   *
   * @param <T> the type of the collection
   * @param collection the collection to check
   * @param exceptionSupplier the supplier that provides the exception to be thrown if validation
   *     fails
   * @return the provided collection if it does not contain any null elements
   * @throws RuntimeException if the collection contains a null element; the thrown exception is
   *     provided by {@code exceptionSupplier}
   * @see #notContainsNull(Collection)
   * @see #notContainsNull(Collection, String)
   */
  @Contract("null, _ -> fail; !null, _ -> param1")
  public <T extends Collection<?>> T notContainsNull(
      T collection, Supplier<? extends RuntimeException> exceptionSupplier) {
    if (isNull(collection) || collection.contains(null)) {
      throw getSupplierOrThrow(exceptionSupplier);
    }
    return collection;
  }

  /**
   * Ensures that the provided collection is not null or empty.
   *
   * @param <T> the type of the collection
   * @param collection the collection to check
   * @return the provided collection if it is not null or empty
   * @throws EnsureException if the collection is null or empty, with the message {@code "collection
   *     must not be empty or null"}
   * @see #notEmpty(Collection, String)
   * @see #notEmpty(Collection, Supplier)
   */
  @Contract("null -> fail; !null -> param1")
  public <T extends Collection<?>> T notEmpty(T collection) {
    return notEmpty(collection, "collection must not be empty or null");
  }

  /**
   * Ensures that the provided collection is not null or empty.
   *
   * @param <T> the type of the collection
   * @param collection the collection to check
   * @param exceptionMessage the message to include in the exception if validation fails
   * @return the provided collection if it is not null or empty
   * @throws EnsureException if the collection is null or empty, with the provided message
   * @see #notEmpty(Collection)
   * @see #notEmpty(Collection, Supplier)
   */
  @Contract("null, _ -> fail; !null, _ -> param1")
  public <T extends Collection<?>> T notEmpty(T collection, String exceptionMessage) {
    return notEmpty(collection, () -> EnsureException.of(exceptionMessage));
  }

  /**
   * Ensures that the provided collection is not null or empty.
   *
   * @param <T> the type of the collection
   * @param collection the collection to check
   * @param exceptionSupplier the supplier that provides the exception to be thrown if validation
   *     fails
   * @return the provided collection if it is not null or empty
   * @throws RuntimeException if the collection is null or empty; the thrown exception is provided
   *     by {@code exceptionSupplier}
   * @see #notEmpty(Collection)
   * @see #notEmpty(Collection, String)
   */
  @Contract("null, _ -> fail; !null, _ -> param1")
  public <T extends Collection<?>> T notEmpty(
      T collection, Supplier<? extends RuntimeException> exceptionSupplier) {
    if (isNull(collection) || collection.isEmpty()) {
      throw getSupplierOrThrow(exceptionSupplier);
    }
    return collection;
  }
}
