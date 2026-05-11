package io.github.mangila.ensure4j.ops;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import io.github.mangila.ensure4j.EnsureException;
import org.junit.jupiter.api.Test;

class EnsureObjectOpsTest implements EnsureOpsTest<EnsureObjectOps> {

  @Override
  public Class<EnsureObjectOps> clazz() {
    return EnsureObjectOps.class;
  }

  @Override
  public EnsureObjectOps instance() {
    return EnsureObjectOps.INSTANCE;
  }

  @Override
  public long expectedPublicMethodCount() {
    return 11;
  }

  @Test
  void isInstanceOfSuccess() {
    String value = "test";
    String result = instance().isInstanceOf(String.class, value);
    assertThat(result).isEqualTo(value);
  }

  @Test
  void isInstanceOfFailure() {
    assertThatThrownBy(() -> instance().isInstanceOf(String.class, 123))
        .isInstanceOf(EnsureException.class)
        .hasMessage("object must be an instance of java.lang.String");
  }

  @Test
  void isInstanceOfInstanceIsNull() {
    assertThatThrownBy(() -> instance().isInstanceOf(null, 123))
        .isInstanceOf(EnsureException.class)
        .hasMessage("class must not be null");
  }

  @Test
  void isInstanceOfInstanceIsNullCustomException() {
    assertThatThrownBy(
            () ->
                instance()
                    .isInstanceOf(
                        null, 123, () -> new IllegalArgumentException("custom exception")))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("custom exception");
  }

  @Test
  void isInstanceOfCustomMessage() {
    assertThatThrownBy(() -> instance().isInstanceOf(String.class, 123, "custom message"))
        .isInstanceOf(EnsureException.class)
        .hasMessage("custom message");
  }

  @Test
  void isInstanceOfCustomException() {
    assertThatThrownBy(
            () ->
                instance()
                    .isInstanceOf(
                        String.class, 123, () -> new IllegalArgumentException("custom exception")))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("custom exception");
  }

  @Test
  void isEqualsEnumSuccess() {

    enum TestEnum {
      A,
    }

    TestEnum val1 = TestEnum.A;
    TestEnum val2 = TestEnum.A;
    TestEnum result = instance().isEquals(val1, val2);
    assertThat(result).isEqualTo(val1);
  }

  @Test
  void isEqualsEnumFailure() {

    enum TestEnum {
      A,
      B
    }

    assertThatThrownBy(() -> instance().isEquals(TestEnum.A, TestEnum.B))
        .isInstanceOf(EnsureException.class)
        .hasMessage("enums must be equal");
  }

  @Test
  void isEqualsObjectSuccess() {
    String val1 = "test";
    String val2 = new String("test");
    String result = instance().isEquals(val1, val2);
    assertThat(result).isEqualTo(val1);

    String val3 = "test";
    String val4 = val3;
    String result2 = instance().isEquals(val3, val4);
    assertThat(result2).isEqualTo(val3);
  }

  @Test
  void isEqualsObjectFailure() {
    assertThatThrownBy(() -> instance().isEquals("a", "b"))
        .isInstanceOf(EnsureException.class)
        .hasMessage("objects must be equal");
  }

  @Test
  void isEqualsObjectNullFailure() {
    assertThatThrownBy(() -> instance().isEquals(null, "b")).isInstanceOf(EnsureException.class);
  }
}
