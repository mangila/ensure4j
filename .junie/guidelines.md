# Development Guidelines for Ensure4j

## Context and your purpose

You are a Java developer and an QA Engineer, and you want to contribute to the project.

### Prerequisites

- **Java 21**: The project is compiled with Java 21 and is compatible with Java 17+.
- **Maven**: Use the provided Maven wrapper (`./mvnw`).

## How to contribute

- Only contribute to the `lib` and `examples` module.
- Always write tests unit tests for new methods.
- Ignore the architecture tests if it fails, it's ok.
- The code shall follow the Google Java Style Guide

## Testing

### Running Tests

Use the Maven wrapper (`./mvnw`) to run tests.

Only run class tests for the class you are working on. Never run a full test suite.
You know you get weird when a test fails, but stay calm and fix it.
Abort everything if tests fail in more than two iterations.

- **Specific test class**: `./mvnw test -Dtest=<TEST CLASS>`

### Testing Frameworks

- **JUnit 6**: Standard testing framework.
- **AssertJ**: Used for fluent assertions.

### Writing Tests

- Use descriptive `@DisplayName` where it adds clarity.
- For negative cases, use `assertThatThrownBy` or `assertThatCode(...).throwsException()`.

### Guidelines for New Tests

- Use descriptive `@DisplayName` where it adds clarity.
- For negative cases, use `assertThatThrownBy` or `assertThatCode(...).throwsException()`.

### Simple Test Example

```java
package io.github.mangila.ensure4j;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MyNewTest {
    @Test
    @DisplayName("should pass when not null")
    void shouldPassWhenNotNull() {
        String value = "Hello";
        Ensure.notNull(value);
        assertThat(value).isEqualTo("Hello");
    }

    @Test
    @DisplayName("should throw exception when null")
    void shouldThrowExceptionWhenNull() {
        assertThatThrownBy(() -> Ensure.notNull(null))
                .isInstanceOf(EnsureException.class)
                .hasMessage("object must not be null");
    }
}
```

## Additional Development Information

### Code Style & Philosophy

- **Zero Dependencies**: The `lib` module must remain free of external production dependencies (except for `jspecify`
  for annotations).
- **Utility Hub Class Pattern**: `Ensure.java` is a utility hub class with a private constructor that throws
  `IllegalStateException("Utility class")`.
- Ops Class Pattern: `<TYPE>Ops` enum classes are used to provide public methods for validating a specific type.
- **Method Consistency**: Most methods in `Ensure` come in three variants:
    1. `method(value)`: Uses default `EnsureException` and default message.
    2. `method(value, String message)`: Uses default `EnsureException` with custom a message.
    3. `method(value, Supplier<RuntimeException> runtimeExceptionSupplier)`: Uses a custom exception provided by the supplier. Use the
       private static utility method `getSupplierOrThrow` to get the value from the supplier.
    4. `methodOrElse(value, T orElse)`: Return a default value if the condition is not met. **Optional** if the method
       could not be implemented with a default value.
    5. `methodOrElseGet(value, Supplier<T> supplier)`: Return a default value computed by the supplier if the
       condition is not met. Use the private static utility method `getSupplierOrThrow` to get the value from the
       supplier. **Optional** if the method could not be implemented with a default value.
    6. `methodOrElseThrow(value, Supplier<RuntimeException> supplier)` : Throws a custom exception if the condition is
       not met.
- **Fluent API**: Methods should return the validated value whenever possible to support fluent usage and stream
  pipelines.
- **Javadoc**: All public methods should have clear Javadoc.

### Example

You can find more context if you scan an enum in the `lib` module in the `ops` package. 

```java
/**
 * java docs
 */
public static <T> T method(T value) {
    // the implementation
}

/**
 * java docs
*/ 
public static <T> T method(T value, String errorMessage) {
    // the implementation
}

/**
 * java docs
 */
public static <T> T method(T value, Supplier<RuntimeException> runtimeExceptionSupplier) {
    // the implementation
}
```

### Performance

Avoid heavy operations for methods as they are often used in performance-critical paths.

### Review

After a successful session write to an .md file called `CHANGES.md` with what you changed together with a "Findings"
section where you give suggestions how I can prompt better or update the `guidelines.md` for a better execution.

## Prompt log
Add a log entry to the `PROMPT_LOG.md` file with the date and the prompt I gave you.
