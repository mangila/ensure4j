package io.github.mangila.ensure4j;

import static io.github.mangila.ensure4j.ArchTestUtils.getMethodCount;
import static io.github.mangila.ensure4j.ArchTestUtils.getMethodNames;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.function.Supplier;
import org.junit.jupiter.api.Test;

class EnsureArchitectureTest {

  @Test
  void shouldThrowIfSupplierIsNull() {
    Supplier<RuntimeException> supplier = null;
    assertThatThrownBy(
            () -> {
              Ensure.isTrue(false, supplier);
            })
        .isInstanceOf(EnsureException.class)
        .hasMessage("supplier was null");
  }

  @Test
  void shouldThrowIfSupplierIsNullWithCustomMessage() {
    Supplier<RuntimeException> supplier = () -> null;
    assertThatThrownBy(
            () -> {
              Ensure.isTrue(false, supplier);
            })
        .isInstanceOf(EnsureException.class)
        .hasMessage("supplier was given a null value");
  }

  @Test
  void shouldThrowIfCallingConstructor() throws NoSuchMethodException {
    Constructor<?> constructor = Ensure.class.getDeclaredConstructor();
    constructor.setAccessible(true);
    assertThatThrownBy(constructor::newInstance)
        .isInstanceOf(InvocationTargetException.class)
        .hasCauseInstanceOf(AssertionError.class)
        .hasRootCauseMessage("No Ensure4j for you!");
  }

  @Test
  void shouldCountOpsClasses() {
    final int expectedOpsCount = 10;
    final List<JavaClass> ensureOpsClasses =
        ArchTestUtils.ENSURE_PACKAGE.stream()
            .filter(javaClass -> javaClass.getSimpleName().endsWith("Ops"))
            .toList();
    assertThat(ensureOpsClasses)
        .as(
            "number of EnsureOps classes: %d - %s"
                .formatted(ensureOpsClasses.size(), ensureOpsClasses))
        .hasSize(expectedOpsCount);
  }

  @Test
  void shouldVerifyEnsure() {
    ArchRuleDefinition.theClass(Ensure.class)
        .should()
        .bePublic()
        .andShould()
        .haveOnlyPrivateConstructors()
        .andShould(
            new ArchCondition<>("verify Ensure methods:") {
              @Override
              public void check(JavaClass item, ConditionEvents events) {
                final long count = getMethodCount(item);
                final long expectedMethodCount = 111;
                assertThat(count)
                    .as(
                        "Expected methods: %s - %s"
                            .formatted(expectedMethodCount, getMethodNames(item)))
                    .isEqualTo(expectedMethodCount);
              }
            })
        .check(ArchTestUtils.ENSURE_PACKAGE);
  }
}
