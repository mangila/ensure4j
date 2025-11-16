# ensure4j

Ensure4j is an implementation of the Preconditions pattern in Java.

## Get started

To get Ensure4j, you have two options:

- Add a dependency to Ensure4j to your project.

- Build Ensure4j yourself.

And, if you want, you can also run the Ensure4j examples.

If you’re building your project using Maven, you can add the following dependency to the `pom.xml`:

```xml

<dependency>
    <groupId>com.github.mangila</groupId>
    <artifactId>ensure4j</artifactId>
    <version>0.0.1</version>
</dependency>
```

If your project is built using another build tool that uses the Maven Central repository, translate this dependency into
the format used by your build tool.

## What is Preconditions?

Preconditions are a design pattern that helps prevent invalid method calls by ensuring that the method arguments meet
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

You most likely already use Preconditions in your code. If you have heard about _Look Before You Leap_ (LBYL), it's the same
mechanism, but preconditions are leaning more towards a contract-based approach.

It's a great and simple way to ensure that your code is in a predictable state.

Here's a basic example of what usage of preconditions can look like in the wild

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

Here's how the same code looks like with Ensure4j

```java
public void placeOrder(Order order) {
    Ensure.notNull(order, "Order cannot be null");
    Ensure.min(1, order.getAmount(), "Order amount must be greater than zero");
    // do business logic
}
```

We have fewer if statements (less cognitive load) and more readable code and also re-usable pre-conditions checks throughout the whole
codebase.

There is some library support for Preconditions already in Java, but they are not as comprehensive and direct for
preconditions checks as Ensure4j.

Libs with the same functionality:

- Guava's Preconditions
- Java's builtin Objects
- Spring's Assert class.

Using a precondition library is a great way to save time. However, with a library there are always drawbacks like a performance penalty that might waste some CPU cycles, 
But this library is very lightweight and has no dependencies.

And of course, you can always roll your own, and I have done that in the past, that's why I created Ensure4j.

## Example usage

See the [examples](examples) project

## Changelog

See [CHANGELOG.md](CHANGELOG.md)
