package io.github.mangila.ensure4j.architecture;

import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import io.github.mangila.ensure4j.ops.EnsureMapOps;
import org.junit.jupiter.api.Test;

public class EnsureMapOpsArchitectureTest {

    @Test
    void test() {
        ArchRuleDefinition.theClass(EnsureMapOps.class)
                .should()
                .beEnums()
                .andShould(new ArchCondition<>("have exactly one enum constant") {
                    @Override
                    public void check(JavaClass javaClass, ConditionEvents events) {
                        System.out.println(javaClass.getName());
                    }
                }).check(ArchTestUtils.ensureOpsClasses);

    }
}
