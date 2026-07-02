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
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public interface EnsureOpsArchTest<T> {

  Class<T> clazz();

  long expectedMethodCount();

  @Test
  @DisplayName("EnsureOps Classes should have expected architecture")
  default void shouldHaveExpectedArch() {
    ArchRuleDefinition.theClass(clazz())
        .should()
        .haveSimpleNameStartingWith("Ensure")
        .andShould()
        .haveSimpleNameEndingWith("Ops")
        .andShould()
        .bePackagePrivate()
        .andShould()
        .haveOnlyPrivateConstructors()
        .andShould()
        .haveOnlyFinalFields()
        .andShould(
            new ArchCondition<>("assert methods: %s".formatted(clazz().getSimpleName())) {
              @Override
              public void check(JavaClass javaClass, ConditionEvents events) {
                final long count = getMethodCount(javaClass);
                final long expectedMethodCount = expectedMethodCount();
                assertThat(count)
                    .as(
                        "Expected methods: %s - %s"
                            .formatted(expectedMethodCount, getMethodNames(javaClass)))
                    .isEqualTo(expectedMethodCount);
              }
            })
        .check(ArchTestUtils.ENSURE_PACKAGE);
  }

  @Test
  @DisplayName("EnsureOps Classes should throw if instantiated")
  default void shouldThrowIfCallingConstructor() throws NoSuchMethodException {
    Constructor<?> constructor = clazz().getDeclaredConstructor();
    constructor.setAccessible(true);
    assertThatThrownBy(constructor::newInstance)
        .isInstanceOf(InvocationTargetException.class)
        .hasCauseInstanceOf(AssertionError.class)
        .hasRootCauseMessage("No Ensure4j for you!");
  }
}
