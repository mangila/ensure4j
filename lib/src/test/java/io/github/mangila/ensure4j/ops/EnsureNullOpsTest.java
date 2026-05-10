package io.github.mangila.ensure4j.ops;

import static org.assertj.core.api.Assertions.*;

import io.github.mangila.ensure4j.EnsureException;
import org.junit.jupiter.api.Test;

class EnsureNullOpsTest implements EnsureOpsTest<EnsureNullOps> {

  @Override
  public Class<EnsureNullOps> clazz() {
    return EnsureNullOps.class;
  }

  @Override
  public EnsureNullOps instance() {
    return EnsureNullOps.INSTANCE;
  }

  @Override
  public long expectedPublicMethodCount() {
    return 7;
  }

  @Test
  void notNullSuccess() {
    assertThatCode(
            () -> {
              var obj = new Object();
              instance().notNull(obj);
              instance().notNull(obj, "message");
              instance().notNull(obj, () -> new RuntimeException("custom"));
            })
        .doesNotThrowAnyException();
  }

  @Test
  void notNullFailure() {
    assertThatThrownBy(() -> instance().notNull(null))
        .isInstanceOf(EnsureException.class)
        .hasMessage("object must not be null");
    assertThatThrownBy(() -> instance().notNull(null, "message"))
        .isInstanceOf(EnsureException.class)
        .hasMessage("message");
    assertThatThrownBy(() -> instance().notNull(null, () -> new RuntimeException("message")))
        .isInstanceOf(RuntimeException.class)
        .hasMessage("message");
  }

  @Test
  void notNullOrElse() {
    var str = "test";
    var fallBack = "fallback";
    assertThat(instance().notNullOrElse(str, fallBack)).isEqualTo(str);
    assertThat(instance().notNullOrElse(null, fallBack)).isEqualTo(fallBack);
  }

  @Test
  void notNullOrElseGet() {
    var str = "test";
    var fallBack = "fallback";
    assertThat(instance().notNullOrElseGet(str, () -> fallBack)).isEqualTo(str);
    assertThat(instance().notNullOrElseGet(null, () -> fallBack)).isEqualTo(fallBack);
  }
}
