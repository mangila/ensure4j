package io.github.mangila.ensure4j.architecture;

import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.SimpleConditionEvent;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import io.github.mangila.ensure4j.ops.EnsureNumberOps;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.github.mangila.ensure4j.architecture.ArchTestUtils.getCountByMethodName;
import static io.github.mangila.ensure4j.architecture.ArchTestUtils.getPublicMethodNames;

public class EnsureNumberOpsArchitectureTest {

    @Test
    void test() {
        ArchRuleDefinition.theClass(EnsureNumberOps.class)
                .should()
                .beEnums()
                .andShould(new ArchCondition<>("assert EnsureNumberOps public methods named") {
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
                case "max" -> {
                    int maxCount = 6;
                    var count = countByName.get(methodName);
                    if (count != maxCount) {
                        events.add(SimpleConditionEvent.violated(javaClass, "method name %s should be called %s times".formatted(methodName, maxCount)));
                    }
                }
                case "min" -> {
                    int minCount = 6;
                    var count = countByName.get(methodName);
                    if (count != minCount) {
                        events.add(SimpleConditionEvent.violated(javaClass, "method name %s should be called %s times".formatted(methodName, minCount)));
                    }
                }
                case "negative" -> {
                    int negativeCount = 6;
                    var count = countByName.get(methodName);
                    if (count != negativeCount) {
                        events.add(SimpleConditionEvent.violated(javaClass, "method name %s should be called %s times".formatted(methodName, negativeCount)));
                    }
                }
                case "positive" -> {
                    int positiveCount = 6;
                    var count = countByName.get(methodName);
                    if (count != positiveCount) {
                        events.add(SimpleConditionEvent.violated(javaClass, "method name %s should be called %s times".formatted(methodName, positiveCount)));
                    }
                }
                case "positiveWithZero" -> {
                    int positiveWithZeroCount = 6;
                    var count = countByName.get(methodName);
                    if (count != positiveWithZeroCount) {
                        events.add(SimpleConditionEvent.violated(javaClass, "method name %s should be called %s times".formatted(methodName, positiveWithZeroCount)));
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
