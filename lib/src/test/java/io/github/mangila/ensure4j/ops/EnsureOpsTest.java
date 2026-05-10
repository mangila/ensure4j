package io.github.mangila.ensure4j.ops;

import static io.github.mangila.ensure4j.ArchTestUtils.getPublicMethodCount;
import static io.github.mangila.ensure4j.ArchTestUtils.getPublicMethodNames;
import static org.assertj.core.api.Assertions.assertThat;

import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.SimpleConditionEvent;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import io.github.mangila.ensure4j.ArchTestUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public interface EnsureOpsTest<T> {

  Class<T> clazz();

  T instance();

  long expectedPublicMethodCount();

  @Test
  @DisplayName("EnsureOpsTest should have expected public methods")
  default void shouldHaveExpectedPublicMethods() {
    ArchRuleDefinition.theClass(clazz())
        .should()
        .beEnums()
        .andShould()
        .bePublic()
        .andShould()
        .haveSimpleNameEndingWith("Ops")
        .andShould(
            new ArchCondition<>("assert public methods: %s".formatted(clazz().getSimpleName())) {
              @Override
              public void check(JavaClass javaClass, ConditionEvents events) {
                long count = getPublicMethodCount(javaClass);
                assertThat(count)
                    .as(
                        "Expected public methods: %s - %s"
                            .formatted(
                                expectedPublicMethodCount(), getPublicMethodNames(javaClass)))
                    .isEqualTo(expectedPublicMethodCount());
              }
            })
        .andShould(
            new ArchCondition<>("have exactly one enum constant with the name INSTANCE") {
              @Override
              public void check(JavaClass javaClass, ConditionEvents events) {
                var constants = javaClass.getEnumConstants();
                if (constants.size() != 1) {
                  events.add(
                      SimpleConditionEvent.violated(
                          javaClass, "should have exactly one enum constant"));
                }
                var tryGetEnumConstant = javaClass.tryGetEnumConstant("INSTANCE");
                if (tryGetEnumConstant.isEmpty()) {
                  events.add(
                      SimpleConditionEvent.violated(javaClass, "must have enum constant INSTANCE"));
                }
              }
            })
        .check(ArchTestUtils.ENSURE_OPS_CLASSES);
  }
}
