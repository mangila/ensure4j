package io.github.mangila.ensure4j;


import org.jspecify.annotations.NonNull;

import java.util.Collection;
import java.util.Map;
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
     * Checks if the given object is an instance of the specified class.
     * If the object is not an instance of the class, a RuntimeException
     * provided by the supplier is thrown.
     *
     * @param clazz    the class to check the object against
     * @param obj      the object to verify
     * @param supplier a supplier that provides the exception to be thrown
     *                 if the object is not an instance of the specified class
     * @throws RuntimeException if the object is not an instance of the specified class
     */
    public static void isInstanceOf(Class<?> clazz, Object obj, Supplier<RuntimeException> supplier) throws RuntimeException {
        notNull(clazz, supplier);
        if (!clazz.isInstance(obj)) {
            throw getSupplierOrThrow(supplier);
        }
    }

    /**
     * Ensures that the specified object is an instance of the given class.
     * If the check fails, throws an EnsureException with the provided exception message.
     *
     * @param clazz            the class type to check against
     * @param obj              the object to verify
     * @param exceptionMessage the message to include in the EnsureException if the check fails
     * @throws EnsureException if the object is not an instance of the specified class
     */
    public static void isInstanceOf(Class<?> clazz, Object obj, String exceptionMessage) throws EnsureException {
        isInstanceOf(clazz, obj, () -> EnsureException.of(exceptionMessage));
    }

    /**
     * Validates that the provided object is an instance of the specified class.
     *
     * @param clazz the expected class that the object should be an instance of
     * @param obj   the object to be checked
     * @throws EnsureException with the message "object must be an instance of %s" - if the object is not an instance of the specified class
     */
    public static void isInstanceOf(Class<?> clazz, Object obj) throws EnsureException {
        notNull(clazz, "clazz must not be null");
        isInstanceOf(clazz, obj, "object must be an instance of %s".formatted(clazz.getName()));
    }

    /**
     * Compares two objects for equality and throws a provided exception if they are not equal.
     * If both objects are the same instance or the first object equals the second, the method returns without exception.
     * If the first object is null or the objects are not equal, a custom exception is thrown.
     *
     * @param object   the first object to compare, must not be null
     * @param other    the second object to compare with the first one
     * @param supplier the supplier providing the exception to be thrown if objects are not equal
     * @throws RuntimeException if the first object is null or the objects are not equal
     */
    public static void equals(Object object, Object other, Supplier<RuntimeException> supplier) throws RuntimeException {
        if (object == other) {
            return;
        }
        notNull(object, supplier);
        if (object.equals(other)) {
            return;
        }


        throw getSupplierOrThrow(supplier);
    }

    /**
     * Compares two objects for equality and throws an EnsureException with the specified message if they are not equal.
     *
     * @param object           the first object to compare
     * @param other            the second object to compare
     * @param exceptionMessage the message to include in the EnsureException if the objects are not equal
     * @throws EnsureException if the objects are not equal
     */
    public static void equals(Object object, Object other, String exceptionMessage) throws EnsureException {
        equals(object, other, () -> EnsureException.of(exceptionMessage));
    }

    /**
     * Asserts that two objects are equal. If they are not equal, an exception is thrown.
     *
     * @param object the first object to compare
     * @param other  the second object to compare
     * @throws EnsureException with the message "objects must be equal" - if the objects are not equal
     */
    public static void equals(Object object, Object other) throws EnsureException {
        equals(object, other, () -> EnsureException.of("objects must be equal"));
    }

    /**
     * Checks if the provided collection does not contain any null elements.
     * If the collection contains a null element, an exception is thrown using the provided supplier.
     * <br>
     * NOTE: No check if the collection is empty is performed.
     *
     * @param collection the collection to be checked for null elements
     * @param supplier   the supplier of the exception to be thrown if a null element is found
     * @throws EnsureException if the collection contains a null element
     */
    public static void notContainsNull(Collection<?> collection, Supplier<RuntimeException> supplier) throws EnsureException {
        notNull(collection, supplier);
        if (collection.contains(null)) {
            throw getSupplierOrThrow(supplier);
        }
    }

    /**
     * Ensures that the provided collection does not contain any null elements.
     * <br>
     * NOTE: No check if the collection is empty is performed.
     *
     * @param collection       the collection to be checked for null elements
     * @param exceptionMessage the message to be used in the exception if the condition is violated
     * @throws EnsureException if the collection contains null elements
     */
    public static void notContainsNull(Collection<?> collection, String exceptionMessage) throws EnsureException {
        notContainsNull(collection, () -> EnsureException.of(exceptionMessage));
    }

    /**
     * Ensures that the provided collection does not contain any null elements.
     * <br>
     * NOTE: No check if the collection is empty is performed.
     *
     * @param collection the collection to be checked for null elements
     * @throws EnsureException with the message "collection must not contain null elements" - if the collection contains a null element
     */
    public static void notContainsNull(Collection<?> collection) throws EnsureException {
        notContainsNull(collection, "collection must not contain null elements");
    }

    /**
     * Validates that the provided collection is not null and not empty.
     * If the collection is null or empty, a RuntimeException is thrown, which is
     * either created using the provided supplier or thrown directly if the supplier is null.
     *
     * @param collection the collection to be validated
     * @param supplier   the supplier providing the RuntimeException to be thrown if the collection is null or empty
     * @throws RuntimeException if the collection is null or empty
     */
    public static void notEmpty(Collection<?> collection, Supplier<RuntimeException> supplier) throws RuntimeException {
        notNull(collection, supplier);
        if (collection.isEmpty()) {
            throw getSupplierOrThrow(supplier);
        }
    }

    /**
     * Validates that the provided collection is not null and not empty.
     * If the collection is null or empty, an EnsureException is thrown, which is
     * either created using the provided supplier or thrown directly if the supplier is null.
     *
     * @param collection       the collection to check for non-emptiness
     * @param exceptionMessage the exception message to use if the collection is empty
     * @throws EnsureException if the collection is empty
     */
    public static void notEmpty(Collection<?> collection, String exceptionMessage) throws EnsureException {
        notEmpty(collection, () -> EnsureException.of(exceptionMessage));
    }

    /**
     * Validates that the provided collection is not null and not empty.
     * If the collection is null or empty, an EnsureException is thrown, which is
     * either created using the provided supplier or thrown directly if the supplier is null.
     *
     * @param collection the collection to validate for non-emptiness
     * @throws EnsureException with the message "collection must not be empty" - if the collection is empty
     */
    public static void notEmpty(Collection<?> collection) throws EnsureException {
        notEmpty(collection, "collection must not be empty");
    }

    /**
     * Validates that the provided map is not empty. If the map is null or empty,
     * the exception provided by the supplied {@code supplier} is thrown.
     *
     * @param map      the map to be validated
     * @param supplier the supplier that provides the exception to be thrown if
     *                 the validation fails
     * @throws RuntimeException if the map is null or empty
     */
    public static void notEmpty(Map<?, ?> map, Supplier<RuntimeException> supplier) throws RuntimeException {
        notNull(map, supplier);
        if (map.isEmpty()) {
            throw getSupplierOrThrow(supplier);
        }
    }

    /**
     * Ensures that the provided map is not empty. If the map is null or empty,
     * throws an EnsureException with the specified exception message.
     *
     * @param map              the map to check for emptiness
     * @param exceptionMessage the message to include in the exception if the map is empty
     * @throws EnsureException if the map is null or empty
     */
    public static void notEmpty(Map<?, ?> map, String exceptionMessage) throws EnsureException {
        notEmpty(map, () -> EnsureException.of(exceptionMessage));
    }

    /**
     * Validates that the provided map is not empty. If the map is null or empty,
     * an EnsureException will be thrown with a specified error message.
     *
     * @param map the map to be checked for emptiness; must not be null or empty
     * @throws EnsureException with the message "map must not be empty" - if the provided map is null or empty
     */
    public static void notEmpty(Map<?, ?> map) throws EnsureException {
        notEmpty(map, "map must not be empty");
    }

    /**
     * Validates that the given array is not empty. If the array is null or empty,
     * the provided supplier is used to throw an exception.
     *
     * @param array    the array to check for non-emptiness
     * @param supplier the supplier providing the exception to be thrown if validation fails
     * @throws RuntimeException if the array is null or empty
     */
    public static void notEmpty(Object[] array, Supplier<RuntimeException> supplier) throws RuntimeException {
        notNull(array, supplier);
        if (array.length == 0) {
            throw getSupplierOrThrow(supplier);
        }
    }

    /**
     * Checks if the provided array is not empty. If the array is empty,
     * throws an EnsureException with the provided exception message.
     *
     * @param array            the array to check for non-emptiness
     * @param exceptionMessage the message to include in the exception if the array is empty
     * @throws EnsureException if the array is empty
     */
    public static void notEmpty(Object[] array, String exceptionMessage) throws EnsureException {
        notEmpty(array, () -> EnsureException.of(exceptionMessage));
    }

    /**
     * Ensures that the provided array is not empty. If the array is empty, an EnsureException is thrown.
     *
     * @param array the array to be checked
     * @throws EnsureException with the message "array must not be empty" - if the array is empty
     */
    public static void notEmpty(Object[] array) throws EnsureException {
        notEmpty(array, "array must not be empty");
    }

    /**
     * Compares the provided number with a specified maximum value and throws a runtime exception if the number exceeds the maximum.
     *
     * @param max      the maximum allowable value
     * @param n        the number to compare against the maximum
     * @param supplier a supplier for creating the runtime exception to be thrown if the provided number exceeds the maximum
     * @return the provided number if it does not exceed the maximum
     * @throws RuntimeException if the provided number exceeds the maximum value
     */
    public static int max(int max, int n, Supplier<RuntimeException> supplier) throws RuntimeException {
        if (n > max) {
            throw getSupplierOrThrow(supplier);
        }
        return n;
    }

    /**
     * Determines the maximum value between the specified integer values, and throws an {@code EnsureException}
     * with the provided message if the conditions are not met.
     *
     * @param max              the current maximum value to compare against
     * @param n                the new integer value to be compared
     * @param exceptionMessage the exception message to be used if the exception is thrown
     * @return the greater of {@code max} and {@code n}
     * @throws EnsureException if the conditions for returning the maximum value fail
     */
    public static int max(int max, int n, String exceptionMessage) throws EnsureException {
        return max(max, n, () -> EnsureException.of(exceptionMessage));
    }

    /**
     * Compares two integers and ensures the second integer is less than or equal to the first integer.
     *
     * @param max the maximum allowable value
     * @param n   the value to compare against the maximum
     * @return the maximum value if the condition is met
     * @throws EnsureException with the message "value must be less than or equal to %d, but was %d" - if the given value exceeds the maximum allowable value
     */
    public static int max(int max, int n) throws EnsureException {
        return max(max, n, "value must be less than or equal to %d, but was %d".formatted(max, n));
    }

    /**
     * Compares a value against a minimum threshold and throws a supplied exception if the value is less than the threshold.
     *
     * @param min      the minimum threshold value
     * @param n        the value to compare against the minimum
     * @param supplier the supplier that provides the runtime exception to be thrown if the comparison fails
     * @return the value of n if it is greater than or equal to the minimum threshold
     * @throws RuntimeException the exception supplied by the supplier, thrown when the value is less than the minimum threshold
     */
    public static int min(int min, int n, Supplier<RuntimeException> supplier) throws RuntimeException {
        if (n < min) {
            throw getSupplierOrThrow(supplier);
        }
        return n;
    }

    /**
     * Compares the given integers and ensures that the second integer is not less than the minimum value,
     * throwing an exception with a specified message if the condition is violated.
     *
     * @param min              the minimum value to compare against
     * @param n                the value to be compared to the minimum value
     * @param exceptionMessage the message to use for the exception if the condition is not met
     * @return the greater value between the specified minimum value and the provided integer
     * @throws EnsureException if the provided integer is less than the minimum value
     */
    public static int min(int min, int n, String exceptionMessage) throws EnsureException {
        return min(min, n, () -> EnsureException.of(exceptionMessage));
    }

    /**
     * Compares two integers and ensures that the given value is greater than or equal to the defined minimum.
     *
     * @param min the minimum allowed value
     * @param n   the value to compare against the minimum
     * @return the minimum value, or throws an exception if the condition is not met
     * @throws EnsureException with the message "value must be greater than or equal to %d, but was %d" - if the value is less than the specified minimum
     */
    public static int min(int min, int n) throws EnsureException {
        return min(min, n, "value must be greater than or equal to %d, but was %d".formatted(min, n));
    }

    /**
     * Ensures that the provided string is not blank. If the string is blank or null,
     * the specified exception supplied by the supplier is thrown.
     *
     * @param s        the string to be validated as not blank
     * @param supplier the supplier that provides the exception to throw if the string is blank
     * @return the validated string if it is not blank
     * @throws RuntimeException if the string is blank or null, provided by the supplier
     */
    public static String notBlank(String s, Supplier<RuntimeException> supplier) throws RuntimeException {
        notNull(s, supplier);
        if (s.isBlank()) {
            throw getSupplierOrThrow(supplier);
        }
        return s;
    }

    /**
     * Ensures that the provided string is not blank. If the string is blank or null,
     * an exception is thrown with the provided exception message.
     *
     * @param s                the string to check for being non-blank
     * @param exceptionMessage the message to include in the thrown exception if the string is blank
     * @return the original string if it is not blank
     * @throws EnsureException if the string is blank
     */
    public static String notBlank(String s, String exceptionMessage) throws EnsureException {
        return notBlank(s, () -> EnsureException.of(exceptionMessage));
    }

    /**
     * Ensures that the provided string is not blank. If the string is blank or null, an exception is thrown with a default message.
     *
     * @param s the string to validate
     * @return the original string if it is not blank
     * @throws EnsureException with the message - "string must not be blank" if the string is blank
     */
    public static String notBlank(String s) throws EnsureException {
        return notBlank(s, "string must not be blank");
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
     * Ensures that the specified object is not null. If the object is null, a RuntimeException
     * provided by the given supplier is thrown.
     *
     * @param <T>      the type of the object being checked
     * @param obj      the object to check for nullity
     * @param supplier the supplier that provides a RuntimeException to be thrown if the object is null
     * @return the non-null object
     * @throws RuntimeException if the object is null and the supplier provides an exception
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
     * @throws RuntimeException with the message "object must not be null" - if the object is null
     */
    public static <T> T notNullOrElseThrow(T obj) throws RuntimeException {
        if (isNull(obj)) {
            throw getSupplierOrThrow(() -> EnsureException.of("object must not be null"));
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
        notNull(obj, () -> EnsureException.of(message));
    }

    /**
     * Ensures that the provided object is not null. If the object is null, an {@link EnsureException} is thrown.
     *
     * @param obj the object to be checked for non-nullity
     * @throws EnsureException with the message "object must not be null" - if {@code obj} is null
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
        isTrue(expression, () -> EnsureException.of(exceptionMessage));
    }

    /**
     * Ensures that the provided boolean value is true. If the value is false,
     * an {@link EnsureException} with a default message is thrown.
     *
     * @param b the boolean value to check; must be true
     * @throws EnsureException with the message "boolean must be true" - if the provided value is false
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
        isFalse(b, () -> EnsureException.of(exceptionMessage));
    }

    /**
     * Ensures that the provided boolean value is false. If the value is true,
     * an {@link EnsureException} with a default message is thrown.
     *
     * @param b the boolean value to be checked
     * @throws EnsureException with the message "boolean must be false" - if {@code b} is true
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