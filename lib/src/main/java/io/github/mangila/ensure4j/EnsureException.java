package io.github.mangila.ensure4j;

import java.io.Serial;

/** Exception thrown when a condition checked by Ensure methods is not met. */
public class EnsureException extends RuntimeException {

  @Serial private static final long serialVersionUID = 1L;

  /**
   * Constructs a new EnsureException with the specified detail message.
   *
   * @param message the detail message.
   */
  public EnsureException(String message) {
    super(message);
  }

  /**
   * Creates a new EnsureException with the specified detail message.
   *
   * @param message the detail message.
   * @return a new EnsureException.
   */
  public static EnsureException from(String message) {
    return new EnsureException(message);
  }
}
