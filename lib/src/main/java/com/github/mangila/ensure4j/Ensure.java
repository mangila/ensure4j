package com.github.mangila.ensure4j;


import org.jspecify.annotations.NonNull;

import java.util.function.Supplier;

/**
 * <p>
 * Utility class providing static methods to handle pre- and post-conditions
 * <br>
 * Ensure also brings utility methods for pre- and post-conditions during a functional programming pipeline/Java Stream so it can also return a value
 * </p>
 */
public final class Ensure {

    private Ensure() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Returns the given object if it is not null; otherwise, it evaluates and returns the result from the supplied {@link Supplier}.
     * If the supplier is null or produces a null value, it throws an {@link EnsureException}.
     *
     * @param <T>      the type of the object being checked
     * @param obj      the object to check for non-nullity
     * @param supplier the supplier to provide an alternative object if {@code obj} is null
     * @return the non-null {@code obj}, or the value provided by the {@code supplier} if {@code obj} is null
     * @throws EnsureException if the {@code supplier} is null or produces a null value
     */
    public static <T> T notNullOrElseGet(T obj, Supplier<T> supplier) throws EnsureException {
        if (isNull(obj)) {
            return getSupplierOrThrow(supplier);
        }
        return obj;
    }

    /**
     * Returns the provided object if it is not null; otherwise, returns the given default object.
     *
     * @param <T>        the type of the objects being evaluated
     * @param obj        the object to check for nullity
     * @param defaultObj the default object to return if {@code obj} is null
     * @return {@code obj} if it is not null, otherwise {@code defaultObj}
     */
    public static <T> T notNullOrElse(T obj, T defaultObj) {
        if (isNull(obj)) {
            return defaultObj;
        }
        return obj;
    }

    /**
     * Returns the provided object if it is not null; otherwise, it throws a {@link RuntimeException}
     * provided by the given {@link Supplier}.
     *
     * @param <T>      the type of the object being evaluated
     * @param obj      the object to be checked for non-nullity
     * @param supplier the supplier to provide the {@link RuntimeException} to throw if {@code obj} is null
     * @return the non-null {@code obj}
     * @throws RuntimeException if {@code obj} is null, derived from the exception provided by {@code supplier}
     */
    public static <T> T notNullOrElseThrow(T obj, Supplier<RuntimeException> supplier) throws RuntimeException {
        if (isNull(obj)) {
            throw getSupplierOrThrow(supplier);
        }
        return obj;
    }

    /**
     * Ensures that the provided object is not null. If the object is null, a {@link RuntimeException}
     * provided by the given {@link Supplier} is thrown.
     *
     * @param obj      the object to be checked for non-nullity
     * @param supplier the supplier responsible for providing the {@link RuntimeException} to be thrown if {@code obj} is null
     * @throws RuntimeException if {@code obj} is null, with the exception derived from the {@code supplier}
     */
    public static void notNull(Object obj, Supplier<RuntimeException> supplier) throws RuntimeException {
        if (isNull(obj)) {
            throw getSupplierOrThrow(supplier);
        }
    }

    /**
     * Ensures that the provided object is not null. If the object is null, an {@link EnsureException}
     * with the given message is thrown.
     *
     * @param obj     the object to be checked for non-nullity
     * @param message the exception message to be included if {@code obj} is null
     * @throws EnsureException if {@code obj} is null
     */
    public static void notNull(Object obj, String message) throws EnsureException {
        notNull(obj, () -> EnsureException.from(message));
    }

    /**
     * Ensures that the provided object is not null. If the object is null, an {@link EnsureException} is thrown.
     *
     * @param obj the object to be checked for non-nullity
     * @throws EnsureException if {@code obj} is null
     */
    public static void notNull(Object obj) throws EnsureException {
        notNull(obj, "object must not be null");
    }

    /**
     * Validates that the given boolean expression is true. If the expression evaluates to false,
     * a {@link RuntimeException} provided by the specified {@link Supplier} is thrown.
     *
     * @param expression the boolean expression to be evaluated
     * @param supplier   the supplier responsible for providing the {@link RuntimeException}
     *                   to be thrown if {@code expression} evaluates to false
     * @throws RuntimeException if {@code expression} evaluates to false, with the exception derived from the {@code supplier}
     */
    public static void isTrue(boolean expression,
                              Supplier<RuntimeException> supplier) throws RuntimeException {
        if (!expression) {
            throw getSupplierOrThrow(supplier);
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
        isTrue(expression, () -> EnsureException.from(exceptionMessage));
    }

    /**
     * Ensures that the provided boolean value is true. If the value is false,
     * an {@link EnsureException} with a default message is thrown.
     *
     * @param b the boolean value to check; must be true
     * @throws EnsureException if the provided value is false
     */
    public static void isTrue(boolean b) throws EnsureException {
        isTrue(b, "boolean must be true");
    }

    /**
     * Ensures that the provided boolean value is false. If the value is true, a {@link RuntimeException}
     * provided by the given {@link Supplier} is thrown.
     *
     * @param b        the boolean value to check
     * @param supplier the supplier responsible for providing the {@link RuntimeException}
     *                 to be thrown if {@code b} is true
     * @throws RuntimeException if {@code b} is true, with the exception derived from the {@code supplier}
     */
    public static void isFalse(boolean b,
                               Supplier<RuntimeException> supplier) throws RuntimeException {
        if (b) {
            throw getSupplierOrThrow(supplier);
        }
    }

    /**
     * Ensures that the provided boolean value is false. If the value is true,
     * an {@link EnsureException} with the provided exception message is thrown.
     *
     * @param b                the boolean value to be checked
     * @param exceptionMessage the exception message to include if {@code b} is true
     * @throws EnsureException if {@code b} is true
     */
    public static void isFalse(boolean b, String exceptionMessage) throws EnsureException {
        isFalse(b, () -> EnsureException.from(exceptionMessage));
    }

    /**
     * Ensures that the provided boolean value is false. If the value is true,
     * an {@link EnsureException} with a default message is thrown.
     *
     * @param b the boolean value to be checked
     * @throws EnsureException if {@code b} is true
     */
    public static void isFalse(boolean b) throws EnsureException {
        isFalse(b, "boolean must be false");
    }

    /**
     * Retrieves the value provided by the given {@link Supplier}. If the supplier is null or the supplied
     * value is null, an {@link EnsureException} is thrown.
     *
     * @param <T>      the type of the object supplied by the {@code Supplier}
     * @param supplier the {@link Supplier} to provide the value; must not be null and must not return null
     * @return the non-null value provided by the {@code supplier}
     * @throws EnsureException if the {@code supplier} is null or produces a null value
     */
    @NonNull
    private static <T> T getSupplierOrThrow(Supplier<T> supplier) {
        if (supplier == null) {
            throw new EnsureException("supplier was null");
        }
        T t = supplier.get();
        if (t == null) {
            throw new EnsureException("supplier was given a null value");
        }
        return t;
    }

    /**
     * Checks whether the given object is null.
     *
     * @param obj the object to check for nullity
     * @return true if the given object is null, false otherwise
     */
    private static boolean isNull(Object obj) {
        return obj == null;
    }

    /**
     * Checks whether the given object is not null.
     *
     * @param <T> the type of the object being checked
     * @param obj the object to check for non-null can be of any type
     * @return true if the given object is not null, false otherwise
     */
    private static <T> boolean isNotNull(T obj) {
        return obj != null;
    }
}