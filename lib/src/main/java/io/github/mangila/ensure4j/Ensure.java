package io.github.mangila.ensure4j;


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
     * Checks if the given number is less than the specified minimum value and throws an exception if the condition is met.
     *
     * @param min      the minimum allowable value
     * @param n        the number to be checked
     * @param supplier the supplier providing the exception to be thrown if the check fails
     * @throws RuntimeException if the number is less than the minimum value
     */
    public static void min(int min, int n, Supplier<RuntimeException> supplier) throws RuntimeException {
        if (n < min) {
            throw getSupplierOrThrow(supplier);
        }
    }

    /**
     * Ensures that the provided value meets the minimum required value, otherwise throws an exception.
     *
     * @param min              The minimum permissible value.
     * @param n                The value to check against the minimum.
     * @param exceptionMessage The message to be used in case an exception is thrown.
     * @throws EnsureException if the value does not meet the minimum requirement.
     */
    public static void min(int min, int n, String exceptionMessage) throws EnsureException {
        min(min, n, () -> EnsureException.from(exceptionMessage));
    }

    /**
     * Ensures that the given value is greater than or equal to the specified minimum value.
     *
     * @param min the minimum allowable value
     * @param n   the value to be checked against the minimum
     * @throws EnsureException if the value does not meet the specified minimum requirement
     */
    public static void min(int min, int n) throws EnsureException {
        min(min, n, "value must be greater than or equal to %d, but was %d".formatted(min, n));
    }

    /**
     * Ensures that the provided string is not blank. If the string is blank,
     * a {@link RuntimeException} provided by the given {@link Supplier} is thrown.
     *
     * @param s        the string to be checked for blankness
     * @param supplier the supplier responsible for providing the {@link RuntimeException}
     *                 to be thrown if {@code s} is blank
     * @throws RuntimeException if {@code s} is blank, with the exception derived from {@code supplier}
     */
    public static void notBlank(String s, Supplier<RuntimeException> supplier) throws RuntimeException {
        notNull(s, supplier);
        if (s.isBlank()) {
            throw getSupplierOrThrow(supplier);
        }
    }

    /**
     * Checks if the given string is blank (null, empty, or only contains whitespace) and
     * throws an exception if it is.
     *
     * @param s                the string to be checked for blankness
     * @param exceptionMessage the message to be included in the thrown exception if the string is blank
     * @throws EnsureException if the given string is blank
     */
    public static void notBlank(String s, String exceptionMessage) throws EnsureException {
        notBlank(s, () -> EnsureException.from(exceptionMessage));
    }

    /**
     * Checks if the provided string is blank (null, empty, or containing only whitespace).
     * Throws an EnsureException if the string does not meet the specified condition.
     *
     * @param s the string to be checked for blankness
     * @throws EnsureException if the string is blank
     */
    public static void notBlank(String s) throws EnsureException {
        notBlank(s, "string must not be blank");
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
     * Ensures that the given object is not null, and returns the object if it is non-null.
     * If the object is null, this method throws a {@link RuntimeException}.
     *
     * @param obj the object to be checked for nullity
     * @param <T> the type of the object
     * @return the non-null object passed as input
     * @throws RuntimeException if the object is null
     */
    public static <T> T notNullOrElseThrow(T obj) throws RuntimeException {
        if (isNull(obj)) {
            throw getSupplierOrThrow(() -> EnsureException.from("object must not be null"));
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
}