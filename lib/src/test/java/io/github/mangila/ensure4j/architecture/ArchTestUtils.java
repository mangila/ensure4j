package io.github.mangila.ensure4j.architecture;

import com.tngtech.archunit.core.domain.*;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.jspecify.annotations.NonNull;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ArchTestUtils {

    public static final JavaClasses ensureOpsClasses = new ClassFileImporter()
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

    public static @NonNull Map<String, Long> getCountByMethodName(JavaClass javaClass) {
        return getPublicMethodNames(javaClass)
                .stream()
                .collect(Collectors.groupingBy(
                        methodName -> methodName,
                        Collectors.counting()
                ));
    }
}
