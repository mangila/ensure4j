package io.github.mangila.ensure4j.ops.architecture;

import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import io.github.mangila.ensure4j.ArchTestUtils;
import io.github.mangila.ensure4j.ClazzTest;
import io.github.mangila.ensure4j.PublicMethodArchitectureTest;
import io.github.mangila.ensure4j.ops.EnsureNullOps;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.github.mangila.ensure4j.ArchTestUtils.getCountByPublicMethodNames;
import static io.github.mangila.ensure4j.ArchTestUtils.getPublicMethodNames;

public class EnsureNullArchitectureOpsTest implements ClazzTest, PublicMethodArchitectureTest {

    @Override
    public Class<?> clazz() {
        return EnsureNullOps.class;
    }

    @Test
    void test() {
        ArchRuleDefinition.theClass(clazz())
                .should()
                .beEnums()
                .andShould(new ArchCondition<>("assert %s public methods".formatted(clazz().getSimpleName())) {
                    @Override
                    public void check(JavaClass javaClass, ConditionEvents events) {
                        assertPublicMethods(javaClass, events);
                    }
                })
                .check(ArchTestUtils.ENSURE_OPS_CLASSES);
    }

    @Override
    public void assertPublicMethods(JavaClass javaClass, ConditionEvents events) {
        List<String> publicMethodNames = getPublicMethodNames(javaClass);
        Map<String, Long> countByName = getCountByPublicMethodNames(javaClass);
        for (String methodName : publicMethodNames) {
            switch (methodName) {
                case "notNull" -> {
                    int notNullCount = 3;
                    var count = countByName.get(methodName);
                    ArchTestUtils.checkOrAddMethodNameViolationEvent(javaClass, events, methodName, notNullCount, count);
                }
                case "notNullOrElse" -> {
                    int notNullOrElseCount = 1;
                    var count = countByName.get(methodName);
                    ArchTestUtils.checkOrAddMethodNameViolationEvent(javaClass, events, methodName, notNullOrElseCount, count);
                }
                case "notNullOrElseGet" -> {
                    int notNullOrElseGetCount = 1;
                    var count = countByName.get(methodName);
                    ArchTestUtils.checkOrAddMethodNameViolationEvent(javaClass, events, methodName, notNullOrElseGetCount, count);
                }
                case "notNullOrElseThrow" -> {
                    int notNullOrElseThrowCount = 2;
                    var count = countByName.get(methodName);
                    ArchTestUtils.checkOrAddMethodNameViolationEvent(javaClass, events, methodName, notNullOrElseThrowCount, count);
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
