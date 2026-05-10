# Development Guidelines for Ensure4j

## Context and your purpose

You are a Java developer and an QA Engineer, and you want to contribute to the project.

### Prerequisites

- **Java 25**: The project is compiled with Java 25 and is compatible with Java 17+.
- **Maven**: Use the installed Mise Maven installation.

## How to contribute

- Only contribute to the `lib` and `examples` module.
- Always write tests unit tests for new methods.
- Ignore the architecture tests if it fails, it's ok.
- The code shall follow the Google Java Style Guide

## Coding Guidelines and Javadocs

- Make sure formatting is correct by runnig `mvn -f lib/pom.xml com.spotify.fmt:fmt-maven-plugin:format`
- Make sure the methods are implemented for the overloaded methods following the Step-down Rule (or the Newspaper Metaphor).

Create methods with descriptive names and javadocs in the following format:
```java
/**
 * Ensures that the provided array is not null or empty.
 *
 * @param <T> the component type of the array
 * @param array the array to check
 * @return the provided array if it is not null or empty
 * @throws EnsureException if the array is null or empty, with the message {@code "array must not
 *     be empty"}
 * @see #notEmpty(Object[], String)
 * @see #notEmpty(Object[], Supplier)
 */
public <T> T[] notEmpty(T[] array) throws EnsureException {
    return notEmpty(array, "array must not be empty");
}

/**
 * Ensures that the provided array is not null or empty.
 *
 * @param <T> the component type of the array
 * @param array the array to check
 * @param exceptionMessage the message to include in the exception if validation fails
 * @return the provided array if it is not null or empty
 * @throws EnsureException if the array is null or empty, with the provided message
 * @see #notEmpty(Object[])
 * @see #notEmpty(Object[], Supplier)
 */
public <T> T[] notEmpty(T[] array, String exceptionMessage) throws EnsureException {
    return notEmpty(array, () -> EnsureException.of(exceptionMessage));
}

/**
 * Ensures that the provided array is not null or empty.
 *
 * @param <T> the component type of the array
 * @param array the array to check
 * @param exceptionSupplier the supplier that provides the exception to be thrown if validation
 *     fails
 * @return the provided array if it is not null or empty
 * @throws RuntimeException if the array is null or empty; the thrown exception is provided by
 *     {@code exceptionSupplier}
 * @see #notEmpty(Object[])
 * @see #notEmpty(Object[], String)
 */
public <T> T[] notEmpty(T[] array, Supplier<? extends RuntimeException> exceptionSupplier) {
    if (isNull(array) || array.length == 0) {
        throw getSupplierOrThrow(exceptionSupplier);
    }
    return array;
}
```

## Testing

### Running Tests

Only run class tests for the class you are working on. Never run a full test suite.
Abort everything if tests fail in more than two iterations.

### Testing Frameworks

- **JUnit 5 or JUnit6 (when you know how to use it)**: Standard testing framework.
- **AssertJ**: Used for fluent assertions.

### Writing Tests

- Use descriptive `@DisplayName` where it adds clarity.
- For negative cases, use `assertThatThrownBy` or `assertThatCode(...).throwsException()`.

### Guidelines for New Tests

- Use descriptive `@DisplayName` where it adds clarity.
- For negative cases, use `assertThatThrownBy` or `assertThatCode(...).throwsException()`.

### Performance

Avoid heavy operations for methods as they are often used in performance-critical paths.

### Review

After a successful session write to an .md file called `CHANGES.md` with what you changed together with a "Findings"
section where you give suggestions how I can prompt better or update the `guidelines.md` for a better execution.
