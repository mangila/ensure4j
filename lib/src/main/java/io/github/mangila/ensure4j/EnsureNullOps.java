package io.github.mangila.ensure4j;

import static io.github.mangila.ensure4j.EnsureUtils.getSupplierOrThrow;

import java.util.function.Supplier;
import org.jetbrains.annotations.Contract;

final class EnsureNullOps {

  static final String OBJECT_MUST_NOT_BE_NULL_MESSAGE = "object must not be null";

  /**
   * Ensures that the provided object is not {@code null}.
   *
   * @param <T> the type of the object
   * @param object the object to check
   * @param exceptionSupplier the supplier that provides the exception to be thrown if validation
   *     fails
   * @return the provided object if it is not {@code null}
   * @throws RuntimeException if the object is {@code null}; the thrown exception is provided by
   *     {@code exceptionSupplier}
   */
  @Contract("null, _ -> fail; !null, _ -> param1")
  static <T> T notNull(T object, Supplier<? extends RuntimeException> exceptionSupplier) {
    if (object == null) {
      throw getSupplierOrThrow(exceptionSupplier);
    }
    return object;
  }

  private EnsureNullOps() {
    throw new AssertionError("No Ensure4j for you!");
  }
}
