package io.github.mangila.ensure4j.ops;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import io.github.mangila.ensure4j.EnsureException;
import org.junit.jupiter.api.Test;

class EnsureBooleanOpsTest implements EnsureOpsTest<EnsureBooleanOps> {

  @Override
  public Class<EnsureBooleanOps> clazz() {
    return EnsureBooleanOps.class;
  }

  @Override
  public EnsureBooleanOps instance() {
    return EnsureBooleanOps.INSTANCE;
  }

  @Override
  public long expectedPublicMethodCount() {
    return 8;
  }

  @Test
  void isTrueSuccess() {
    assertThatCode(
            () -> {
              instance().isTrue(true);
              instance().isTrue(true, "message");
              instance().isTrue(true, () -> new RuntimeException("custom"));
            })
        .doesNotThrowAnyException();
  }

  @Test
  void isTrueThrow() {
    assertThatThrownBy(() -> instance().isTrue(false))
        .isInstanceOf(EnsureException.class)
        .hasMessage("boolean must be true");
    assertThatThrownBy(() -> instance().isTrue(false, "message"))
        .isInstanceOf(EnsureException.class)
        .hasMessage("message");
    assertThatThrownBy(() -> instance().isTrue(false, () -> new RuntimeException("message")))
        .isInstanceOf(RuntimeException.class)
        .hasMessage("message");
  }

  @Test
  void isFalseSuccess() {
    assertThatCode(
            () -> {
              instance().isFalse(false);
              instance().isFalse(false, "message");
              instance().isFalse(false, () -> new RuntimeException("custom"));
            })
        .doesNotThrowAnyException();
  }

  @Test
  void isFalseThrow() {
    assertThatThrownBy(() -> instance().isFalse(true))
        .isInstanceOf(EnsureException.class)
        .hasMessage("boolean must be false");
    assertThatThrownBy(() -> instance().isFalse(true, "message"))
        .isInstanceOf(EnsureException.class)
        .hasMessage("message");
    assertThatThrownBy(() -> instance().isFalse(true, () -> new RuntimeException("message")))
        .isInstanceOf(RuntimeException.class)
        .hasMessage("message");
  }
}
