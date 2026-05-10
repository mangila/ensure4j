package io.github.mangila.ensure4j;

import com.tngtech.archunit.core.domain.*;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import java.util.List;
import org.jspecify.annotations.NonNull;

public class ArchTestUtils {

  public static final JavaClasses ENSURE_TOP_LEVEL =
      new ClassFileImporter()
          .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
          .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_PACKAGE_INFOS)
          .importPackages("io.github.mangila.ensure4j");

  public static final JavaClasses ENSURE_OPS_CLASSES =
      new ClassFileImporter()
          .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
          .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_PACKAGE_INFOS)
          .importPackages("io.github.mangila.ensure4j.ops");

  public static long getPublicMethodCount(JavaClass javaClass) {
    return javaClass.getMethods().stream()
        .filter(
            javaMethod ->
                javaMethod.getModifiers().stream()
                    .anyMatch(javaModifier -> javaModifier == JavaModifier.PUBLIC))
        .count();
  }

  public static @NonNull List<JavaMethod> getPublicMethods(JavaClass javaClass) {
    return javaClass.getMethods().stream()
        .filter(
            javaMethod ->
                javaMethod.getModifiers().stream()
                    .anyMatch(javaModifier -> javaModifier == JavaModifier.PUBLIC))
        .toList();
  }

  public static @NonNull List<String> getPublicMethodNames(JavaClass javaClass) {
    return getPublicMethods(javaClass).stream().map(JavaMember::getName).toList();
  }
}
