package io.github.mangila.ensure4j.architecture;

import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import io.github.mangila.ensure4j.ArchTestUtils;
import io.github.mangila.ensure4j.ClazzTest;
import io.github.mangila.ensure4j.Ensure;
import io.github.mangila.ensure4j.PublicMethodArchitectureTest;
import org.junit.jupiter.api.Test;

public class EnsureArchitectureTest implements ClazzTest, PublicMethodArchitectureTest {

    @Override
    public Class<?> clazz() {
        return Ensure.class;
    }

    @Test
    void test() {
        ArchRuleDefinition.theClass(clazz())
                .should()
                .bePublic()
                .andShould(new ArchCondition<>("assert %s public methods".formatted(clazz().getSimpleName())) {
                    @Override
                    public void check(JavaClass javaClass, ConditionEvents events) {
                        assertPublicMethods(javaClass, events);
                    }
                })
                .check(ArchTestUtils.ENSURE_TOP_LEVEL);
    }

    @Override
    public void assertPublicMethods(JavaClass javaClass, ConditionEvents events) {
        var publicMethodNames = ArchTestUtils.getPublicMethodNames(javaClass);
        var countByName = ArchTestUtils.getCountByPublicMethodNames(javaClass);
        for (String methodName : publicMethodNames) {
            switch (methodName) {
                case "arrays" -> {
                    int arraysCount = 1;
                    var count = countByName.get(methodName);
                    ArchTestUtils.checkOrAddMethodNameViolationEvent(javaClass, events, methodName, arraysCount, count);
                }
                case "collections" -> {
                    int collectionsCount = 1;
                    var count = countByName.get(methodName);
                    ArchTestUtils.checkOrAddMethodNameViolationEvent(javaClass, events, methodName, collectionsCount, count);
                }
                case "isFalse" -> {
                    int isFalseCount = 3;
                    var count = countByName.get(methodName);
                    ArchTestUtils.checkOrAddMethodNameViolationEvent(javaClass, events, methodName, isFalseCount, count);
                }
                case "isTrue" -> {
                    int isTrueCount = 3;
                    var count = countByName.get(methodName);
                    ArchTestUtils.checkOrAddMethodNameViolationEvent(javaClass, events, methodName, isTrueCount, count);
                }
                case "maps" -> {
                    int mapsCount = 1;
                    var count = countByName.get(methodName);
                    ArchTestUtils.checkOrAddMethodNameViolationEvent(javaClass, events, methodName, mapsCount, count);
                }
                case "numbers" -> {
                    int numbersCount = 1;
                    var count = countByName.get(methodName);
                    ArchTestUtils.checkOrAddMethodNameViolationEvent(javaClass, events, methodName, numbersCount, count);
                }
                case "objects" -> {
                    int objectsCount = 1;
                    var count = countByName.get(methodName);
                    ArchTestUtils.checkOrAddMethodNameViolationEvent(javaClass, events, methodName, objectsCount, count);
                }
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
                // TODO: remove
                case "notNullOrElseThrow" -> {
                    int notNullOrElseThrowCount = 2;
                    var count = countByName.get(methodName);
                    ArchTestUtils.checkOrAddMethodNameViolationEvent(javaClass, events, methodName, notNullOrElseThrowCount, count);
                }
                case "strings" -> {
                    int stringsCount = 1;
                    var count = countByName.get(methodName);
                    ArchTestUtils.checkOrAddMethodNameViolationEvent(javaClass, events, methodName, stringsCount, count);
                }
                default -> ArchTestUtils.addMissingMethodViolationEvent(javaClass, events, methodName);
            }
        }
    }
}
