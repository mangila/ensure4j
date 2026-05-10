package io.github.mangila.ensure4j.ops.architecture;

import static org.assertj.core.api.Assertions.assertThat;

import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaModifier;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.SimpleConditionEvent;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import io.github.mangila.ensure4j.ArchTestUtils;
import org.junit.jupiter.api.Test;

class EnsureOpsArchitectureTest {

  @Test
  void test() {
    int count = ArchTestUtils.ENSURE_OPS_CLASSES.stream().peek(System.out::println).toList().size();
    assertThat(count).as("number of EnsureOps enum classes").isEqualTo(8);
    ArchRuleDefinition.classes()
        .should()
        .beEnums()
        .andShould()
        .haveSimpleNameEndingWith("Ops")
        .andShould()
        .haveModifier(JavaModifier.PUBLIC)
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
