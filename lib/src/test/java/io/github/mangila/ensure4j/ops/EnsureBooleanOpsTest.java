package io.github.mangila.ensure4j.ops;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import io.github.mangila.ensure4j.EnsureException;
import org.junit.jupiter.api.Test;

class EnsureBooleanOpsTest {

  private final EnsureBooleanOps ops = EnsureBooleanOps.INSTANCE;

  @Test
  void isTrueSuccess() {
    assertThatCode(
            () -> {
              ops.isTrue(true);
              ops.isTrue(true, "message");
              ops.isTrue(true, () -> new RuntimeException("custom"));
            })
        .doesNotThrowAnyException();
  }

  @Test
  void isTrueThrow() {
    assertThatThrownBy(() -> ops.isTrue(false))
        .isInstanceOf(EnsureException.class)
        .hasMessage("boolean must be true");
    assertThatThrownBy(() -> ops.isTrue(false, "message"))
        .isInstanceOf(EnsureException.class)
        .hasMessage("message");
    assertThatThrownBy(() -> ops.isTrue(false, () -> new RuntimeException("message")))
        .isInstanceOf(RuntimeException.class)
        .hasMessage("message");
  }

  @Test
  void isFalseSuccess() {
    assertThatCode(
            () -> {
              ops.isFalse(false);
              ops.isFalse(false, "message");
              ops.isFalse(false, () -> new RuntimeException("custom"));
            })
        .doesNotThrowAnyException();
  }

  @Test
  void isFalseThrow() {
    assertThatThrownBy(() -> ops.isFalse(true))
        .isInstanceOf(EnsureException.class)
        .hasMessage("boolean must be false");
    assertThatThrownBy(() -> ops.isFalse(true, "message"))
        .isInstanceOf(EnsureException.class)
        .hasMessage("message");
    assertThatThrownBy(() -> ops.isFalse(true, () -> new RuntimeException("message")))
        .isInstanceOf(RuntimeException.class)
        .hasMessage("message");
  }
}
