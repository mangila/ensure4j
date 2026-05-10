package io.github.mangila.ensure4j.ops;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import io.github.mangila.ensure4j.EnsureException;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

class EnsureMapOpsTest implements EnsureOpsTest<EnsureMapOps> {

  @Override
  public Class<EnsureMapOps> clazz() {
    return EnsureMapOps.class;
  }

  @Override
  public EnsureMapOps instance() {
    return EnsureMapOps.INSTANCE;
  }

  @Override
  public long expectedPublicMethodCount() {
    return 5;
  }

  @Test
  void notEmptySuccess() {
    Map<String, String> map = new HashMap<>();
    map.put("key", "value");
    Map<String, String> result = instance().notEmpty(map);
    assertThat(result).isSameAs(map);
  }

  @Test
  void notEmptyFailure() {
    Map<String, String> map = new HashMap<>();
    assertThatThrownBy(() -> instance().notEmpty(map))
        .isInstanceOf(EnsureException.class)
        .hasMessage("map must not be empty or null");
  }

  @Test
  void notEmptyNullFailure() {
    assertThatThrownBy(() -> instance().notEmpty(null)).isInstanceOf(EnsureException.class);
  }
}
