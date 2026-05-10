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
  INSTANCE;

  /**
   * Validates that the provided map is not empty. If the map is null or empty, the exception
   * provided by the supplied {@code runtimeExceptionSupplier} is thrown.
   *
   * @param <T> the type of the map
   * @param map the map to be validated
   * @param runtimeExceptionSupplier the runtimeExceptionSupplier that provides the exception to be
   *     thrown if the validation fails
   * @return the map passed as input
   * @throws RuntimeException if the map is null or empty
   */
  public <T extends Map<?, ?>> T notEmpty(
      T map, Supplier<RuntimeException> runtimeExceptionSupplier) throws RuntimeException {
    if (isNull(map) || map.isEmpty()) {
      throw getSupplierOrThrow(runtimeExceptionSupplier);
    }
    return map;
  }

  /**
   * Ensures that the provided map is not empty. If the map is null or empty, throws an
   * EnsureException with the specified exception message.
   *
   * @param <T> the type of the map
   * @param map the map to check for emptiness
   * @param exceptionMessage the message to include in the exception if the map is empty
   * @return the map passed as input
   * @throws EnsureException if the map is null or empty
   */
  public <T extends Map<?, ?>> T notEmpty(T map, String exceptionMessage) throws EnsureException {
    return notEmpty(map, () -> EnsureException.of(exceptionMessage));
  }

  /**
   * Validates that the provided map is not empty. If the map is null or empty, an EnsureException
   * will be thrown with a specified error message.
   *
   * @param <T> the type of the map
   * @param map the map to be checked for emptiness; must not be null or empty
   * @return the map passed as input
   * @throws EnsureException with the message "map must not be empty or null" - if the provided map
   *     is null or empty
   */
  public <T extends Map<?, ?>> T notEmpty(T map) throws EnsureException {
    return notEmpty(map, "map must not be empty or null");
  }
}
