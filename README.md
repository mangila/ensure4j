# ensure4j

Ensure4j is a direct implementation of the precondition pattern in Java. It's a lightweight library with no external
dependencies.

[![CircleCI](https://dl.circleci.com/status-badge/img/gh/mangila/ensure4j/tree/main.svg?style=shield)](https://dl.circleci.com/status-badge/redirect/gh/mangila/ensure4j/tree/main)
![GitHub Release](https://img.shields.io/github/v/release/mangila/ensure4j)

There is also support for functional programming pipelines and Java Streams.

**NOTE: This is an initial development cycle. Anything MAY change at any time. The public API SHOULD NOT be considered
stable. Yet...**

The Jacoco coverage report is deployed as the GitHub pages.
- https://mangila.github.io/ensure4j/

## Get started

To get **Ensure4j**, you have two options:

- Add a dependency to Ensure4j to your project.

- Build Ensure4j yourself.

And, if you want, you can also run the Ensure4j examples.

If you’re building your project using Maven, you can add the following dependency to the `pom.xml`:

```xml

<dependency>
    <groupId>io.github.mangila</groupId>
    <artifactId>ensure4j</artifactId>
    <version>0.0.7</version>
</dependency>
```

If your project is built using another build tool that uses the Maven Central repository, translate this dependency into
the format used by your build tool.

Check out the artifact
in [Central-Sonatype Overview](https://central.sonatype.com/artifact/io.github.mangila/ensure4j/overview)

## What is Preconditions?

Preconditions is a design pattern that helps prevent invalid method calls by ensuring that the method arguments meet
certain criteria.

## Why use Preconditions?

- **Robust and Predictable**: It forces a method to define the minimum acceptable state of the data and environment
  before it runs. This prevents the method from executing with invalid inputs, leading to fewer runtime errors and a
  more predictable system state.

- **Easier to Debug**: By failing fast and throwing an exception immediately at the entry point of a method (a bug in
  the caller), the root cause of the problem is localized instantly. This saves time tracing errors through complex
  internal logic.

- **Clearer API/Interface**: It acts as living documentation. When a developer sees the preconditions (often implemented
  by Guard Clauses), they immediately know exactly what is required to call the method successfully, reducing guesswork
  and misuse.

- **Simplified Core Logic**: By putting validation checks at the start, the primary business logic that follows can be
  written without continually checking for nulls or invalid values. This makes the core logic flatter and cleaner (
  lowering the indentation level).

You most likely already use preconditions in your code. If you have heard about _Look Before You Leap_ (LBYL), it's the
same
mechanism, but preconditions are leaning more towards a contract-based approach.

It's a great and simple way to ensure that your code is in a predictable state.

Here's a basic example of what usage of preconditions can look like in the wild:

```java

public void placeOrder(Order order) {
    if (order == null) {
        throw new IllegalArgumentException("Order cannot be null");
    }
    if (order.getAmount() < 0) {
        throw new IllegalArgumentException("Order amount must be greater than zero");
    }
    // do business logic
}
```

Here's what the same code looks like with Ensure4j:

```java
public void placeOrder(Order order) {
    Ensure.notNull(order, "Order cannot be null");
    Ensure.min(1, order.getAmount(), "Order amount must be greater than zero");
    // do business logic
}
```

With the exception that Ensure4j throws an `EnsureException` instead of an `IllegalArgumentException`.

Ensure4j has supplier functions that can be used to provide a custom exception suited for the application or use case
need.

```java
public void placeOrder(Order order) {
    Ensure.notNull(order, () -> new OrderException("Order cannot be null"));
    Ensure.min(1, order.getAmount(), () -> new OrderException("Order amount must be greater than zero"));
    // do business logic
}
```

Predefined exception messages are builtin in the library if you don't want to provide a custom one.

**NOTE: Use well-defined exception messages. They should be self-explanatory and provide enough context to help
understand the problem. The default ones might not be enough for your use case.**

```java
public void placeOrder(Order order) {
    Ensure.notNull(order); // will throw an EnsureException with the message "object must not be null"
    Ensure.min(1, order.getAmount()); // will throw an EnsureException with the message "value must be greater than or equal to %d, but was %d"
}
```

We have fewer if statements (less cognitive load) and more readable code and also reusable preconditions checks
throughout the whole codebase.

There is some library support for preconditions already in Java, but they are not as comprehensive and direct as
Ensure4j. Since this is a precondition only library.

Libs with preconditions support:

- Guava's Preconditions
- Java's builtin Objects
- Spring's Assert class.

Using a precondition library is a great way to save time. However, with a library there are always drawbacks like a
performance penalty that might waste some CPU cycles.

And of course, you can always roll your own, and I have done that in the past, that's why I created Ensure4j.

## Example usage

See the [examples](examples) project
