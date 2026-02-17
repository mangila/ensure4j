package io.github.mangila.ensure4j.ops;

import io.github.mangila.ensure4j.EnsureException;

import java.util.function.Supplier;

import static io.github.mangila.ensure4j.internal.EnsureUtils.getSupplierOrThrow;
import static io.github.mangila.ensure4j.internal.EnsureUtils.isNull;

/**
 * Provides utility methods for validating and operating on null.
 * This enum implements singleton behavior, ensuring a single instance is used throughout.
 */
public enum EnsureNullOps {
    INSTANCE;

    /**
     * Returns the given object if it is not null; otherwise, it evaluates and returns the result from the supplied {@link Supplier}.
     * If the fallbackSupplier is null or produces a null value, it throws an {@link EnsureException}.
     *
     * @param <T>              the type of the object being checked
     * @param object           the object to check for non-nullity
     * @param fallbackSupplier the fallbackSupplier to provide an alternative object if {@code object} is null
     * @return the non-null {@code object}, or the value provided by the {@code fallbackSupplier} if {@code object} is null
     * @throws EnsureException if the {@code fallbackSupplier} is null or produces a null value
     */
    public <T> T notNullOrElseGet(T object, Supplier<T> fallbackSupplier) throws EnsureException {
        if (isNull(object)) {
            return getSupplierOrThrow(fallbackSupplier);
        }
        return object;
    }

    /**
     * Returns the provided object if it is not null; otherwise, returns the given default object.
     *
     * @param <T>           the type of the objects being evaluated
     * @param object        the object to check for nullity
     * @param defaultObject the default object to return if {@code object} is null
     * @return {@code object} if it is not null, otherwise {@code defaultObject}
     */
    public <T> T notNullOrElse(T object, T defaultObject) {
        if (isNull(object)) {
            return defaultObject;
        }
        return object;
    }

    /**
     * Ensures that the specified object is not null. If the object is null, a RuntimeException
     * provided by the given runtimeExceptionSupplier is thrown.
     *
     * @param <T>                      the type of the object being checked
     * @param object                   the object to check for nullity
     * @param runtimeExceptionSupplier the runtimeExceptionSupplier that provides a RuntimeException to be thrown if the object is null
     * @return the non-null object
     * @throws RuntimeException if the object is null and the runtimeExceptionSupplier provides an exception
     *                          * @deprecated Use {@link #notNull(Object)} ()} instead.
     */
    @Deprecated(since = "3.0.2", forRemoval = true)
    public <T> T notNullOrElseThrow(T object, Supplier<RuntimeException> runtimeExceptionSupplier) throws RuntimeException {
        return notNull(object, runtimeExceptionSupplier);
    }

    /**
     * Ensures that the given object is not null, and returns the object if it is non-null.
     * If the object is null, this method throws a {@link RuntimeException}.
     *
     * @param <T>    the type of the object
     * @param object the object to be checked for nullity
     * @return the non-null object passed as input
     * @throws RuntimeException with the message "object must not be null" - if the object is null
     * @deprecated Use {@link #notNull(Object)} ()} instead.
     */
    @Deprecated(since = "3.0.2", forRemoval = true)
    public <T> T notNullOrElseThrow(T object) throws RuntimeException {
        return notNull(object, () -> EnsureException.of("object must not be null"));
    }

    /**
     * Ensures that the provided object is not null. If the object is null, a {@link RuntimeException}
     * provided by the given {@link Supplier} is thrown.
     *
     * @param <T>                      the type of the object
     * @param object                   the object to be checked for non-nullity
     * @param runtimeExceptionSupplier the runtimeExceptionSupplier responsible for providing the {@link RuntimeException} to be thrown if {@code object} is null
     * @return the non-null object
     * @throws RuntimeException if {@code object} is null, with the exception derived from the {@code runtimeExceptionSupplier}
     */
    public <T> T notNull(T object, Supplier<RuntimeException> runtimeExceptionSupplier) throws RuntimeException {
        if (isNull(object)) {
            throw getSupplierOrThrow(runtimeExceptionSupplier);
        }
        return object;
    }

    /**
     * Ensures that the provided object is not null. If the object is null, an {@link EnsureException}
     * with the given message is thrown.
     *
     * @param <T>     the type of the object
     * @param object  the object to be checked for non-nullity
     * @param message the exception message to be included if {@code object} is null
     * @return the non-null object
     * @throws EnsureException if {@code object} is null
     */
    public <T> T notNull(T object, String message) throws EnsureException {
        return notNull(object, () -> EnsureException.of(message));
    }

    /**
     * Ensures that the provided object is not null. If the object is null, an {@link EnsureException} is thrown.
     *
     * @param <T>    the type of the object
     * @param object the object to be checked for non-nullity
     * @return the non-null object
     * @throws EnsureException with the message "object must not be null" - if {@code object} is null
     */
    public <T> T notNull(T object) throws EnsureException {
        return notNull(object, "object must not be null");
    }
}
