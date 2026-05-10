package io.github.mangila.ensure4j.ops;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import io.github.mangila.ensure4j.EnsureException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.junit.jupiter.api.Test;

class EnsureCollectionOpsTest {

  private final EnsureCollectionOps ops = EnsureCollectionOps.INSTANCE;

  @Test
  void containsElementSuccess() {
    List<String> list = Arrays.asList("a", "b", "c");
    Collection<String> result = ops.containsElement(list, "b");
    assertThat(result).isSameAs(list);
  }

  @Test
  void containsElementFailure() {
    List<String> list = Arrays.asList("a", "b", "c");
    assertThatThrownBy(() -> ops.containsElement(list, "d"))
        .isInstanceOf(EnsureException.class)
        .hasMessage("collection must contain element 'd'");
  }

  @Test
  void containsElementCollectionIsNull() {
    List<String> list = null;
    assertThatThrownBy(() -> ops.containsElement(list, "a")).isInstanceOf(EnsureException.class);
  }

  @Test
  void notContainsNullIterateSuccess() {
    List<String> list = Arrays.asList("a", "b", "c");
    Collection<String> result = ops.notContainsNullIterate(list);
    assertThat(result).isSameAs(list);
  }

  @Test
  void notContainsNullIterateFailure() {
    List<String> list = Arrays.asList("a", null, "c");
    assertThatThrownBy(() -> ops.notContainsNullIterate(list))
        .isInstanceOf(EnsureException.class)
        .hasMessage("collection must not contain null elements");
  }

  @Test
  void notContainsNullIterateCollectionIsNull() {
    List<String> list = null;
    assertThatThrownBy(() -> ops.notContainsNullIterate(list)).isInstanceOf(EnsureException.class);
  }

  @Test
  void notContainsNullSuccess() {
    List<String> list = Arrays.asList("a", "b", "c");
    Collection<String> result = ops.notContainsNull(list);
    assertThat(result).isSameAs(list);
  }

  @Test
  void notContainsNullFailure() {
    List<String> list = Arrays.asList("a", null, "c");
    assertThatThrownBy(() -> ops.notContainsNull(list))
        .isInstanceOf(EnsureException.class)
        .hasMessage("collection must not contain null elements");
  }

  @Test
  void notContainsNullCollectionIsNull() {
    List<String> list = null;
    assertThatThrownBy(() -> ops.notContainsNull(list)).isInstanceOf(EnsureException.class);
  }

  @Test
  void notEmptySuccess() {
    List<String> list = Arrays.asList("a");
    Collection<String> result = ops.notEmpty(list);
    assertThat(result).isSameAs(list);
  }

  @Test
  void notEmptyFailure() {
    List<String> list = new ArrayList<>();
    assertThatThrownBy(() -> ops.notEmpty(list))
        .isInstanceOf(EnsureException.class)
        .hasMessage("collection must not be empty or null");
  }

  @Test
  void notEmptyCollectionIsNull() {
    List<String> list = null;
    assertThatThrownBy(() -> ops.notEmpty(list))
        .isInstanceOf(EnsureException.class)
        .hasMessage("collection must not be empty or null");
  }
}
