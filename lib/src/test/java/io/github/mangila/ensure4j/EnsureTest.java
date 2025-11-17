package io.github.mangila.ensure4j;

import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaMember;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.util.function.Supplier;

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
    void testMethodSize() {
        String javaPackage = "io.github.mangila.ensure4j";
        ArchRuleDefinition.classes()
                .that()
                .areAssignableFrom(Ensure.class)
                .should(new ArchCondition<>("") {
                    @Override
                    public void check(JavaClass item, ConditionEvents events) {
                        item.getMethods()
                                .stream()
                                .map(JavaMember::getName)
                                .distinct()
                                .forEach(System.out::println);
                        int size = item.getMethods().size();
                        assertThat(size).isEqualTo(30);
                    }
                })
                .check(new ClassFileImporter().importPackages(javaPackage));
    }

}
