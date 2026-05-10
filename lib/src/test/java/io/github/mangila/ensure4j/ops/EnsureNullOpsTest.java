package io.github.mangila.ensure4j.ops;

import static org.assertj.core.api.Assertions.*;

import io.github.mangila.ensure4j.EnsureException;
import org.junit.jupiter.api.Test;

class EnsureNullOpsTest {

  private final EnsureNullOps ops = EnsureNullOps.INSTANCE;

  @Test
  void notNullSuccess() {
    assertThatCode(
            () -> {
              var obj = new Object();
              ops.notNull(obj);
              ops.notNull(obj, "message");
              ops.notNull(obj, () -> new RuntimeException("custom"));
            })
        .doesNotThrowAnyException();
  }

  @Test
  void notNullFailure() {
    assertThatThrownBy(() -> ops.notNull(null))
        .isInstanceOf(EnsureException.class)
        .hasMessage("object must not be null");
    assertThatThrownBy(() -> ops.notNull(null, "message"))
        .isInstanceOf(EnsureException.class)
        .hasMessage("message");
    assertThatThrownBy(() -> ops.notNull(null, () -> new RuntimeException("message")))
        .isInstanceOf(RuntimeException.class)
        .hasMessage("message");
  }

  @Test
  void notNullOrElse() {
    var str = "test";
    var fallBack = "fallback";
    assertThat(ops.notNullOrElse(str, fallBack)).isEqualTo(str);
    assertThat(ops.notNullOrElse(null, fallBack)).isEqualTo(fallBack);
  }

  @Test
  void notNullOrElseGet() {
    var str = "test";
    var fallBack = "fallback";
    assertThat(ops.notNullOrElseGet(str, () -> fallBack)).isEqualTo(str);
    assertThat(ops.notNullOrElseGet(null, () -> fallBack)).isEqualTo(fallBack);
  }

  // TODO: remove
  @Test
  void notNullOrElseThrow() {
    var obj = new Object();
    assertThat(ops.notNullOrElseThrow(obj)).isEqualTo(obj);
    assertThatThrownBy(() -> ops.notNullOrElseThrow(null))
        .isInstanceOf(EnsureException.class)
        .hasMessage("object must not be null");
    assertThatThrownBy(() -> ops.notNullOrElseThrow(null, () -> new RuntimeException("message")))
        .isInstanceOf(RuntimeException.class)
        .hasMessage("message");
  }
}
