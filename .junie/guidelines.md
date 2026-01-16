# Development Guidelines for Ensure4j

## Build/Configuration Instructions

### Prerequisites
- **Java 21**: The project is compiled with Java 21 and is compatible with Java 17+.
- **Maven**: Use the provided Maven wrapper (`./mvnw`).

### Build Commands
- **Clean and Build**: `./mvnw clean install`
- **Build without tests**: `./mvnw clean install -DskipTests`

### Git Hooks
The project uses `pre-commit` to maintain code quality.
1. Create a virtual environment: `python -m venv venv`
2. Activate it: `source venv/bin/activate` (or appropriate for your OS)
3. Install requirements: `pip install -r requirements.txt`
4. Install hooks: `pre-commit install`

## Testing Information

### Frameworks
- **JUnit 5**: Standard testing framework.
- **AssertJ**: Used for fluent assertions.
- **ArchUnit**: Used to enforce architectural constraints (e.g., verifying method counts and visibility in `Ensure.java`).

### Running Tests
- **All tests**: `./mvnw test`
- **Specific module**: `./mvnw test -pl lib`
- **Specific test class**: `./mvnw test -Dtest=EnsureTest`

### Guidelines for New Tests
- Use descriptive `@DisplayName` where it adds clarity.
- For negative cases, use `assertThatThrownBy` or `assertThatCode(...).throwsException()`.
- If adding new public methods to `Ensure.java`, update `EnsureTest.archTest()` to reflect the new method count and signature checks.
- Test files are organized by data type or feature (e.g., `StringTest.java`, `CollectionTest.java`).

### Simple Test Example
```java
package io.github.mangila.ensure4j;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MyNewTest {
    @Test
    void shouldPassWhenNotNull() {
        String value = "Hello";
        Ensure.notNull(value);
        assertThat(value).isEqualTo("Hello");
    }

    @Test
    void shouldThrowExceptionWhenNull() {
        assertThatThrownBy(() -> Ensure.notNull(null))
                .isInstanceOf(EnsureException.class)
                .hasMessage("object must not be null");
    }
}
```

## Additional Development Information

### Code Style & Philosophy
- **Zero Dependencies**: The `lib` module must remain free of external production dependencies (except for `jspecify` for annotations).
- **Utility Class Pattern**: `Ensure.java` is a utility class with a private constructor that throws `IllegalStateException("Utility class")`.
- **Method Consistency**: Most methods in `Ensure` come in three variants:
  1. `method(value)`: Uses default `EnsureException` and default message.
  2. `method(value, String message)`: Uses default `EnsureException` with custom message.
  3. `method(value, Supplier<RuntimeException> supplier)`: Uses a custom exception provided by the supplier.
- **Fluent API**: Methods should return the validated value whenever possible to support fluent usage and stream pipelines.
- **Javadoc**: All public methods should have clear Javadoc.

### Performance
Avoid heavy operations inside `Ensure` methods as they are often used in performance-critical paths.

### Examples
New features should ideally be accompanied by an example in the `examples` module.
