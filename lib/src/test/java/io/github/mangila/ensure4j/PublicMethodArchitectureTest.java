package io.github.mangila.ensure4j;

import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.lang.ConditionEvents;

public interface PublicMethodArchitectureTest {
  void assertPublicMethods(JavaClass javaClass, ConditionEvents events);
}
