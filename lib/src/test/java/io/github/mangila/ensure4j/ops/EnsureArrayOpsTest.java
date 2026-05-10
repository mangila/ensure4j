package io.github.mangila.ensure4j.ops;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import io.github.mangila.ensure4j.EnsureException;
import org.junit.jupiter.api.Test;

class EnsureArrayOpsTest {

  private final EnsureArrayOps ops = EnsureArrayOps.INSTANCE;

  @Test
  void notEmptySuccess() {
    String[] array = {"a", "b"};
    String[] result = ops.notEmpty(array);
    assertThat(result).isSameAs(array);
  }

  @Test
  void notEmptyFailure() {
    String[] array = {};
    assertThatThrownBy(() -> ops.notEmpty(array))
        .isInstanceOf(EnsureException.class)
        .hasMessage("array must not be empty");
  }

  @Test
  void notEmptyNullFailure() {
    assertThatThrownBy(() -> ops.notEmpty(null)).isInstanceOf(EnsureException.class);
  }
}
