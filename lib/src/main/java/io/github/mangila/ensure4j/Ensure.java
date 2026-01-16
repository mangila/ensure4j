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
     * @param <T>      the type of the object
     * @param clazz    the class to check the object against
     * @param object   the object to verify
     * @param supplier a supplier that provides the exception to be thrown
     *                 if the object is not an instance of the specified class
     * @return the non-null object passed as input
     * @throws RuntimeException if the object is not an instance of the specified class
     */
    @SuppressWarnings("unchecked")
    public static <T> T isInstanceOf(Class<T> clazz, Object object, Supplier<RuntimeException> supplier) throws RuntimeException {
        notNull(clazz, supplier);
        if (!clazz.isInstance(object)) {
            throw getSupplierOrThrow(supplier);
        }
        return (T) object;
    }

    /**
     * Ensures that the specified object is an instance of the given class.
     * If the check fails, throws an EnsureException with the provided exception message.
     *
     * @param <T>              the type of the object
     * @param clazz            the class type to check against
     * @param object           the object to verify
     * @param exceptionMessage the message to include in the EnsureException if the check fails
     * @return the non-null object passed as input
     * @throws EnsureException if the object is not an instance of the specified class
     */
    public static <T> T isInstanceOf(Class<T> clazz, Object object, String exceptionMessage) throws EnsureException {
        return isInstanceOf(clazz, object, () -> EnsureException.of(exceptionMessage));
    }

    /**
     * Validates that the provided object is an instance of the specified class.
     *
     * @param <T>    the type of the object
     * @param clazz  the expected class that the object should be an instance of
     * @param object the object to be checked
     * @return the non-null object passed as input
     * @throws EnsureException with the message "object must be an instance of %s" - if the object is not an instance of the specified class
     */
    public static <T> T isInstanceOf(Class<T> clazz, Object object) throws EnsureException {
        notNull(clazz, "clazz must not be null");
        return isInstanceOf(clazz, object, "object must be an instance of %s".formatted(clazz.getName()));
    }

    /**
     * Compares two enum values for equality and throws an exception if they are not equal.
     *
     * @param <T>                      the type of the enum
     * @param enum1                    the first enum value to compare
     * @param enum2                    the second enum value to compare
     * @param runtimeExceptionSupplier a supplier that provides a RuntimeException to be thrown if the enum values are not equal
     * @return the first enum value if equal
     * @throws EnsureException if the enum values are not equal and the supplied exception is thrown
     */
    public static <T extends Enum<T>> T isEquals(T enum1, T enum2, Supplier<RuntimeException> runtimeExceptionSupplier) throws EnsureException {
        if (enum1 != enum2) {
            throw runtimeExceptionSupplier.get();
        }
        return enum1;
    }

    /**
     * Compares two Enum values for equality and throws an EnsureException if they are not equal.
     *
     * @param <T>              the type of the enum
     * @param enum1            The first Enum value to compare.
     * @param enum2            The second Enum value to compare.
     * @param exceptionMessage The message to include in the EnsureException if the values are not equal.
     * @return the first enum value if equal
     * @throws EnsureException if the two Enum values are not equal.
     */
    public static <T extends Enum<T>> T isEquals(T enum1, T enum2, String exceptionMessage) throws EnsureException {
        return isEquals(enum1, enum2, () -> EnsureException.of(exceptionMessage));
    }

    /**
     * Compares two enum values for equality and throws an exception if they are not equal.
     *
     * @param <T>   the type of the enum
     * @param enum1 the first Enum value to compare
     * @param enum2 the second Enum value to compare
     * @return the first enum value if equal
     * @throws EnsureException with the message "enums must be equal" - if the two Enum values are not equal
     */
    public static <T extends Enum<T>> T isEquals(T enum1, T enum2) throws EnsureException {
        return isEquals(enum1, enum2, "enums must be equal");
    }

    /**
     * Compares two objects for equality and throws a provided exception if they are not equal.
     * If both objects are the same instance or the first object equals the second, the method returns without exception.
     * If the first object is null or the objects are not equal, a custom exception is thrown.
     *
     * @param <T>                      the type of the object
     * @param object                   the first object to compare, must not be null
     * @param otherObject              the second object to compare with the first one
     * @param runtimeExceptionSupplier the runtimeExceptionSupplier providing the exception to be thrown if objects are not equal
     * @return the first object if equal
     * @throws RuntimeException if the first object is null or the objects are not equal
     */
    public static <T> T isEquals(T object, Object otherObject, Supplier<RuntimeException> runtimeExceptionSupplier) throws RuntimeException {
        if (object == otherObject) {
            return object;
        }
        notNull(object, runtimeExceptionSupplier);
        if (object.equals(otherObject)) {
            return object;
        }
        throw getSupplierOrThrow(runtimeExceptionSupplier);
    }

    /**
     * Compares two objects for equality and throws an EnsureException with the specified message if they are not equal.
     *
     * @param <T>              the type of the object
     * @param object           the first object to compare
     * @param otherObject      the second object to compare
     * @param exceptionMessage the message to include in the EnsureException if the objects are not equal
     * @return the first object if equal
     * @throws EnsureException if the objects are not equal
     */
    public static <T> T isEquals(T object, Object otherObject, String exceptionMessage) throws EnsureException {
        return isEquals(object, otherObject, () -> EnsureException.of(exceptionMessage));
    }

    /**
     * Asserts that two objects are equal. If they are not equal, an exception is thrown.
     *
     * @param <T>         the type of the object
     * @param object      the first object to compare
     * @param otherObject the second object to compare
     * @return the first object if equal
     * @throws EnsureException with the message "objects must be equal" - if the objects are not equal
     */
    public static <T> T isEquals(T object, Object otherObject) throws EnsureException {
        return isEquals(object, otherObject, "objects must be equal");
    }

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
    public static <T extends Collection<?>> T containsElement(T collection, Object element, Supplier<RuntimeException> runtimeExceptionSupplier) throws RuntimeException {
        notNull(collection, runtimeExceptionSupplier);
        if (!collection.contains(element)) {
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
    public static <T extends Collection<?>> T containsElement(T collection, Object element, String exceptionMessage) throws RuntimeException {
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
    public static <T extends Collection<?>> T containsElement(T collection, Object element) throws RuntimeException {
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
    public static <T extends Collection<?>> T notContainsNullLegacy(T collection, Supplier<RuntimeException> runtimeExceptionSupplier) throws EnsureException {
        notNull(collection, runtimeExceptionSupplier);
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
     * NOTE: There is no check if the collection is empty.
     *
     * @param <T>              the type of the collection
     * @param collection       the collection to be checked for null elements
     * @param exceptionMessage the message to be used in the exception if the condition is violated
     * @return the collection passed as input
     * @throws EnsureException if the collection contains null elements
     */
    public static <T extends Collection<?>> T notContainsNullLegacy(T collection, String exceptionMessage) throws EnsureException {
        return notContainsNullLegacy(collection, () -> EnsureException.of(exceptionMessage));
    }

    /**
     * Ensures that the provided collection does not contain any null elements.
     * <br>
     * NOTE: There is no check if the collection is empty.
     *
     * @param <T>        the type of the collection
     * @param collection the collection to be checked for null elements
     * @return the collection passed as input
     * @throws EnsureException with the message "collection must not contain null elements" - if the collection contains a null element
     */
    public static <T extends Collection<?>> T notContainsNullLegacy(T collection) throws EnsureException {
        return notContainsNullLegacy(collection, "collection must not contain null elements");
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
    public static <T extends Collection<?>> T notContainsNull(T collection, Supplier<RuntimeException> runtimeExceptionSupplier) throws RuntimeException {
        notNull(collection, runtimeExceptionSupplier);
        if (collection.contains(null)) {
            throw getSupplierOrThrow(runtimeExceptionSupplier);
        }
        return collection;
    }

    /**
     * Ensures that the provided collection does not contain any null elements.
     * <br>
     * NOTE: There is no check if the collection is empty.
     *
     * @param <T>              the type of the collection
     * @param collection       the collection to be checked for null elements
     * @param exceptionMessage the message to be used in the exception if the condition is violated
     * @return the collection passed as input
     * @throws EnsureException if the collection contains null elements
     */
    public static <T extends Collection<?>> T notContainsNull(T collection, String exceptionMessage) throws EnsureException {
        return notContainsNull(collection, () -> EnsureException.of(exceptionMessage));
    }

    /**
     * Ensures that the provided collection does not contain any null elements.
     * <br>
     * NOTE: There is no check if the collection is empty.
     *
     * @param <T>        the type of the collection
     * @param collection the collection to be checked for null elements
     * @return the collection passed as input
     * @throws EnsureException with the message "collection must not contain null elements" - if the collection contains a null element
     */
    public static <T extends Collection<?>> T notContainsNull(T collection) throws EnsureException {
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
    public static <T extends Collection<?>> T notEmpty(T collection, Supplier<RuntimeException> runtimeExceptionSupplier) throws RuntimeException {
        notNull(collection, runtimeExceptionSupplier);
        if (collection.isEmpty()) {
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
    public static <T extends Collection<?>> T notEmpty(T collection, String exceptionMessage) throws EnsureException {
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
     * @throws EnsureException with the message "collection must not be empty" - if the collection is empty
     */
    public static <T extends Collection<?>> T notEmpty(T collection) throws EnsureException {
        return notEmpty(collection, "collection must not be empty");
    }

    /**
     * Validates that the provided map is not empty. If the map is null or empty,
     * the exception provided by the supplied {@code runtimeExceptionSupplier} is thrown.
     *
     * @param <T>                      the type of the map
     * @param map                      the map to be validated
     * @param runtimeExceptionSupplier the runtimeExceptionSupplier that provides the exception to be thrown if
     *                                 the validation fails
     * @return the map passed as input
     * @throws RuntimeException if the map is null or empty
     */
    public static <T extends Map<?, ?>> T notEmpty(T map, Supplier<RuntimeException> runtimeExceptionSupplier) throws RuntimeException {
        notNull(map, runtimeExceptionSupplier);
        if (map.isEmpty()) {
            throw getSupplierOrThrow(runtimeExceptionSupplier);
        }
        return map;
    }

    /**
     * Ensures that the provided map is not empty. If the map is null or empty,
     * throws an EnsureException with the specified exception message.
     *
     * @param <T>              the type of the map
     * @param map              the map to check for emptiness
     * @param exceptionMessage the message to include in the exception if the map is empty
     * @return the map passed as input
     * @throws EnsureException if the map is null or empty
     */
    public static <T extends Map<?, ?>> T notEmpty(T map, String exceptionMessage) throws EnsureException {
        return notEmpty(map, () -> EnsureException.of(exceptionMessage));
    }

    /**
     * Validates that the provided map is not empty. If the map is null or empty,
     * an EnsureException will be thrown with a specified error message.
     *
     * @param <T> the type of the map
     * @param map the map to be checked for emptiness; must not be null or empty
     * @return the map passed as input
     * @throws EnsureException with the message "map must not be empty" - if the provided map is null or empty
     */
    public static <T extends Map<?, ?>> T notEmpty(T map) throws EnsureException {
        return notEmpty(map, "map must not be empty");
    }

    /**
     * Validates that the given array is not empty. If the array is null or empty,
     * the provided runtimeExceptionSupplier is used to throw an exception.
     * NOTE: Also check if null
     *
     * @param <T>                      the type of the array
     * @param array                    the array to check for non-emptiness
     * @param runtimeExceptionSupplier the runtimeExceptionSupplier providing the exception to be thrown if validation fails
     * @return the array passed as input
     * @throws RuntimeException if the array is null or empty
     */
    public static <T> T[] notEmpty(T[] array, Supplier<RuntimeException> runtimeExceptionSupplier) throws RuntimeException {
        notNull(array, runtimeExceptionSupplier);
        if (array.length == 0) {
            throw getSupplierOrThrow(runtimeExceptionSupplier);
        }
        return array;
    }

    /**
     * Checks if the provided array is not empty. If the array is empty,
     * throws an EnsureException with the provided exception message.
     * NOTE: Also check if null
     *
     * @param <T>              the type of the array
     * @param array            the array to check for non-emptiness
     * @param exceptionMessage the message to include in the exception if the array is empty
     * @return the array passed as input
     * @throws EnsureException if the array is null or empty
     */
    public static <T> T[] notEmpty(T[] array, String exceptionMessage) throws EnsureException {
        return notEmpty(array, () -> EnsureException.of(exceptionMessage));
    }

    /**
     * Ensures that the provided array is not empty. If the array is empty, an EnsureException is thrown.
     * NOTE: Also check if null
     *
     * @param <T>   the type of the array
     * @param array the array to be checked
     * @return the array passed as input
     * @throws EnsureException with the message "array must not be empty" - if the array is empty
     */
    public static <T> T[] notEmpty(T[] array) throws EnsureException {
        return notEmpty(array, "array must not be empty");
    }

    /**
     * Returns the given value if it is less than or equal to the specified boundary.
     * If the value exceeds the boundary, the provided RuntimeException is thrown.
     *
     * @param boundary                 the maximum allowed value
     * @param value                    the value to be checked against the boundary
     * @param runtimeExceptionSupplier the supplier of the RuntimeException to be thrown if the value exceeds the boundary
     * @return the value if it is less than or equal to the boundary
     * @throws RuntimeException if the value exceeds the specified boundary
     */
    public static long max(long boundary, long value, Supplier<RuntimeException> runtimeExceptionSupplier) throws RuntimeException {
        if (value > boundary) {
            throw getSupplierOrThrow(runtimeExceptionSupplier);
        }
        return value;
    }

    /**
     * Ensures that the given value does not exceed the specified boundary.
     * If the value exceeds the boundary, an exception with the provided message is thrown.
     *
     * @param boundary         the maximum allowable value
     * @param value            the value to be checked
     * @param exceptionMessage the message to be used in the exception if the boundary is exceeded
     * @return the value if it does not exceed the boundary
     * @throws EnsureException if the value exceeds the boundary
     */
    public static long max(long boundary, long value, String exceptionMessage) throws EnsureException {
        return max(boundary, value, () -> EnsureException.of(exceptionMessage));
    }

    /**
     * Returns the maximum of the boundary and value. If the value exceeds the boundary, an EnsureException is thrown.
     *
     * @param boundary the upper limit that the value must not exceed
     * @param value    the value to compare against the boundary
     * @return the maximum of the boundary and the value
     * @throws EnsureException with the message "value must be less than or equal to %d, but was %d" - if the given value exceeds the maximum allowable value
     */
    public static long max(long boundary, long value) throws EnsureException {
        return max(boundary, value, "value must be less than or equal to %d, but was %d".formatted(boundary, value));
    }

    /**
     * Compares the provided number with a specified maximum value and throws a runtime exception if the number exceeds the maximum.
     *
     * @param boundary                 the maximum allowable value
     * @param value                    the number to compare against the maximum
     * @param runtimeExceptionSupplier a runtimeExceptionSupplier for creating the runtime exception to be thrown if the provided number exceeds the maximum
     * @return the provided number if it does not exceed the maximum
     * @throws RuntimeException if the provided number exceeds the maximum value
     */
    public static int max(int boundary, int value, Supplier<RuntimeException> runtimeExceptionSupplier) throws RuntimeException {
        if (value > boundary) {
            throw getSupplierOrThrow(runtimeExceptionSupplier);
        }
        return value;
    }

    /**
     * Determines the maximum value between the specified integer values, and throws an {@code EnsureException}
     * with the provided message if the conditions are not met.
     *
     * @param boundary         the current maximum value to compare against
     * @param value            the new integer value to be compared
     * @param exceptionMessage the exception message to be used if the exception is thrown
     * @return the greater of {@code boundary} and {@code value}
     * @throws EnsureException if the conditions for returning the maximum value fail
     */
    public static int max(int boundary, int value, String exceptionMessage) throws EnsureException {
        return max(boundary, value, () -> EnsureException.of(exceptionMessage));
    }

    /**
     * Compares two integers and ensures the second integer is less than or equal to the first integer.
     *
     * @param boundary the maximum allowable value
     * @param value    the value to compare against the maximum
     * @return the maximum value if the condition is met
     * @throws EnsureException with the message "value must be less than or equal to %d, but was %d" - if the given value exceeds the maximum allowable value
     */
    public static int max(int boundary, int value) throws EnsureException {
        return max(boundary, value, "value must be less than or equal to %d, but was %d".formatted(boundary, value));
    }

    /**
     * Ensures that the provided value is greater than or equal to the specified boundary.
     * If the value is less than the boundary, the provided exception supplier is used
     * to generate and throw a RuntimeException.
     *
     * @param boundary                 the minimum allowable value
     * @param value                    the value to be checked
     * @param runtimeExceptionSupplier a supplier that provides the RuntimeException to be thrown if the value is invalid
     * @return the provided value if it meets or exceeds the boundary
     * @throws RuntimeException if the value is less than the boundary, based on the supplied exception
     */
    public static long min(long boundary, long value, Supplier<RuntimeException> runtimeExceptionSupplier) throws RuntimeException {
        if (value < boundary) {
            throw getSupplierOrThrow(runtimeExceptionSupplier);
        }
        return value;
    }

    /**
     * Ensures that a given value meets or exceeds a specified boundary.
     * If the value is less than the boundary, an exception with the specified message is thrown.
     *
     * @param boundary         the minimum permissible value
     * @param value            the value to be validated
     * @param exceptionMessage the message to be included in the exception if the validation fails
     * @return the validated value if it meets or exceeds the boundary
     * @throws EnsureException if the value is less than the boundary
     */
    public static long min(long boundary, long value, String exceptionMessage) throws EnsureException {
        return min(boundary, value, () -> EnsureException.of(exceptionMessage));
    }

    /**
     * Determines the minimum value constrained by a specified boundary.
     * If the given value is smaller than the boundary, an exception will be thrown.
     *
     * @param boundary the lower boundary that the value must meet or exceed
     * @param value    the value to be compared against the boundary
     * @return the value if it is greater than or equal to the boundary
     * @throws EnsureException with the message "value must be greater than or equal to %d, but was %d" - if the value is less than the specified minimum
     */
    public static long min(long boundary, long value) throws EnsureException {
        return min(boundary, value, "value must be greater than or equal to %d, but was %d".formatted(boundary, value));
    }

    /**
     * Compares a value against a minimum threshold and throws a supplied exception if the value is less than the threshold.
     *
     * @param boundary                 the minimum threshold value
     * @param value                    the value to compare against the minimum
     * @param runtimeExceptionSupplier the runtimeExceptionSupplier that provides the runtime exception to be thrown if the comparison fails
     * @return the value of value if it is greater than or equal to the minimum threshold
     * @throws RuntimeException the exception supplied by the runtimeExceptionSupplier, thrown when the value is less than the minimum threshold
     */
    public static int min(int boundary, int value, Supplier<RuntimeException> runtimeExceptionSupplier) throws RuntimeException {
        if (value < boundary) {
            throw getSupplierOrThrow(runtimeExceptionSupplier);
        }
        return value;
    }

    /**
     * Compares the given integers and ensures that the second integer is not less than the minimum value,
     * throwing an exception with a specified message if the condition is violated.
     *
     * @param boundary         the minimum value to compare against
     * @param value            the value to be compared to the minimum value
     * @param exceptionMessage the message to use for the exception if the condition is not met
     * @return the greater value between the specified minimum value and the provided integer
     * @throws EnsureException if the provided integer is less than the minimum value
     */
    public static int min(int boundary, int value, String exceptionMessage) throws EnsureException {
        return min(boundary, value, () -> EnsureException.of(exceptionMessage));
    }

    /**
     * Compares two integers and ensures that the given value is greater than or equal to the defined minimum.
     *
     * @param boundary the minimum allowed value
     * @param value    the value to compare against the minimum
     * @return the minimum value, or throws an exception if the condition is not met
     * @throws EnsureException with the message "value must be greater than or equal to %d, but was %d" - if the value is less than the specified minimum
     */
    public static int min(int boundary, int value) throws EnsureException {
        return min(boundary, value, "value must be greater than or equal to %d, but was %d".formatted(boundary, value));
    }

    /**
     * Returns the provided string if it is not blank (non-null and containing non-whitespace characters),
     * otherwise retrieves and returns a value from the provided fallback supplier.
     *
     * @param string           the string to check for blankness
     * @param fallbackSupplier the supplier to retrieve an alternative value if the string is blank
     * @return the original string if it is not blank, or the value supplied by the fallback supplier if the string is blank
     */
    public static String notBlankOrElseGet(String string, Supplier<String> fallbackSupplier) {
        notNull(string);
        if (isBlank(string)) {
            return fallbackSupplier.get();
        }
        return string;
    }

    /**
     * Returns the given string if it is not blank; otherwise, returns the specified fallback value.
     * A string is considered blank if it is null, empty, or contains only whitespace characters.
     *
     * @param string        the input string to check
     * @param fallbackValue the fallback value to return if the input string is blank
     * @return the input string if it is not blank; otherwise, the fallback value
     */
    public static String notBlankOrElse(String string, String fallbackValue) {
        notNull(string);
        if (isBlank(string)) {
            return fallbackValue;
        }
        return string;
    }

    /**
     * Ensures that the provided string is not blank. If the string is blank or null,
     * the specified exception supplied by the runtimeExceptionSupplier is thrown.
     *
     * @param string                   the string to be validated as not blank
     * @param runtimeExceptionSupplier the runtimeExceptionSupplier that provides the exception to throw if the string is blank
     * @return the validated string if it is not blank
     * @throws RuntimeException if the string is blank or null, provided by the runtimeExceptionSupplier
     */
    public static String notBlank(String string, Supplier<RuntimeException> runtimeExceptionSupplier) throws RuntimeException {
        notNull(string, runtimeExceptionSupplier);
        if (isBlank(string)) {
            throw getSupplierOrThrow(runtimeExceptionSupplier);
        }
        return string;
    }

    /**
     * Ensures that the provided string is not blank. If the string is blank or null,
     * an exception is thrown with the provided exception message.
     *
     * @param string           the string to check for being non-blank
     * @param exceptionMessage the message to include in the thrown exception if the string is blank
     * @return the original string if it is not blank
     * @throws EnsureException if the string is blank
     */
    public static String notBlank(String string, String exceptionMessage) throws EnsureException {
        return notBlank(string, () -> EnsureException.of(exceptionMessage));
    }

    /**
     * Ensures that the provided string is not blank. If the string is blank or null, an exception is thrown with a default message.
     *
     * @param string the string to validate
     * @return the original string if it is not blank
     * @throws EnsureException with the message - "string must not be blank" if the string is blank
     */
    public static String notBlank(String string) throws EnsureException {
        return notBlank(string, "string must not be blank");
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
        final T t = supplier.get();
        if (t == null) {
            throw new EnsureException("supplier was given a null value");
        }
        return t;
    }

    /**
     * Checks whether the given object is null.
     *
     * @param object the object to check for nullity
     * @return true if the given object is null, false otherwise
     */
    private static boolean isNull(Object object) {
        return object == null;
    }

    /**
     * Checks if the provided string is blank. A string is considered blank if it is empty
     * or contains only white-space characters.
     *
     * @param string the input string to check for blankness
     * @return true if the string is blank, false otherwise
     */
    private static boolean isBlank(@NonNull String string) {
        return string.isBlank();
    }
}