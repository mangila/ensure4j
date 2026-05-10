package io.github.mangila.ensure4j.ops;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import io.github.mangila.ensure4j.EnsureException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.junit.jupiter.api.Test;

class EnsureCollectionOpsTest implements EnsureOpsTest<EnsureCollectionOps> {

  @Override
  public Class<EnsureCollectionOps> clazz() {
    return EnsureCollectionOps.class;
  }

  @Override
  public EnsureCollectionOps instance() {
    return EnsureCollectionOps.INSTANCE;
  }

  @Override
  public long expectedPublicMethodCount() {
    return 14;
  }

  @Test
  void containsElementSuccess() {
    List<String> list = Arrays.asList("a", "b", "c");
    Collection<String> result = instance().containsElement(list, "b");
    assertThat(result).isSameAs(list);
  }

  @Test
  void containsElementFailure() {
    List<String> list = Arrays.asList("a", "b", "c");
    assertThatThrownBy(() -> instance().containsElement(list, "d"))
        .isInstanceOf(EnsureException.class)
        .hasMessage("collection must contain element 'd'");
  }

  @Test
  void containsElementCollectionIsNull() {
    List<String> list = null;
    assertThatThrownBy(() -> instance().containsElement(list, "a"))
        .isInstanceOf(EnsureException.class);
  }

  @Test
  void notContainsNullIterateSuccess() {
    List<String> list = Arrays.asList("a", "b", "c");
    Collection<String> result = instance().notContainsNullIterate(list);
    assertThat(result).isSameAs(list);
  }

  @Test
  void notContainsNullIterateFailure() {
    List<String> list = Arrays.asList("a", null, "c");
    assertThatThrownBy(() -> instance().notContainsNullIterate(list))
        .isInstanceOf(EnsureException.class)
        .hasMessage("collection must not contain null elements");
  }

  @Test
  void notContainsNullIterateCollectionIsNull() {
    List<String> list = null;
    assertThatThrownBy(() -> instance().notContainsNullIterate(list))
        .isInstanceOf(EnsureException.class);
  }

  @Test
  void notContainsNullSuccess() {
    List<String> list = Arrays.asList("a", "b", "c");
    Collection<String> result = instance().notContainsNull(list);
    assertThat(result).isSameAs(list);
  }

  @Test
  void notContainsNullFailure() {
    List<String> list = Arrays.asList("a", null, "c");
    assertThatThrownBy(() -> instance().notContainsNull(list))
        .isInstanceOf(EnsureException.class)
        .hasMessage("collection must not contain null elements");
  }

  @Test
  void notContainsNullCollectionIsNull() {
    List<String> list = null;
    assertThatThrownBy(() -> instance().notContainsNull(list)).isInstanceOf(EnsureException.class);
  }

  @Test
  void notEmptySuccess() {
    List<String> list = Arrays.asList("a");
    Collection<String> result = instance().notEmpty(list);
    assertThat(result).isSameAs(list);
  }

  @Test
  void notEmptyFailure() {
    List<String> list = new ArrayList<>();
    assertThatThrownBy(() -> instance().notEmpty(list))
        .isInstanceOf(EnsureException.class)
        .hasMessage("collection must not be empty or null");
  }

  @Test
  void notEmptyCollectionIsNull() {
    List<String> list = null;
    assertThatThrownBy(() -> instance().notEmpty(list))
        .isInstanceOf(EnsureException.class)
        .hasMessage("collection must not be empty or null");
  }
}
