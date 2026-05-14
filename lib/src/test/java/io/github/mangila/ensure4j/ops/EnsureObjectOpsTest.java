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
    return 14;
  }

  @Test
  void isDeepEqualsSuccess() {
    String[] val1 = {"test"};
    String[] val2 = {"test"};
    String[] result = instance().isDeepEquals(val1, val2);
    assertThat(result).isEqualTo(val1);
  }

  @Test
  void isDeepEqualsFailure() {
    String[] val1 = {"test"};
    String[] val2 = {"other"};
    assertThatThrownBy(() -> instance().isDeepEquals(val1, val2))
        .isInstanceOf(EnsureException.class)
        .hasMessage("objects must be deeply equal");
  }

  @Test
  void isDeepEqualsCustomMessage() {
    String[] val1 = {"test"};
    String[] val2 = {"other"};
    assertThatThrownBy(() -> instance().isDeepEquals(val1, val2, "custom message"))
        .isInstanceOf(EnsureException.class)
        .hasMessage("custom message");
  }

  @Test
  void isDeepEqualsCustomException() {
    String[] val1 = {"test"};
    String[] val2 = {"other"};
    assertThatThrownBy(
            () ->
                instance()
                    .isDeepEquals(
                        val1, val2, () -> new IllegalArgumentException("custom exception")))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("custom exception");
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

    record TestRecord(String value) {}

    TestRecord val1 = new TestRecord("test");
    TestRecord val2 = new TestRecord("test");
    TestRecord result = instance().isEquals(val1, val2);
    assertThat(result).isEqualTo(val1);
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
