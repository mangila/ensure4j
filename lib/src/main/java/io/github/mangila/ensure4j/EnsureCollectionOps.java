package io.github.mangila.ensure4j;

import static io.github.mangila.ensure4j.EnsureUtils.getSupplierOrThrow;

import java.util.Collection;
import java.util.function.Supplier;
import org.jetbrains.annotations.Contract;

final class EnsureCollectionOps {

  static final String COLLECTION_MUST_CONTAIN_ELEMENT_MESSAGE = "collection must contain element";
  static final String COLLECTION_MUST_NOT_BE_EMPTY_OR_NULL_MESSAGE =
      "collection must not be empty or null";

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
   */
  @Contract("null, _, _ -> fail; !null, _, _ -> param1")
  static <T extends Collection<?>> T containsElement(
      T collection, Object element, Supplier<? extends RuntimeException> exceptionSupplier) {
    if (!EnsureUtils.contains(collection, element)) {
      throw getSupplierOrThrow(exceptionSupplier);
    }
    return collection;
  }

  /**
   * Ensures that the provided collection is not {@code null} or empty.
   *
   * @param <T> the type of the collection
   * @param collection the collection to check
   * @param exceptionSupplier the supplier that provides the exception to be thrown if validation
   *     fails
   * @return the provided collection if it is not {@code null} or empty
   * @throws RuntimeException if the collection is {@code null} or empty; the thrown exception is
   *     provided by {@code exceptionSupplier}
   */
  @Contract("null, _ -> fail; !null, _ -> param1")
  static <T extends Collection<?>> T notEmpty(
      T collection, Supplier<? extends RuntimeException> exceptionSupplier) {
    if (EnsureUtils.isEmpty(collection)) {
      throw getSupplierOrThrow(exceptionSupplier);
    }
    return collection;
  }

  private EnsureCollectionOps() {
    throw new AssertionError("No Ensure4j for you!");
  }
}
