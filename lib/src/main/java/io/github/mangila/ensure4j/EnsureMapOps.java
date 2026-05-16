package io.github.mangila.ensure4j;

import static io.github.mangila.ensure4j.EnsureUtils.getSupplierOrThrow;

import java.util.Map;
import java.util.function.Supplier;
import org.jetbrains.annotations.Contract;

final class EnsureMapOps {

  static final String MAP_MUST_NOT_BE_EMPTY_MESSAGE = "map must not be empty";

  private EnsureMapOps() {
    throw new AssertionError("No Ensure4j for you!");
  }

  /**
   * Ensures that the provided map is not {@code null} or empty.
   *
   * @param <T> the type of the map
   * @param map the map to check
   * @param exceptionSupplier the supplier that provides the exception to be thrown if validation
   *     fails
   * @return the provided map if it is not {@code null} or empty
   * @throws RuntimeException if the map is {@code null} or empty; the thrown exception is provided
   *     by {@code exceptionSupplier}
   */
  @Contract("null, _ -> fail; !null, _ -> param1")
  static <T extends Map<?, ?>> T notEmpty(
      T map, Supplier<? extends RuntimeException> exceptionSupplier) {
    if (EnsureUtils.isEmpty(map)) {
      throw getSupplierOrThrow(exceptionSupplier);
    }
    return map;
  }
}
