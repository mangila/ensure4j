package io.github.mangila.ensure4j;


import io.github.mangila.ensure4j.ops.*;
import org.jspecify.annotations.NonNull;

import java.util.function.Supplier;

import static io.github.mangila.ensure4j.internal.EnsureUtils.getSupplierOrThrow;
import static io.github.mangila.ensure4j.internal.EnsureUtils.isNull;

/**
 * <p>
 * Acts as a utility hub for typed pre- and post-condition checks but keeps the most frequently used ones in the top level class.
 * Like null check and boolean checks.
 * </p>
 */
public final class Ensure {

    private static final ArrayOps ARRAY_OPS = ArrayOps.INSTANCE;
    private static final CollectionOps COLLECTION_OPS = CollectionOps.INSTANCE;
    private static final MapOps MAP_OPS = MapOps.INSTANCE;
    private static final NumberOps NUMBER_OPS = NumberOps.INSTANCE;
    private static final ObjectOps OBJECT_OPS = ObjectOps.INSTANCE;
    private static final StringOps STRING_OPS = StringOps.INSTANCE;

    private Ensure() {
        throw new IllegalStateException("Utility class");
    }

    @NonNull
    public static ArrayOps arrays() {
        return ARRAY_OPS;
    }

    @NonNull
    public static CollectionOps collections() {
        return COLLECTION_OPS;
    }

    @NonNull
    public static MapOps maps() {
        return MAP_OPS;
    }

    @NonNull
    public static NumberOps numbers() {
        return NUMBER_OPS;
    }

    @NonNull
    public static ObjectOps objects() {
        return OBJECT_OPS;
    }

    @NonNull
    public static StringOps strings() {
        return STRING_OPS;
    }

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
    public static <T> T notNullOrElseGet(T object, Supplier<T> fallbackSupplier) throws EnsureException {
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
    public static <T> T notNullOrElse(T object, T defaultObject) {
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
     */
    public static <T> T notNullOrElseThrow(T object, Supplier<RuntimeException> runtimeExceptionSupplier) throws RuntimeException {
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
     */
    public static <T> T notNullOrElseThrow(T object) throws RuntimeException {
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
    public static <T> T notNull(T object, Supplier<RuntimeException> runtimeExceptionSupplier) throws RuntimeException {
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
    public static <T> T notNull(T object, String message) throws EnsureException {
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
    public static <T> T notNull(T object) throws EnsureException {
        return notNull(object, "object must not be null");
    }

    /**
     * Validates that the given boolean expression is true. If the expression evaluates to false,
     * a {@link RuntimeException} provided by the specified {@link Supplier} is thrown.
     *
     * @param expression               the boolean expression to be evaluated
     * @param runtimeExceptionSupplier the runtimeExceptionSupplier responsible for providing the {@link RuntimeException}
     *                                 to be thrown if {@code expression} evaluates to false
     * @throws RuntimeException if {@code expression} evaluates to false, with the exception derived from the {@code runtimeExceptionSupplier}
     */
    public static void isTrue(boolean expression,
                              Supplier<RuntimeException> runtimeExceptionSupplier) throws RuntimeException {
        if (!expression) {
            throw getSupplierOrThrow(runtimeExceptionSupplier);
        }
    }

    /**
     * Ensures that the provided expression evaluates to {@code true}. If the expression evaluates to {@code false},
     * an {@link EnsureException} is thrown with the provided exception message.
     *
     * @param expression       the boolean expression to be evaluated
     * @param exceptionMessage the exception message to be included if the expression evaluates to {@code false}
     * @throws EnsureException if {@code expression} evaluates to {@code false}
     */
    public static void isTrue(boolean expression, String exceptionMessage) throws EnsureException {
        isTrue(expression, () -> EnsureException.of(exceptionMessage));
    }

    /**
     * Ensures that the provided boolean value is true. If the value is false,
     * an {@link EnsureException} with a default message is thrown.
     *
     * @param expression the boolean value to check; must be true
     * @throws EnsureException with the message "boolean must be true" - if the provided value is false
     */
    public static void isTrue(boolean expression) throws EnsureException {
        isTrue(expression, "boolean must be true");
    }

    /**
     * Ensures that the provided boolean value is false. If the value is true, a {@link RuntimeException}
     * provided by the given {@link Supplier} is thrown.
     *
     * @param expression               the boolean value to check
     * @param runtimeExceptionSupplier the runtimeExceptionSupplier responsible for providing the {@link RuntimeException}
     *                                 to be thrown if {@code expression} is true
     * @throws RuntimeException if {@code expression} is true, with the exception derived from the {@code runtimeExceptionSupplier}
     */
    public static void isFalse(boolean expression,
                               Supplier<RuntimeException> runtimeExceptionSupplier) throws RuntimeException {
        if (expression) {
            throw getSupplierOrThrow(runtimeExceptionSupplier);
        }
    }

    /**
     * Ensures that the provided boolean value is false. If the value is true,
     * an {@link EnsureException} with the provided exception message is thrown.
     *
     * @param expression       the boolean value to be checked
     * @param exceptionMessage the exception message to include if {@code expression} is true
     * @throws EnsureException if {@code expression} is true
     */
    public static void isFalse(boolean expression, String exceptionMessage) throws EnsureException {
        isFalse(expression, () -> EnsureException.of(exceptionMessage));
    }

    /**
     * Ensures that the provided boolean value is false. If the value is true,
     * an {@link EnsureException} with a default message is thrown.
     *
     * @param expression the boolean value to be checked
     * @throws EnsureException with the message "boolean must be false" - if {@code expression} is true
     */
    public static void isFalse(boolean expression) throws EnsureException {
        isFalse(expression, "boolean must be false");
    }
}