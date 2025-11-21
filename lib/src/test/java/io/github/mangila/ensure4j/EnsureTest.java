package io.github.mangila.ensure4j;

import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaMember;
import com.tngtech.archunit.core.domain.JavaModifier;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class EnsureTest {

    @Test
    void test() throws Exception {
        Constructor<?> constructor = Ensure.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        assertThatThrownBy(constructor::newInstance)
                .hasCauseInstanceOf(IllegalStateException.class)
                .hasRootCauseMessage("Utility class");
    }

    @Test
    void testSupplier() {
        assertThatThrownBy(() -> Ensure.notNull(null, (Supplier<RuntimeException>) null))
                .isInstanceOf(EnsureException.class)
                .hasMessage("supplier was null");
        assertThatThrownBy(() -> Ensure.notNull(null, () -> null))
                .isInstanceOf(EnsureException.class)
                .hasMessage("supplier was given a null value");
    }

    @Test
    void archTest() {
        String javaPackage = "io.github.mangila.ensure4j";
        ArchRuleDefinition.classes()
                .that()
                .areAssignableFrom(Ensure.class)
                .should(new ArchCondition<>("") {
                    @Override
                    public void check(JavaClass item, ConditionEvents events) {
                        var publicMethods = item.getMethods()
                                .stream()
                                .filter(javaMethod -> {
                                    return javaMethod.getModifiers()
                                            .stream()
                                            .anyMatch(javaModifier -> javaModifier == JavaModifier.PUBLIC);
                                })
                                .map(JavaMember::getName)
                                .toList();
                        assertPublicMethods(publicMethods);
                        int totalMethods = item.getMethods().size();
                        assertThat(totalMethods).isEqualTo(42);
                    }

                    /**
                     * Sanity test to guard from slippery slopes
                     */
                    private void assertPublicMethods(List<String> publicMethodNames) {
                        int publicMethodsCount = publicMethodNames.size();
                        assertThat(publicMethodsCount)
                                .isEqualTo(40);
                        Map<String, Long> counts = publicMethodNames.stream()
                                .collect(Collectors.groupingBy(
                                        methodName -> methodName,
                                        Collectors.counting()
                                ));
                        for (String methodName : publicMethodNames) {
                            switch (methodName) {
                                case "notNull" -> assertThat(counts.get(methodName)).isEqualTo(3L);
                                case "notNullOrElse" -> assertThat(counts.get(methodName)).isEqualTo(1L);
                                case "notNullOrElseGet" -> assertThat(counts.get(methodName)).isEqualTo(1L);
                                case "notNullOrElseThrow" -> assertThat(counts.get(methodName)).isEqualTo(2L);
                                case "isTrue" -> assertThat(counts.get(methodName)).isEqualTo(3L);
                                case "isFalse" -> assertThat(counts.get(methodName)).isEqualTo(3L);
                                case "max" -> assertThat(counts.get(methodName)).isEqualTo(3L);
                                case "min" -> assertThat(counts.get(methodName)).isEqualTo(3L);
                                case "notBlank" -> assertThat(counts.get(methodName)).isEqualTo(3L);
                                case "notBlankOrElse" -> assertThat(counts.get(methodName)).isEqualTo(1L);
                                case "notEmpty" -> assertThat(counts.get(methodName)).isEqualTo(9L);
                                case "notContainsNull" -> assertThat(counts.get(methodName)).isEqualTo(3L);
                                case "isEquals" -> assertThat(counts.get(methodName)).isEqualTo(3L);
                                case "isInstanceOf" -> assertThat(counts.get(methodName)).isEqualTo(3L);
                                default -> throw new IllegalStateException("Unexpected value: " + methodName);
                            }
                        }
                    }
                })
                .check(new ClassFileImporter().importPackages(javaPackage));
    }

}
