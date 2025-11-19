package io.github.mangila.ensure4j;

/**
 * Represents an exception that can be used for validation or ensuring specific conditions are met
 * during runtime. This exception is unchecked and extends {@code RuntimeException}.
 * <br>
 * The {@code EnsureException} provides a constructor to define a custom exception message and a
 * static factory method for creating new instances using a provided message.
 */
public class EnsureException extends RuntimeException {

    /**
     * Constructs a new {@code EnsureException} with the specified detail message.
     *
     * @param message the detail message that provides information about the exception.
     */
    public EnsureException(String message) {
        super(message);
    }

    /**
     * Creates a new instance of {@code EnsureException} with the specified message.
     *
     * @param message the detail message that describes the reason for the exception
     * @return a new {@code EnsureException} instance encapsulating the provided message
     */
    public static EnsureException of(String message) {
        return new EnsureException(message);
    }
}