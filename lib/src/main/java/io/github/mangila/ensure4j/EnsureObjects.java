package io.github.mangila.ensure4j;

import java.util.function.Supplier;

import static io.github.mangila.ensure4j.Ensure.notNull;
import static io.github.mangila.ensure4j.internal.EnsureUtils.getSupplierOrThrow;
import static io.github.mangila.ensure4j.internal.EnsureUtils.isNull;

public final class EnsureObjects {

    private EnsureObjects() {
    }

    private static class Holder {
        private static final EnsureObjects INSTANCE = new EnsureObjects();
    }

    public static EnsureObjects getInstance() {
        return EnsureObjects.Holder.INSTANCE;
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
    public <T> T isInstanceOf(Class<T> clazz, Object object, Supplier<RuntimeException> supplier) throws RuntimeException {
        if (isNull(clazz) || !clazz.isInstance(object)) {
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
    public <T> T isInstanceOf(Class<T> clazz, Object object, String exceptionMessage) throws EnsureException {
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
    public <T> T isInstanceOf(Class<T> clazz, Object object) throws EnsureException {
        notNull(clazz, "class must not be null");
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
    public <T extends Enum<T>> T isEquals(T enum1, T enum2, Supplier<RuntimeException> runtimeExceptionSupplier) throws EnsureException {
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
    public <T extends Enum<T>> T isEquals(T enum1, T enum2, String exceptionMessage) throws EnsureException {
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
    public <T extends Enum<T>> T isEquals(T enum1, T enum2) throws EnsureException {
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
    public <T> T isEquals(T object, Object otherObject, Supplier<RuntimeException> runtimeExceptionSupplier) throws RuntimeException {
        if (object == otherObject) {
            return object;
        }
        if (isNull(object)) {
            throw getSupplierOrThrow(runtimeExceptionSupplier);
        }
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
    public <T> T isEquals(T object, Object otherObject, String exceptionMessage) throws EnsureException {
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
    public <T> T isEquals(T object, Object otherObject) throws EnsureException {
        return isEquals(object, otherObject, "objects must be equal");
    }

}
