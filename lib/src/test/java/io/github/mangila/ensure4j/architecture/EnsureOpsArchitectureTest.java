package io.github.mangila.ensure4j.architecture;

import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.SimpleConditionEvent;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import org.junit.jupiter.api.Test;

public class EnsureOpsArchitectureTest {

    @Test
    void test() {
        ArchRuleDefinition.classes()
                .should()
                .beEnums()
                .andShould(new ArchCondition<>("have exactly one enum constant with the name INSTANCE") {
                    @Override
                    public void check(JavaClass javaClass, ConditionEvents events) {
                        var constants = javaClass.getEnumConstants();
                        if (constants.size() != 1) {
                            events.add(SimpleConditionEvent.violated(javaClass, "should have exactly one enum constant"));
                        }
                        var tryGetEnumConstant = javaClass.tryGetEnumConstant("INSTANCE");
                        if (tryGetEnumConstant.isEmpty()) {
                            events.add(SimpleConditionEvent.violated(javaClass, "must have enum constant INSTANCE"));
                        }
                    }
                })
                .check(ArchTestUtils.ENSURE_OPS_CLASSES);
    }
}
