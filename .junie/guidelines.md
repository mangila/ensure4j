# Development Guidelines for Ensure4j

## Context and your purpose

You are a Java developer and an QA Engineer, and you want to contribute to the project.

### Prerequisites

- **Java**: The project is compiled with Java 17 and developed with Java 25 for outermost compatibility.
- **Maven**: Use the installed Mise Maven installation.

## How to contribute

- Only contribute to the `./lib` directory.
- Always write unit tests for new methods.
- The code shall follow the Google Java Style Guide

## Coding Guidelines and Javadocs

Ensure.java is the public API methods that are used by the users.

The **Ops classes** are the classes that are used to implement the methods and are not meant to be used directly.

Make sure the formatting is correct by running the following command: "mvn com.spotify.fmt:fmt-maven-plugin:format"

- Make sure the methods are implemented for the overloaded methods following the Step-down Rule (or the Newspaper
  Metaphor).
- Create methods with descriptive names
- Use contracts for methods with the annotation `@org.jetbrains.annotations.Contract;`
- Use Javadocs for all public methods

Follow the example below in implementation for public methods in Ensure.java:

```java
import java.util.function.Supplier;
import org.jetbrains.annotations.Contract;

// ... existing code ...

/**
 * Ensures that the provided string ends with the specified suffix.
 *
 * @param string the string to check
 * @param suffix the required suffix
 * @return the provided string if it ends with the suffix
 * @throws EnsureException if the string is {@code null} or does not end with the suffix, with the
 *     message {@code "string must end with %s"}
 * @see #endsWith(String, String, String)
 * @see #endsWith(String, String, Supplier)
 */
@Contract("null, _ -> fail; !null, _ -> param1")
public static String endsWith(String string, String suffix) {
  return Ensure.endsWith(
          string, suffix, EnsureStringOps.STRING_MUST_END_WITH_FORMAT.formatted(suffix));
}

/**
 * Ensures that the provided string ends with the specified suffix.
 *
 * @param string the string to check
 * @param suffix the required suffix
 * @param exceptionMessage the message to include in the exception if validation fails
 * @return the provided string if it ends with the suffix
 * @throws EnsureException if the string is {@code null} or does not end with the suffix, with the
 *     provided message
 * @see #endsWith(String, String)
 * @see #endsWith(String, String, Supplier)
 */
@Contract("null, _, _ -> fail; !null, _, _ -> param1")
public static String endsWith(String string, String suffix, String exceptionMessage) {
  return Ensure.endsWith(string, suffix, () -> EnsureException.from(exceptionMessage));
}

/**
 * Ensures that the provided string ends with the specified suffix.
 *
 * @param string the string to check
 * @param suffix the required suffix
 * @param exceptionSupplier the supplier that provides the exception to be thrown if validation
 *     fails
 * @return the provided string if it ends with the suffix
 * @throws RuntimeException if the string is {@code null} or does not end with the suffix; the
 *     thrown exception is provided by {@code exceptionSupplier}
 * @see #endsWith(String, String)
 * @see #endsWith(String, String, String)
 */
@Contract("null, _, _ -> fail; !null, _, _ -> param1")
public static String endsWith(
        String string, String suffix, Supplier<? extends RuntimeException> exceptionSupplier) {
  return EnsureStringOps.endsWith(string, suffix, exceptionSupplier);
}

// ... existing code ...
```

And for the methods in the Ops classes:

```java
import java.util.function.Supplier;

import org.jetbrains.annotations.Contract;

// ... existing code ...

static final String SUFFIX_MUST_NOT_BE_NULL_MESSAGE = "suffix must not be null";

/**
 * Ensures that the provided string ends with the specified suffix.
 *
 * @param string the string to check
 * @param suffix the suffix to check for
 * @param exceptionSupplier the supplier that provides the exception to be thrown if validation
 *     fails
 * @return the provided string if it ends with the suffix
 * @throws RuntimeException if the string does not end with the suffix; the thrown exception is
 *     provided by {@code exceptionSupplier}
 */
@Contract("null, _, _ -> fail; _, null, _ -> fail; !null, !null, _ -> param1")
static String endsWith(
        String string, String suffix, Supplier<? extends RuntimeException> exceptionSupplier) {
  if (suffix == null) {
    throw EnsureException.from(SUFFIX_MUST_NOT_BE_NULL_MESSAGE);
  }
  if (!EnsureUtils.hasSuffix(string, suffix)) {
    throw getSupplierOrThrow(exceptionSupplier);
  }
  return string;
}

// ... existing code ...
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
- Get the full structure of all the classes and test classes to get a full understanding of the code. To know which Ensure.java methods are tested, to the right Ops test class.
- Only write tests for the Ensure.java methods, to the delegated methods in the Ops test classes.

### Performance

Avoid heavy operations for methods as they are often used in performance-critical paths.

### Review

After a successful session write to an .md file called `CHANGES.md` with what you changed together with a "Findings"
section where you give suggestions how I can prompt better or update the `guidelines.md` for a better execution.
