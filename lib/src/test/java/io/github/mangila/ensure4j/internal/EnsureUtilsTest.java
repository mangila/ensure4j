package io.github.mangila.ensure4j.internal;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.github.mangila.ensure4j.EnsureException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EnsureUtilsTest {

  @Test
  @DisplayName(
      "Should pass when utility class is instantiated via reflection and throw IllegalStateException")
  void shouldThrowWhenReflection() throws Exception {
    Constructor<?> constructor = EnsureUtils.class.getDeclaredConstructor();
    constructor.setAccessible(true);
    assertThatThrownBy(constructor::newInstance)
        .isInstanceOf(InvocationTargetException.class)
        .hasCauseInstanceOf(IllegalStateException.class)
        .hasRootCauseMessage("Utility class");
  }

  @Test
  void isBlank() {
    String s = "";
    assertTrue(EnsureUtils.isBlank(s));
  }

  @Test
  void getSupplierOrThrow() {
    assertThatThrownBy(() -> EnsureUtils.getSupplierOrThrow(null))
        .isInstanceOf(EnsureException.class)
        .hasMessage("supplier was null");
    assertThatThrownBy(() -> EnsureUtils.getSupplierOrThrow(() -> null))
        .isInstanceOf(EnsureException.class)
        .hasMessage("supplier was given a null value");
  }
}
