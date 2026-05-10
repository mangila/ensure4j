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
    return 5;
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
}
