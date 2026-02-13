package io.github.mangila.ensure4j.ops;

import io.github.mangila.ensure4j.EnsureException;

import java.util.Collection;
import java.util.function.Supplier;

import static io.github.mangila.ensure4j.internal.EnsureUtils.getSupplierOrThrow;
import static io.github.mangila.ensure4j.internal.EnsureUtils.isNull;

public enum EnsureCollectionOps {
    INSTANCE;

    /**
     * Checks if the specified collection contains the given element. If the element is not found,
     * the provided runtime exception supplier is used to throw an exception.
     *
     * @param <T>                      the type of the collection
     * @param collection               the collection to be checked for the presence of the element
     * @param element                  the element to look for within the collection
     * @param runtimeExceptionSupplier the supplier that provides the runtime exception to be thrown
     *                                 if the element is not found in the collection
     * @return the collection passed as input
     * @throws RuntimeException if the element is not found in the collection
     */
    public <T extends Collection<?>> T containsElement(T collection, Object element, Supplier<RuntimeException> runtimeExceptionSupplier) throws RuntimeException {
        if (isNull(collection) || !collection.contains(element)) {
            throw getSupplierOrThrow(runtimeExceptionSupplier);
        }
        return collection;
    }

    /**
     * Checks if the specified collection contains the given element.
     * If the element is not found within the collection, a RuntimeException is thrown with the provided exception message.
     *
     * @param <T>              the type of the collection
     * @param collection       the collection to be searched for the specified element
     * @param element          the element to be checked for presence in the collection
     * @param exceptionMessage the message to be included in the RuntimeException if the element is not found
     * @return the collection passed as input
     * @throws RuntimeException if the specified element is not found in the collection
     */
    public <T extends Collection<?>> T containsElement(T collection, Object element, String exceptionMessage) throws RuntimeException {
        return containsElement(collection, element, () -> EnsureException.of(exceptionMessage));
    }

    /**
     * Checks if the specified collection contains the given element. If the element
     * is not found in the collection, a RuntimeException is thrown.
     *
     * @param <T>        the type of the collection
     * @param collection the collection in which to search for the element
     * @param element    the element to search for within the collection
     * @return the collection passed as input
     * @throws RuntimeException with the message "collection must contain element %s" - if the element is not found in the collection
     */
    public <T extends Collection<?>> T containsElement(T collection, Object element) throws RuntimeException {
        return containsElement(collection, element, String.format("collection must contain element '%s'", element));
    }

    /**
     * Ensures that the provided collection does not contain any null elements.
     * If the collection contains a null element, an exception is thrown using the provided runtimeExceptionSupplier.
     * <br>
     * NOTE: This method will perform a null check on each element of the collection. Using a foreach loop
     * NOTE: There is no check if the collection is empty.
     *
     * @param <T>                      the type of the collection
     * @param collection               the collection to be checked for null elements
     * @param runtimeExceptionSupplier the runtimeExceptionSupplier of the exception to be thrown if a null element is found
     * @return the collection passed as input
     * @throws EnsureException if the collection contains a null element
     */
    public <T extends Collection<?>> T notContainsNullIterate(T collection, Supplier<RuntimeException> runtimeExceptionSupplier) throws EnsureException {
        if (isNull(collection)) {
            throw getSupplierOrThrow(runtimeExceptionSupplier);
        }
        for (Object element : collection) {
            if (isNull(element)) {
                throw getSupplierOrThrow(runtimeExceptionSupplier);
            }
        }
        return collection;
    }

    /**
     * Ensures that the provided collection does not contain any null elements.
     * <br>
     * NOTE: This method will perform a null check on each element of the collection. Using a foreach loop
     * NOTE: There is no check if the collection is empty.
     *
     * @param <T>              the type of the collection
     * @param collection       the collection to be checked for null elements
     * @param exceptionMessage the message to be used in the exception if the condition is violated
     * @return the collection passed as input
     * @throws EnsureException if the collection contains null elements
     */
    public <T extends Collection<?>> T notContainsNullIterate(T collection, String exceptionMessage) throws EnsureException {
        return notContainsNullIterate(collection, () -> EnsureException.of(exceptionMessage));
    }

    /**
     * Ensures that the provided collection does not contain any null elements.
     * <br>
     * NOTE: This method will perform a null check on each element of the collection. Using a foreach loop
     * NOTE: There is no check if the collection is empty.
     *
     * @param <T>        the type of the collection
     * @param collection the collection to be checked for null elements
     * @return the collection passed as input
     * @throws EnsureException with the message "collection must not contain null elements" - if the collection contains a null element
     */
    public <T extends Collection<?>> T notContainsNullIterate(T collection) throws EnsureException {
        return notContainsNullIterate(collection, "collection must not contain null elements");
    }

    /**
     * Ensures that the provided collection does not contain any null elements.
     * If the collection contains a null element, an exception is thrown using the provided runtimeExceptionSupplier.
     * <br>
     * NOTE: This method will perform a null check using the built-in {@link Collection#contains(Object)} method.
     * NOTE: There is no check if the collection is empty.
     *
     * @param <T>                      the type of the collection
     * @param collection               the collection to be checked for null elements
     * @param runtimeExceptionSupplier the runtimeExceptionSupplier of the exception to be thrown if a null element is found
     * @return the collection passed as input
     * @throws RuntimeException if the collection contains a null element
     */
    public <T extends Collection<?>> T notContainsNull(T collection, Supplier<RuntimeException> runtimeExceptionSupplier) throws RuntimeException {
        if (isNull(collection) || collection.contains(null)) {
            throw getSupplierOrThrow(runtimeExceptionSupplier);
        }
        return collection;
    }

    /**
     * Ensures that the provided collection does not contain any null elements.
     * <br>
     * NOTE: This method will perform a null check using the built-in {@link Collection#contains(Object)} method.
     * NOTE: There is no check if the collection is empty.
     *
     * @param <T>              the type of the collection
     * @param collection       the collection to be checked for null elements
     * @param exceptionMessage the message to be used in the exception if the condition is violated
     * @return the collection passed as input
     * @throws EnsureException if the collection contains null elements
     */
    public <T extends Collection<?>> T notContainsNull(T collection, String exceptionMessage) throws EnsureException {
        return notContainsNull(collection, () -> EnsureException.of(exceptionMessage));
    }

    /**
     * Ensures that the provided collection does not contain any null elements.
     * <br>
     * NOTE: This method will perform a null check using the built-in {@link Collection#contains(Object)} method.
     * NOTE: There is no check if the collection is empty.
     *
     * @param <T>        the type of the collection
     * @param collection the collection to be checked for null elements
     * @return the collection passed as input
     * @throws EnsureException with the message "collection must not contain null elements" - if the collection contains a null element
     */
    public <T extends Collection<?>> T notContainsNull(T collection) throws EnsureException {
        return notContainsNull(collection, "collection must not contain null elements");
    }

    /**
     * Validates that the provided collection is not null and not empty.
     * If the collection is null or empty, a RuntimeException is thrown, which is
     * either created using the provided runtimeExceptionSupplier or thrown directly if the runtimeExceptionSupplier is null.
     *
     * @param <T>                      the type of the collection
     * @param collection               the collection to be validated
     * @param runtimeExceptionSupplier the runtimeExceptionSupplier providing the RuntimeException to be thrown if the collection is null or empty
     * @return the collection passed as input
     * @throws RuntimeException if the collection is null or empty
     */
    public <T extends Collection<?>> T notEmpty(T collection, Supplier<RuntimeException> runtimeExceptionSupplier) throws RuntimeException {
        if (isNull(collection) || collection.isEmpty()) {
            throw getSupplierOrThrow(runtimeExceptionSupplier);
        }
        return collection;
    }

    /**
     * Validates that the provided collection is not null and not empty.
     * If the collection is null or empty, an EnsureException is thrown, which is
     * either created using the provided supplier or thrown directly if the supplier is null.
     *
     * @param <T>              the type of the collection
     * @param collection       the collection to check for non-emptiness
     * @param exceptionMessage the exception message to use if the collection is empty
     * @return the collection passed as input
     * @throws EnsureException if the collection is empty
     */
    public <T extends Collection<?>> T notEmpty(T collection, String exceptionMessage) throws EnsureException {
        return notEmpty(collection, () -> EnsureException.of(exceptionMessage));
    }

    /**
     * Validates that the provided collection is not null and not empty.
     * If the collection is null or empty, an EnsureException is thrown, which is
     * either created using the provided supplier or thrown directly if the supplier is null.
     *
     * @param <T>        the type of the collection
     * @param collection the collection to validate for non-emptiness
     * @return the collection passed as input
     * @throws EnsureException with the message "collection must not be empty or null" - if the collection is empty
     */
    public <T extends Collection<?>> T notEmpty(T collection) throws EnsureException {
        return notEmpty(collection, "collection must not be empty or null");
    }

}
