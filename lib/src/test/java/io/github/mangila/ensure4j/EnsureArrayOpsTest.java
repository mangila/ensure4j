package io.github.mangila.ensure4j;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EnsureArrayOpsTest implements EnsureOpsArchTest<EnsureArrayOps> {

  @Override
  public Class<EnsureArrayOps> clazz() {
    return EnsureArrayOps.class;
  }

  @Override
  public long expectedMethodCount() {
    return 3;
  }

  @Test
  @DisplayName("deepEqualTo should return array when deeply equal")
  void deepEqualToShouldReturnArrayWhenDeeplyEqual() {
    String[][] array1 = {{"test"}};
    String[][] array2 = {{"test"}};
    assertThat(Ensure.deepEqualTo(array1, array2)).isEqualTo(array1);
  }

  @Test
  @DisplayName("deepEqualTo should throw exception when not deeply equal")
  void deepEqualToShouldThrowExceptionWhenNotDeeplyEqual() {
    String[][] array1 = {{"test1"}};
    String[][] array2 = {{"test2"}};
    assertThatThrownBy(() -> Ensure.deepEqualTo(array1, array2))
        .isInstanceOf(EnsureException.class)
        .hasMessage("arrays must be deep equal");
  }

  @Test
  @DisplayName("equalTo should return array when equal")
  void equalToShouldReturnArrayWhenEqual() {
    String[] array1 = {"test"};
    String[] array2 = {"test"};
    assertThat(Ensure.equalTo(array1, array2)).isEqualTo(array1);
  }

  @Test
  @DisplayName("equalTo should throw exception when not equal")
  void equalToShouldThrowExceptionWhenNotEqual() {
    String[] array1 = {"test1"};
    String[] array2 = {"test2"};
    assertThatThrownBy(() -> Ensure.equalTo(array1, array2))
        .isInstanceOf(EnsureException.class)
        .hasMessage("arrays must be equal");
  }

  @Test
  @DisplayName("notEmpty should return array when not empty")
  void notEmptyShouldReturnArrayWhenNotEmpty() {
    String[] array = {"test"};
    assertThat(Ensure.notEmpty(array)).isEqualTo(array);
  }

  @Test
  @DisplayName("notEmpty should throw custom exception")
  void notEmptyShouldThrowCustomException() {
    assertThatThrownBy(
            () -> Ensure.notEmpty(new String[0], () -> new IllegalArgumentException("custom")))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("custom");
  }

  @Test
  @DisplayName("notEmpty should throw exception when empty")
  void notEmptyShouldThrowExceptionWhenEmpty() {
    assertThatThrownBy(() -> Ensure.notEmpty(new String[0]))
        .isInstanceOf(EnsureException.class)
        .hasMessage("array must not be empty");
  }

  @Test
  @DisplayName("notEmpty should throw exception when null")
  void notEmptyShouldThrowExceptionWhenNull() {
    assertThatThrownBy(() -> Ensure.notEmpty((String[]) null))
        .isInstanceOf(EnsureException.class)
        .hasMessage("array must not be empty");
  }

  @Test
  @DisplayName("notEmpty should throw exception with custom message")
  void notEmptyShouldThrowExceptionWithCustomMessage() {
    assertThatThrownBy(() -> Ensure.notEmpty(new String[0], "custom message"))
        .isInstanceOf(EnsureException.class)
        .hasMessage("custom message");
  }
}
