package io.github.mangila.ensure4j;

import io.github.mangila.ensure4j.ops.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.assertj.core.api.Assertions.*;

public class EnsureTest {

    @Test
    @DisplayName("Should pass when utility class is instantiated via reflection and throw IllegalStateException")
    void shouldThrowWhenReflection() throws Exception {
        Constructor<?> constructor = Ensure.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        assertThatThrownBy(constructor::newInstance)
                .isInstanceOf(InvocationTargetException.class)
                .hasCauseInstanceOf(IllegalStateException.class)
                .hasRootCauseMessage("Utility class");
    }

    @Test
    @DisplayName("Should test ops accessors")
    void testOpsAccessors() {
        assertThat(Ensure.arrays()).isInstanceOf(EnsureArrayOps.class);
        assertThat(Ensure.collections()).isInstanceOf(EnsureCollectionOps.class);
        assertThat(Ensure.maps()).isInstanceOf(EnsureMapOps.class);
        assertThat(Ensure.numbers()).isInstanceOf(EnsureNumberOps.class);
        assertThat(Ensure.objects()).isInstanceOf(EnsureObjectOps.class);
        assertThat(Ensure.strings()).isInstanceOf(EnsureStringOps.class);
    }

    @Test
    void invokeDelegateMethods() {
        assertThatCode(() -> {
            Ensure.isTrue(true);
            Ensure.isTrue(true, "message");
            Ensure.isTrue(true, () -> new RuntimeException("custom"));
        })
                .doesNotThrowAnyException();
        assertThatCode(() -> {
            Ensure.isFalse(false);
            Ensure.isFalse(false, "message");
            Ensure.isFalse(false, () -> new RuntimeException("custom"));
        })
                .doesNotThrowAnyException();
        assertThatCode(() -> {
            Ensure.notNull("str");
            Ensure.notNull("str", "message");
            Ensure.notNull("str", () -> new RuntimeException("custom"));
        })
                .doesNotThrowAnyException();
        assertThatCode(() -> Ensure.notNullOrElse("str", "fallback"))
                .doesNotThrowAnyException();
        assertThatCode(() -> Ensure.notNullOrElseGet("str", () -> "fallback"))
                .doesNotThrowAnyException();
        assertThatCode(() -> {
            Ensure.notNullOrElseThrow("str");
            Ensure.notNullOrElseThrow("str", () -> new RuntimeException("custom"));
        })
                .doesNotThrowAnyException();
    }

}
