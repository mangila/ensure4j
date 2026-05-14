package io.github.mangila.ensure4j.ops;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import io.github.mangila.ensure4j.EnsureException;
import org.junit.jupiter.api.Test;

class EnsureArrayOpsTest implements EnsureOpsTest<EnsureArrayOps> {

  @Override
  public Class<EnsureArrayOps> clazz() {
    return EnsureArrayOps.class;
  }

  @Override
  public EnsureArrayOps instance() {
    return EnsureArrayOps.INSTANCE;
  }

  @Override
  public long expectedPublicMethodCount() {
    return 11;
  }

  @Test
  void notEmptySuccess() {
    String[] array = {"a", "b"};
    String[] result = instance().notEmpty(array);
    assertThat(result).isSameAs(array);
  }

  @Test
  void notEmptyFailure() {
    String[] array = {};
    assertThatThrownBy(() -> instance().notEmpty(array))
        .isInstanceOf(EnsureException.class)
        .hasMessage("array must not be empty");
  }

  @Test
  void notEmptyNullFailure() {
    assertThatThrownBy(() -> instance().notEmpty(null)).isInstanceOf(EnsureException.class);
  }

  @Test
  void equalToSuccess() {
    String[] val1 = {"test"};
    String[] val2 = {"test"};
    String[] result = instance().equalTo(val1, val2);
    assertThat(result).isSameAs(val1);
  }

  @Test
  void equalToFailure() {
    String[] val1 = {"test"};
    String[] val2 = {"not equal"}; // different instance, same content
    assertThatThrownBy(() -> instance().equalTo(val1, val2))
        .isInstanceOf(EnsureException.class)
        .hasMessage("arrays must be equal");
  }

  @Test
  void deepEqualToSuccess() {
    Object[] val1 = {new int[] {1, 2}};
    Object[] val2 = {new int[] {1, 2}};
    Object[] result = instance().deepEqualTo(val1, val2);
    assertThat(result).isSameAs(val1);
  }

  @Test
  void deepEqualToFailure() {
    String[] val1 = {"test"};
    String[] val2 = {"other"};
    assertThatThrownBy(() -> instance().deepEqualTo(val1, val2))
        .isInstanceOf(EnsureException.class)
        .hasMessage("arrays must be deeply equal");
  }

  @Test
  void deepEqualToCustomMessage() {
    String[] val1 = {"test"};
    String[] val2 = {"other"};
    assertThatThrownBy(() -> instance().deepEqualTo(val1, val2, "custom message"))
        .isInstanceOf(EnsureException.class)
        .hasMessage("custom message");
  }

  @Test
  void deepEqualToCustomException() {
    String[] val1 = {"test"};
    String[] val2 = {"other"};
    assertThatThrownBy(
            () ->
                instance()
                    .deepEqualTo(
                        val1, val2, () -> new IllegalArgumentException("custom exception")))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("custom exception");
  }
}
