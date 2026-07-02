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
    return 3;
  }

  @Test
  @DisplayName("containsKey should return map when key exists")
  void containsKeyShouldReturnMapWhenKeyExists() {
    Map<String, String> map = Map.of("key", "value");
    assertThat(Ensure.containsKey(map, "key")).isEqualTo(map);
  }

  @Test
  @DisplayName("containsKey should throw custom exception")
  void containsKeyShouldThrowCustomException() {
    Map<String, String> map = Map.of("key", "value");
    assertThatThrownBy(
            () -> Ensure.containsKey(map, "other", () -> new IllegalArgumentException("custom")))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("custom");
  }

  @Test
  @DisplayName("containsKey should throw exception when key does not exist")
  void containsKeyShouldThrowExceptionWhenKeyDoesNotExist() {
    Map<String, String> map = Map.of("key", "value");
    assertThatThrownBy(() -> Ensure.containsKey(map, "other"))
        .isInstanceOf(EnsureException.class)
        .hasMessage("map must contain key other");
  }

  @Test
  @DisplayName("containsKey should throw exception with custom message")
  void containsKeyShouldThrowExceptionWithCustomMessage() {
    Map<String, String> map = Map.of("key", "value");
    assertThatThrownBy(() -> Ensure.containsKey(map, "other", "custom message"))
        .isInstanceOf(EnsureException.class)
        .hasMessage("custom message");
  }

  @Test
  @DisplayName("containsValue should return map when value exists")
  void containsValueShouldReturnMapWhenValueExists() {
    Map<String, String> map = Map.of("key", "value");
    assertThat(Ensure.containsValue(map, "value")).isEqualTo(map);
  }

  @Test
  @DisplayName("containsValue should throw custom exception")
  void containsValueShouldThrowCustomException() {
    Map<String, String> map = Map.of("key", "value");
    assertThatThrownBy(
            () -> Ensure.containsValue(map, "other", () -> new IllegalArgumentException("custom")))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("custom");
  }

  @Test
  @DisplayName("containsValue should throw exception when value does not exist")
  void containsValueShouldThrowExceptionWhenValueDoesNotExist() {
    Map<String, String> map = Map.of("key", "value");
    assertThatThrownBy(() -> Ensure.containsValue(map, "other"))
        .isInstanceOf(EnsureException.class)
        .hasMessage("map must contain value other");
  }

  @Test
  @DisplayName("containsValue should throw exception with custom message")
  void containsValueShouldThrowExceptionWithCustomMessage() {
    Map<String, String> map = Map.of("key", "value");
    assertThatThrownBy(() -> Ensure.containsValue(map, "other", "custom message"))
        .isInstanceOf(EnsureException.class)
        .hasMessage("custom message");
  }

  @Test
  @DisplayName("notEmpty should return map when not empty")
  void notEmptyShouldReturnMapWhenNotEmpty() {
    Map<String, String> map = Map.of("key", "value");
    assertThat(Ensure.notEmpty(map)).isEqualTo(map);
  }

  @Test
  @DisplayName("notEmpty should throw custom exception")
  void notEmptyShouldThrowCustomException() {
    Map<String, String> map = Map.of();
    assertThatThrownBy(() -> Ensure.notEmpty(map, () -> new IllegalArgumentException("custom")))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("custom");
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
}
