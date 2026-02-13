package io.github.mangila.ensure4j.architecture;

import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import io.github.mangila.ensure4j.ops.CollectionOps;
import org.junit.jupiter.api.Test;

public class CollectionOpsArchitectureTest {

    @Test
    void test() {
        ArchRuleDefinition.theClass(CollectionOps.class)
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
