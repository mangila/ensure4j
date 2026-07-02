package io.github.mangila.ensure4j;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EnsureBooleanOpsTest implements EnsureOpsArchTest<EnsureBooleanOps> {

  @Override
  public Class<EnsureBooleanOps> clazz() {
    return EnsureBooleanOps.class;
  }

  @Override
  public long expectedMethodCount() {
    return 2;
  }

  @Test
  @DisplayName("isFalse should not throw when false")
  void isFalseShouldNotThrowWhenFalse() {
    assertThatCode(() -> Ensure.isFalse(false)).doesNotThrowAnyException();
  }

  @Test
  @DisplayName("isFalse should throw custom exception")
  void isFalseShouldThrowCustomException() {
    assertThatThrownBy(() -> Ensure.isFalse(true, () -> new IllegalArgumentException("custom")))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("custom");
  }

  @Test
  @DisplayName("isFalse should throw exception when true")
  void isFalseShouldThrowExceptionWhenTrue() {
    assertThatThrownBy(() -> Ensure.isFalse(true))
        .isInstanceOf(EnsureException.class)
        .hasMessage("boolean must be false");
  }

  @Test
  @DisplayName("isFalse should throw exception with custom message")
  void isFalseShouldThrowExceptionWithCustomMessage() {
    assertThatThrownBy(() -> Ensure.isFalse(true, "custom message"))
        .isInstanceOf(EnsureException.class)
        .hasMessage("custom message");
  }

  @Test
  @DisplayName("isTrue should not throw when true")
  void isTrueShouldNotThrowWhenTrue() {
    assertThatCode(() -> Ensure.isTrue(true)).doesNotThrowAnyException();
  }

  @Test
  @DisplayName("isTrue should throw custom exception")
  void isTrueShouldThrowCustomException() {
    assertThatThrownBy(() -> Ensure.isTrue(false, () -> new IllegalArgumentException("custom")))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("custom");
  }

  @Test
  @DisplayName("isTrue should throw exception when false")
  void isTrueShouldThrowExceptionWhenFalse() {
    assertThatThrownBy(() -> Ensure.isTrue(false))
        .isInstanceOf(EnsureException.class)
        .hasMessage("boolean must be true");
  }

  @Test
  @DisplayName("isTrue should throw exception with custom message")
  void isTrueShouldThrowExceptionWithCustomMessage() {
    assertThatThrownBy(() -> Ensure.isTrue(false, "custom message"))
        .isInstanceOf(EnsureException.class)
        .hasMessage("custom message");
  }
}
