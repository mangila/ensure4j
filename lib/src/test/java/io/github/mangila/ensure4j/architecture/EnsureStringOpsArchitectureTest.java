package io.github.mangila.ensure4j.architecture;

import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import io.github.mangila.ensure4j.ops.EnsureStringOps;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.github.mangila.ensure4j.architecture.ArchTestUtils.getCountByPublicMethodNames;
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
                .check(ArchTestUtils.ENSURE_OPS_CLASSES);
    }

    private void assertPublicMethods(JavaClass javaClass, ConditionEvents events) {
        List<String> publicMethodNames = getPublicMethodNames(javaClass);
        Map<String, Long> countByName = getCountByPublicMethodNames(javaClass);
        for (String methodName : publicMethodNames) {
            switch (methodName) {
                case "maxLength" -> {
                    int maxLengthCount = 3;
                    var count = countByName.get(methodName);
                    ArchTestUtils.checkOrAddMethodNameViolationEvent(javaClass, events, methodName, maxLengthCount, count);
                }
                case "minLength" -> {
                    int minLengthCount = 3;
                    var count = countByName.get(methodName);
                    ArchTestUtils.checkOrAddMethodNameViolationEvent(javaClass, events, methodName, minLengthCount, count);
                }
                case "notBlank" -> {
                    int notBlankCount = 3;
                    var count = countByName.get(methodName);
                    ArchTestUtils.checkOrAddMethodNameViolationEvent(javaClass, events, methodName, notBlankCount, count);
                }
                case "notBlankOrElse" -> {
                    int notBlankOrElseCount = 1;
                    var count = countByName.get(methodName);
                    ArchTestUtils.checkOrAddMethodNameViolationEvent(javaClass, events, methodName, notBlankOrElseCount, count);
                }
                case "notBlankOrElseGet" -> {
                    int notBlankOrElseGetCount = 1;
                    var count = countByName.get(methodName);
                    ArchTestUtils.checkOrAddMethodNameViolationEvent(javaClass, events, methodName, notBlankOrElseGetCount, count);
                }
                // Enum default methods
                case "valueOf" -> {
                    // do nothing
                }
                case "values" -> {
                    // do nothing
                }
                default -> ArchTestUtils.addMissingMethodViolationEvent(javaClass, events, methodName);
            }
        }
    }
}
