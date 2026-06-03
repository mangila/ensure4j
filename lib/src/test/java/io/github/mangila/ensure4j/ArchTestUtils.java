package io.github.mangila.ensure4j;

import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.domain.JavaMember;
import com.tngtech.archunit.core.domain.JavaMethod;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import java.util.List;

public final class ArchTestUtils {

  private ArchTestUtils() {
    throw new AssertionError("No instances for you!");
  }

  public static final JavaClasses ENSURE_PACKAGE =
      new ClassFileImporter()
          .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
          .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_PACKAGE_INFOS)
          .importPackages("io.github.mangila.ensure4j");

  public static List<JavaMethod> getMethods(JavaClass javaClass) {
    return javaClass.getMethods().stream().toList();
  }

  public static List<String> getMethodNames(JavaClass javaClass) {
    return getMethods(javaClass).stream().map(JavaMember::getName).toList();
  }

  public static long getMethodCount(JavaClass javaClass) {
    return getMethods(javaClass).size();
  }
}
