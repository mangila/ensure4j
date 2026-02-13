package io.github.mangila.ensure4j.ops;

import io.github.mangila.ensure4j.EnsureException;

import java.util.function.Supplier;

import static io.github.mangila.ensure4j.internal.EnsureUtils.getSupplierOrThrow;

public enum EnsureNumberOps {
    INSTANCE;

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
    public long max(long boundary, long value, Supplier<RuntimeException> runtimeExceptionSupplier) throws RuntimeException {
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
    public long max(long boundary, long value, String exceptionMessage) throws EnsureException {
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
    public long max(long boundary, long value) throws EnsureException {
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
    public int max(int boundary, int value, Supplier<RuntimeException> runtimeExceptionSupplier) throws RuntimeException {
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
    public int max(int boundary, int value, String exceptionMessage) throws EnsureException {
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
    public int max(int boundary, int value) throws EnsureException {
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
    public long min(long boundary, long value, Supplier<RuntimeException> runtimeExceptionSupplier) throws RuntimeException {
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
    public long min(long boundary, long value, String exceptionMessage) throws EnsureException {
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
    public long min(long boundary, long value) throws EnsureException {
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
    public int min(int boundary, int value, Supplier<RuntimeException> runtimeExceptionSupplier) throws RuntimeException {
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
    public int min(int boundary, int value, String exceptionMessage) throws EnsureException {
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
    public int min(int boundary, int value) throws EnsureException {
        return min(boundary, value, "value must be greater than or equal to %d, but was %d".formatted(boundary, value));
    }

    /**
     * Ensures that the provided value is positive.
     *
     * @param value                    the value to check
     * @param runtimeExceptionSupplier a supplier that provides the exception to be thrown if the value is not positive
     * @return the input value if it is positive
     * @throws RuntimeException if the value is not positive
     */
    public long positive(long value, Supplier<RuntimeException> runtimeExceptionSupplier) throws RuntimeException {
        if (value <= 0) {
            throw getSupplierOrThrow(runtimeExceptionSupplier);
        }
        return value;
    }

    /**
     * Ensures that the provided value is positive.
     *
     * @param value            the value to check
     * @param exceptionMessage the message to include in the EnsureException if the value is not positive
     * @return the input value if it is positive
     * @throws EnsureException if the value is not positive
     */
    public long positive(long value, String exceptionMessage) throws EnsureException {
        return positive(value, () -> EnsureException.of(exceptionMessage));
    }

    /**
     * Ensures that the provided value is positive.
     *
     * @param value the value to check
     * @return the input value if it is positive
     * @throws EnsureException with the message "value must be positive - (%s)" - if the value is not positive
     */
    public long positive(long value) throws EnsureException {
        return positive(value, "value must be positive - (%s)".formatted(value));
    }

    /**
     * Ensures that the provided value is positive.
     *
     * @param value                    the value to check
     * @param runtimeExceptionSupplier a supplier that provides the exception to be thrown if the value is not positive
     * @return the input value if it is positive
     * @throws RuntimeException if the value is not positive
     */
    public int positive(int value, Supplier<RuntimeException> runtimeExceptionSupplier) throws RuntimeException {
        if (value <= 0) {
            throw getSupplierOrThrow(runtimeExceptionSupplier);
        }
        return value;
    }

    /**
     * Ensures that the provided value is positive.
     *
     * @param value            the value to check
     * @param exceptionMessage the message to include in the EnsureException if the value is not positive
     * @return the input value if it is positive
     * @throws EnsureException if the value is not positive
     */
    public int positive(int value, String exceptionMessage) throws EnsureException {
        return positive(value, () -> EnsureException.of(exceptionMessage));
    }

    /**
     * Ensures that the provided value is positive.
     *
     * @param value the value to check
     * @return the input value if it is positive
     * @throws EnsureException with the message "value must be positive" - if the value is not positive
     */
    public int positive(int value) throws EnsureException {
        return positive(value, "value must be positive - (%s)".formatted(value));
    }

    /**
     * Ensures that the provided value is negative.
     *
     * @param value                    the value to check
     * @param runtimeExceptionSupplier a supplier that provides the exception to be thrown if the value is not negative
     * @return the input value if it is negative
     * @throws RuntimeException if the value is not negative
     */
    public long negative(long value, Supplier<RuntimeException> runtimeExceptionSupplier) throws RuntimeException {
        if (value >= 0) {
            throw getSupplierOrThrow(runtimeExceptionSupplier);
        }
        return value;
    }

    /**
     * Ensures that the provided value is negative.
     *
     * @param value            the value to check
     * @param exceptionMessage the message to include in the EnsureException if the value is not negative
     * @return the input value if it is negative
     * @throws EnsureException if the value is not negative
     */
    public long negative(long value, String exceptionMessage) throws EnsureException {
        return negative(value, () -> EnsureException.of(exceptionMessage));
    }

    /**
     * Ensures that the provided value is negative.
     *
     * @param value the value to check
     * @return the input value if it is negative
     * @throws EnsureException with the message "value must be negative" - if the value is not negative
     */
    public long negative(long value) throws EnsureException {
        return negative(value, "value must be negative - (%s)".formatted(value));
    }

    /**
     * Ensures that the provided value is negative.
     *
     * @param value                    the value to check
     * @param runtimeExceptionSupplier a supplier that provides the exception to be thrown if the value is not negative
     * @return the input value if it is negative
     * @throws RuntimeException if the value is not negative
     */
    public int negative(int value, Supplier<RuntimeException> runtimeExceptionSupplier) throws RuntimeException {
        if (value >= 0) {
            throw getSupplierOrThrow(runtimeExceptionSupplier);
        }
        return value;
    }

    /**
     * Ensures that the provided value is negative.
     *
     * @param value            the value to check
     * @param exceptionMessage the message to include in the EnsureException if the value is not negative
     * @return the input value if it is negative
     * @throws EnsureException if the value is not negative
     */
    public int negative(int value, String exceptionMessage) throws EnsureException {
        return negative(value, () -> EnsureException.of(exceptionMessage));
    }

    /**
     * Ensures that the provided value is negative.
     *
     * @param value the value to check
     * @return the input value if it is negative
     * @throws EnsureException with the message "value must be negative" - if the value is not negative
     */
    public int negative(int value) throws EnsureException {
        return negative(value, "value must be negative - (%s)".formatted(value));
    }

}
