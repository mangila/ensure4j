package io.github.mangila.ensure4j;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EnsureObjectOpsTest implements EnsureOpsArchTest<EnsureObjectOps> {

  @Override
  public Class<EnsureObjectOps> clazz() {
    return EnsureObjectOps.class;
  }

  @Override
  public long expectedMethodCount() {
    return 3;
  }

  @Test
  @DisplayName("deepEqualTo should return object when deeply equal")
  void deepEqualToShouldReturnObjectWhenDeeplyEqual() {
    List<String> value = List.of("test");
    assertThat(Ensure.deepEqualTo(value, List.of("test"))).isEqualTo(value);
  }

  @Test
  @DisplayName("deepEqualTo should throw custom exception")
  void deepEqualToShouldThrowCustomException() {
    final List<String> list1 = List.of("test1");
    final List<String> list2 = List.of("test2");
    assertThatThrownBy(
            () -> Ensure.deepEqualTo(list1, list2, () -> new IllegalArgumentException("custom")))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("custom");
  }

  @Test
  @DisplayName("deepEqualTo should throw exception when not deeply equal")
  void deepEqualToShouldThrowExceptionWhenNotDeeplyEqual() {
    final List<String> list1 = List.of("test1");
    final List<String> list2 = List.of("test2");
    assertThatThrownBy(() -> Ensure.deepEqualTo(list1, list2))
        .isInstanceOf(EnsureException.class)
        .hasMessage("objects must be deeply equal");
  }

  @Test
  @DisplayName("deepEqualTo should throw exception with custom message")
  void deepEqualToShouldThrowExceptionWithCustomMessage() {
    final List<String> list1 = List.of("test1");
    final List<String> list2 = List.of("test2");
    assertThatThrownBy(() -> Ensure.deepEqualTo(list1, list2, "custom message"))
        .isInstanceOf(EnsureException.class)
        .hasMessage("custom message");
  }

  @Test
  @DisplayName("equalTo should return object when equal")
  void equalToShouldReturnObjectWhenEqual() {
    String value = "test";
    assertThat(Ensure.equalTo(value, "test")).isEqualTo(value);
  }

  @Test
  @DisplayName("equalTo should throw custom exception")
  void equalToShouldThrowCustomException() {
    assertThatThrownBy(
            () -> Ensure.equalTo("test1", "test2", () -> new IllegalArgumentException("custom")))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("custom");
  }

  @Test
  @DisplayName("equalTo should throw exception when not equal")
  void equalToShouldThrowExceptionWhenNotEqual() {
    assertThatThrownBy(() -> Ensure.equalTo("test1", "test2"))
        .isInstanceOf(EnsureException.class)
        .hasMessage("objects must be equal");
  }

  @Test
  @DisplayName("equalTo should throw exception with custom message")
  void equalToShouldThrowExceptionWithCustomMessage() {
    assertThatThrownBy(() -> Ensure.equalTo("test1", "test2", "custom message"))
        .isInstanceOf(EnsureException.class)
        .hasMessage("custom message");
  }

  @Test
  @DisplayName("typeOf should return object when correct type")
  void typeOfShouldReturnObjectWhenCorrectType() {
    String value = "test";
    assertThat(Ensure.typeOf(value, String.class)).isEqualTo(value);
  }

  @Test
  @DisplayName("typeOf should throw custom exception")
  void typeOfShouldThrowCustomException() {
    Object value = 123;
    assertThatThrownBy(
            () -> Ensure.typeOf(value, String.class, () -> new IllegalArgumentException("custom")))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("custom");
  }

  @Test
  @DisplayName("typeOf should throw exception when class is null")
  void typeOfShouldThrowExceptionWhenClassIsNull() {
    Object value = 123;
    assertThatThrownBy(() -> Ensure.typeOf(value, null))
        .isInstanceOf(EnsureException.class)
        .hasMessage("object must be a type of");
  }

  @Test
  @DisplayName("typeOf should throw exception when incorrect type")
  void typeOfShouldThrowExceptionWhenIncorrectType() {
    Object value = 123;
    assertThatThrownBy(() -> Ensure.typeOf(value, String.class))
        .isInstanceOf(EnsureException.class)
        .hasMessage("object must be a type of");
  }

  @Test
  @DisplayName("typeOf should throw exception with custom message")
  void typeOfShouldThrowExceptionWithCustomMessage() {
    Object value = 123;
    assertThatThrownBy(() -> Ensure.typeOf(value, String.class, "custom message"))
        .isInstanceOf(EnsureException.class)
        .hasMessage("custom message");
  }
}
