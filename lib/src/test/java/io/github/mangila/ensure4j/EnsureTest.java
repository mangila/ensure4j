package io.github.mangila.ensure4j;

import static io.github.mangila.ensure4j.ArchTestUtils.getPublicMethodCount;
import static io.github.mangila.ensure4j.ArchTestUtils.getPublicMethodNames;
import static org.assertj.core.api.Assertions.*;

import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import io.github.mangila.ensure4j.ops.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EnsureTest {

  @Test
  @DisplayName("Ensure should have expected public methods")
  void shouldHaveExpectedPublicMethods() {
    final var clazz = Ensure.class;
    final int expectedPublicMethodCount = 18;
    ArchRuleDefinition.theClass(clazz)
        .should()
        .bePublic()
        .andShould(
            new ArchCondition<>("assert public methods: %s".formatted(clazz.getSimpleName())) {
              @Override
              public void check(JavaClass javaClass, ConditionEvents events) {
                long count = getPublicMethodCount(javaClass);
                assertThat(count)
                    .as(
                        "Expected public methods: %s - %s"
                            .formatted(expectedPublicMethodCount, getPublicMethodNames(javaClass)))
                    .isEqualTo(expectedPublicMethodCount);
              }
            })
        .check(ArchTestUtils.ENSURE_TOP_LEVEL);
  }

  @Test
  @DisplayName(
      "Should pass when utility class is instantiated via reflection and throw IllegalStateException")
  void shouldThrowWhenReflection() throws Exception {
    Constructor<?> constructor = Ensure.class.getDeclaredConstructor();
    constructor.setAccessible(true);
    assertThatThrownBy(constructor::newInstance)
        .isInstanceOf(InvocationTargetException.class)
        .hasCauseInstanceOf(IllegalStateException.class)
        .hasRootCauseMessage("Utility class");
  }

  @Test
  @DisplayName("Should test ops accessors")
  void testOpsAccessors() {
    assertThat(Ensure.arrays()).isInstanceOf(EnsureArrayOps.class);
    assertThat(Ensure.collections()).isInstanceOf(EnsureCollectionOps.class);
    assertThat(Ensure.maps()).isInstanceOf(EnsureMapOps.class);
    assertThat(Ensure.numbers()).isInstanceOf(EnsureNumberOps.class);
    assertThat(Ensure.objects()).isInstanceOf(EnsureObjectOps.class);
    assertThat(Ensure.strings()).isInstanceOf(EnsureStringOps.class);
    assertThat(Ensure.dates()).isInstanceOf(EnsureDateTimeOps.class);
  }

  @Test
  void invokeDelegateMethods() {
    assertThatCode(
            () -> {
              Ensure.isTrue(true);
              Ensure.isTrue(true, "message");
              Ensure.isTrue(true, () -> new RuntimeException("custom"));
            })
        .doesNotThrowAnyException();
    assertThatCode(
            () -> {
              Ensure.isFalse(false);
              Ensure.isFalse(false, "message");
              Ensure.isFalse(false, () -> new RuntimeException("custom"));
            })
        .doesNotThrowAnyException();
    assertThatCode(
            () -> {
              Ensure.notNull("str");
              Ensure.notNull("str", "message");
              Ensure.notNull("str", () -> new RuntimeException("custom"));
            })
        .doesNotThrowAnyException();
    assertThatCode(() -> Ensure.notNullOrElse("str", "fallback")).doesNotThrowAnyException();
    assertThatCode(() -> Ensure.notNullOrElseGet("str", () -> "fallback"))
        .doesNotThrowAnyException();
  }
}
