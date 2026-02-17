package io.github.mangila.ensure4j.ops.architecture;

import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import io.github.mangila.ensure4j.ArchTestUtils;
import io.github.mangila.ensure4j.ClazzTest;
import io.github.mangila.ensure4j.PublicMethodArchitectureTest;
import io.github.mangila.ensure4j.ops.EnsureObjectOps;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.github.mangila.ensure4j.ArchTestUtils.getCountByPublicMethodNames;
import static io.github.mangila.ensure4j.ArchTestUtils.getPublicMethodNames;

public class EnsureObjectOpsArchitectureTest implements ClazzTest, PublicMethodArchitectureTest {

    @Override
    public Class<?> clazz() {
        return EnsureObjectOps.class;
    }

    @Test
    void test() {
        ArchRuleDefinition.theClass(clazz())
                .should()
                .beEnums()
                .andShould(new ArchCondition<>("assert %s public methods named".formatted(clazz().getSimpleName())) {
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
                case "notEmpty" -> {
                    int notEmptyCount = 3;
                    var count = countByName.get(methodName);
                    ArchTestUtils.checkOrAddMethodNameViolationEvent(javaClass, events, methodName, notEmptyCount, count);
                }
                case "isEquals" -> {
                    int isEqualsCount = 6;
                    var count = countByName.get(methodName);
                    ArchTestUtils.checkOrAddMethodNameViolationEvent(javaClass, events, methodName, isEqualsCount, count);
                }
                case "isInstanceOf" -> {
                    int isInstanceOfCount = 3;
                    var count = countByName.get(methodName);
                    ArchTestUtils.checkOrAddMethodNameViolationEvent(javaClass, events, methodName, isInstanceOfCount, count);
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
