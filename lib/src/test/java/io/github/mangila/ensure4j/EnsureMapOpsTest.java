package io.github.mangila.ensure4j;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EnsureMapOpsTest implements EnsureOpsArchTest<EnsureMapOps> {

  @Override
  public Class<EnsureMapOps> clazz() {
    return EnsureMapOps.class;
  }

  @Override
  public long expectedMethodCount() {
    return 1;
  }

  @Test
  @DisplayName("notEmpty should return map when not empty")
  void notEmptyShouldReturnMapWhenNotEmpty() {
    Map<String, String> map = Map.of("key", "value");
    assertThat(Ensure.notEmpty(map)).isEqualTo(map);
  }

  @Test
  @DisplayName("notEmpty should throw exception when empty")
  void notEmptyShouldThrowExceptionWhenEmpty() {
    Map<String, String> map = Map.of();
    assertThatThrownBy(() -> Ensure.notEmpty(map))
        .isInstanceOf(EnsureException.class)
        .hasMessage("map must not be empty");
  }

  @Test
  @DisplayName("notEmpty should throw exception with custom message")
  void notEmptyShouldThrowExceptionWithCustomMessage() {
    Map<String, String> map = Map.of();
    assertThatThrownBy(() -> Ensure.notEmpty(map, "custom message"))
        .isInstanceOf(EnsureException.class)
        .hasMessage("custom message");
  }

  @Test
  @DisplayName("notEmpty should throw custom exception")
  void notEmptyShouldThrowCustomException() {
    Map<String, String> map = Map.of();
    assertThatThrownBy(() -> Ensure.notEmpty(map, () -> new IllegalArgumentException("custom")))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("custom");
  }
}
