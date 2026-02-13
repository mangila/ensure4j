package io.github.mangila.ensure4j.architecture;

import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.SimpleConditionEvent;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import io.github.mangila.ensure4j.ops.EnsureArrayOps;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.github.mangila.ensure4j.architecture.ArchTestUtils.getCountByMethodName;
import static io.github.mangila.ensure4j.architecture.ArchTestUtils.getPublicMethodNames;

public class EnsureArrayOpsArchitectureTest {

    @Test
    void test() {
        ArchRuleDefinition.theClass(EnsureArrayOps.class)
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
                .andShould(new ArchCondition<>("assert public methods named") {
                    @Override
                    public void check(JavaClass javaClass, ConditionEvents events) {
                        assertPublicMethods(javaClass, events);
                    }
                })
                .check(ArchTestUtils.ensureOpsClasses);
    }

    private void assertPublicMethods(JavaClass javaClass, ConditionEvents events) {
        List<String> publicMethodNames = getPublicMethodNames(javaClass);
        Map<String, Long> countByName = getCountByMethodName(javaClass);
        int notEmptyCount = 3;
        for (String methodName : publicMethodNames) {
            switch (methodName) {
                case "notEmpty" -> {
                    var count = countByName.get(methodName);
                    if (count != notEmptyCount) {
                        events.add(SimpleConditionEvent.violated(javaClass, "method name %s should be called %s times".formatted(methodName, notEmptyCount)));
                    }
                }
                // Enum default methods, ignore
                case "valueOf" -> {
                }
                case "values" -> {
                }
                default -> events.add(SimpleConditionEvent.violated(javaClass, "method name cannot be empty"));
            }
        }
    }
}
