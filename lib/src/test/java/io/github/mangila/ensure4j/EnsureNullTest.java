package io.github.mangila.ensure4j;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EnsureNullTest implements EnsureOpsArchTest<EnsureNullOps> {

  @Override
  public Class<EnsureNullOps> clazz() {
    return EnsureNullOps.class;
  }

  @Override
  public long expectedMethodCount() {
    return 1;
  }

  @Test
  @DisplayName("notNull should return object when not null")
  void notNullShouldReturnObjectWhenNotNull() {
    String value = "test";
    assertThat(Ensure.notNull(value)).isEqualTo(value);
  }

  @Test
  @DisplayName("notNull should throw exception when null")
  void notNullShouldThrowExceptionWhenNull() {
    assertThatThrownBy(() -> Ensure.notNull(null))
        .isInstanceOf(EnsureException.class)
        .hasMessage("object must not be null");
  }

  @Test
  @DisplayName("notNull should throw exception with custom message")
  void notNullShouldThrowExceptionWithCustomMessage() {
    assertThatThrownBy(() -> Ensure.notNull(null, "custom message"))
        .isInstanceOf(EnsureException.class)
        .hasMessage("custom message");
  }

  @Test
  @DisplayName("notNull should throw custom exception")
  void notNullShouldThrowCustomException() {
    assertThatThrownBy(() -> Ensure.notNull(null, () -> new IllegalArgumentException("custom")))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("custom");
  }
}
