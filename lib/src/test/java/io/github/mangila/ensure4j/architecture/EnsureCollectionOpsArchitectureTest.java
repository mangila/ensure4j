package io.github.mangila.ensure4j.architecture;

import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.SimpleConditionEvent;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import io.github.mangila.ensure4j.ops.EnsureCollectionOps;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.github.mangila.ensure4j.architecture.ArchTestUtils.getCountByMethodName;
import static io.github.mangila.ensure4j.architecture.ArchTestUtils.getPublicMethodNames;

public class EnsureCollectionOpsArchitectureTest {

    @Test
    void test() {
        ArchRuleDefinition.theClass(EnsureCollectionOps.class)
                .should()
                .beEnums()
                .andShould(new ArchCondition<>("assert EnsureCollectionOps public methods named") {
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
        for (String methodName : publicMethodNames) {
            switch (methodName) {
                case "notEmpty" -> {
                    int notEmptyCount = 3;
                    var count = countByName.get(methodName);
                    if (count != notEmptyCount) {
                        events.add(SimpleConditionEvent.violated(javaClass, "method name %s should be called %s times".formatted(methodName, notEmptyCount)));
                    }
                }
                case "notContainsNull" -> {
                    int notContainsNullCount = 3;
                    var count = countByName.get(methodName);
                    if (count != notContainsNullCount) {
                        events.add(SimpleConditionEvent.violated(javaClass, "method name %s should be called %s times".formatted(methodName, notContainsNullCount)));
                    }
                }
                case "notContainsNullIterate" -> {
                    int notContainsNullIterateCount = 3;
                    var count = countByName.get(methodName);
                    if (count != notContainsNullIterateCount) {
                        events.add(SimpleConditionEvent.violated(javaClass, "method name %s should be called %s times".formatted(methodName, notContainsNullIterateCount)));
                    }
                }
                case "containsElement" -> {
                    int notContainsNullIterateCount = 3;
                    var count = countByName.get(methodName);
                    if (count != notContainsNullIterateCount) {
                        events.add(SimpleConditionEvent.violated(javaClass, "method name %s should be called %s times".formatted(methodName, notContainsNullIterateCount)));
                    }
                }
                // Enum default methods, ignore
                case "valueOf" -> {
                }
                case "values" -> {
                }
                default ->
                        events.add(SimpleConditionEvent.violated(javaClass, "missing method name %s".formatted(methodName)));
            }
        }
    }
}
