package io.github.mangila.ensure4j.architecture;

import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.SimpleConditionEvent;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import io.github.mangila.ensure4j.ops.EnsureStringOps;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.github.mangila.ensure4j.architecture.ArchTestUtils.getCountByMethodName;
import static io.github.mangila.ensure4j.architecture.ArchTestUtils.getPublicMethodNames;

public class EnsureStringOpsArchitectureTest {

    @Test
    void test() {
        ArchRuleDefinition.theClass(EnsureStringOps.class)
                .should()
                .beEnums()
                .andShould(new ArchCondition<>("assert EnsureStringOps public methods named") {
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
                case "maxLength" -> {
                    int maxLengthCount = 3;
                    var count = countByName.get(methodName);
                    if (count != maxLengthCount) {
                        events.add(SimpleConditionEvent.violated(javaClass, "method name %s should be called %s times".formatted(methodName, maxLengthCount)));
                    }
                }
                case "minLength" -> {
                    int minLengthCount = 3;
                    var count = countByName.get(methodName);
                    if (count != minLengthCount) {
                        events.add(SimpleConditionEvent.violated(javaClass, "method name %s should be called %s times".formatted(methodName, minLengthCount)));
                    }
                }
                case "notBlank" -> {
                    int notBlankCount = 3;
                    var count = countByName.get(methodName);
                    if (count != notBlankCount) {
                        events.add(SimpleConditionEvent.violated(javaClass, "method name %s should be called %s times".formatted(methodName, notBlankCount)));
                    }
                }
                case "notBlankOrElse" -> {
                    int notBlankOrElseCount = 1;
                    var count = countByName.get(methodName);
                    if (count != notBlankOrElseCount) {
                        events.add(SimpleConditionEvent.violated(javaClass, "method name %s should be called %s times".formatted(methodName, notBlankOrElseCount)));
                    }
                }
                case "notBlankOrElseGet" -> {
                    int notBlankOrElseGetCount = 1;
                    var count = countByName.get(methodName);
                    if (count != notBlankOrElseGetCount) {
                        events.add(SimpleConditionEvent.violated(javaClass, "method name %s should be called %s times".formatted(methodName, notBlankOrElseGetCount)));
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
