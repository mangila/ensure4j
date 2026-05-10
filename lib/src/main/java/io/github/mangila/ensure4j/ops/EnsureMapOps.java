package io.github.mangila.ensure4j.ops;

import static io.github.mangila.ensure4j.internal.EnsureUtils.getSupplierOrThrow;
import static io.github.mangila.ensure4j.internal.EnsureUtils.isNull;

import io.github.mangila.ensure4j.EnsureException;
import java.util.Map;
import java.util.function.Supplier;

/**
 * Provides utility methods for validating and operating on maps. This enum implements singleton
 * behavior, ensuring a single instance is used throughout.
 */
public enum EnsureMapOps {

  /**
   * Access point for the {@code EnsureMapOps} singleton. Use this instance to perform map
   * operations.
   */
  INSTANCE;

  /**
   * Ensures that the provided map is not null or empty.
   *
   * @param <T> the type of the map
   * @param map the map to check
   * @return the provided map if it is not null or empty
   * @throws EnsureException if the map is null or empty, with the message {@code "map must not be
   *     empty or null"}
   * @see #notEmpty(Map, String)
   * @see #notEmpty(Map, Supplier)
   */
  public <T extends Map<?, ?>> T notEmpty(T map) throws EnsureException {
    return notEmpty(map, "map must not be empty or null");
  }

  /**
   * Ensures that the provided map is not null or empty.
   *
   * @param <T> the type of the map
   * @param map the map to check
   * @param exceptionMessage the message to include in the exception if validation fails
   * @return the provided map if it is not null or empty
   * @throws EnsureException if the map is null or empty, with the provided message
   * @see #notEmpty(Map)
   * @see #notEmpty(Map, Supplier)
   */
  public <T extends Map<?, ?>> T notEmpty(T map, String exceptionMessage) throws EnsureException {
    return notEmpty(map, () -> EnsureException.of(exceptionMessage));
  }

  /**
   * Ensures that the provided map is not null or empty.
   *
   * @param <T> the type of the map
   * @param map the map to check
   * @param exceptionSupplier the supplier that provides the exception to be thrown if validation
   *     fails
   * @return the provided map if it is not null or empty
   * @throws RuntimeException if the map is null or empty; the thrown exception is provided by
   *     {@code exceptionSupplier}
   * @see #notEmpty(Map)
   * @see #notEmpty(Map, String)
   */
  public <T extends Map<?, ?>> T notEmpty(
      T map, Supplier<? extends RuntimeException> exceptionSupplier) throws RuntimeException {
    if (isNull(map) || map.isEmpty()) {
      throw getSupplierOrThrow(exceptionSupplier);
    }
    return map;
  }
}
