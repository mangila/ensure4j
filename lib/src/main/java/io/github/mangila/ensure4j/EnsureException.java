package io.github.mangila.ensure4j;

import java.io.Serial;

/** Exception thrown by Ensure methods. */
public class EnsureException extends RuntimeException {

  @Serial private static final long serialVersionUID = 1L;

  public EnsureException(String message) {
    super(message);
  }

  public static EnsureException from(String message) {
    return new EnsureException(message);
  }
}
