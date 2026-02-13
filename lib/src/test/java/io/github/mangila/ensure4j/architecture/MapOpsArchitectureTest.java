package io.github.mangila.ensure4j.architecture;

import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import io.github.mangila.ensure4j.ops.ArrayOps;
import io.github.mangila.ensure4j.ops.MapOps;
import org.junit.jupiter.api.Test;

public class MapOpsArchitectureTest {

    @Test
    void test() {
        var javaPackage = "io.github.mangila.ensure4j";
        ArchRuleDefinition.theClass(MapOps.class)
                .should()
                .beEnums()
                .andShould(new ArchCondition<>("have exactly one enum constant") {
                    @Override
                    public void check(JavaClass javaClass, ConditionEvents events) {
                        System.out.println(javaClass.getName());
                    }
                }).check(new ClassFileImporter().importPackages(javaPackage));

    }
}
