package io.github.mangila.ensure4j.architecture;

import com.tngtech.archunit.core.domain.*;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.SimpleConditionEvent;
import org.jspecify.annotations.NonNull;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class ArchTestUtils {

    public static final JavaClasses ENSURE_TOP_LEVEL = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_PACKAGE_INFOS)
            .importPackages("io.github.mangila.ensure4j");

    public static final JavaClasses ENSURE_OPS_CLASSES = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_PACKAGE_INFOS)
            .importPackages("io.github.mangila.ensure4j.ops");

    public static @NonNull List<JavaMethod> getPublicMethods(JavaClass javaClass) {
        return javaClass.getMethods()
                .stream()
                .filter(javaMethod -> javaMethod.getModifiers()
                        .stream()
                        .anyMatch(javaModifier -> javaModifier == JavaModifier.PUBLIC))
                .toList();
    }

    public static @NonNull List<String> getPublicMethodNames(JavaClass javaClass) {
        return getPublicMethods(javaClass)
                .stream()
                .map(JavaMember::getName)
                .toList();
    }

    public static @NonNull Map<String, Long> getCountByPublicMethodNames(JavaClass javaClass) {
        return getPublicMethodNames(javaClass)
                .stream()
                .collect(Collectors.groupingBy(
                        methodName -> methodName,
                        Collectors.counting()
                ));
    }

    public static void checkOrAddMethodNameViolationEvent(JavaClass javaClass, ConditionEvents events, String methodName, int expectedCount, Long actualCount) {
        assertThat(actualCount)
                .as("method name %s has null value on actualCount".formatted(methodName))
                .isNotNull();
        if (actualCount != expectedCount) {
            events.add(SimpleConditionEvent.violated(javaClass, "method name %s was expected be called %s times but was %s".formatted(methodName, expectedCount, actualCount)));
        }
    }

    public static void addMissingMethodViolationEvent(JavaClass javaClass, ConditionEvents events, String methodName) {
        events.add(SimpleConditionEvent.violated(javaClass, "missing method name %s".formatted(methodName)));
    }
}
