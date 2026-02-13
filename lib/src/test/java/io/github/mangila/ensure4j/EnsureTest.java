package io.github.mangila.ensure4j;

import io.github.mangila.ensure4j.ops.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
    @DisplayName("Should throw exception when supplier is null or returns null")
    void testSupplier() {
        assertThatThrownBy(() -> Ensure.notNull(null, (Supplier<RuntimeException>) null))
                .isInstanceOf(EnsureException.class)
                .hasMessage("supplier was null");
        assertThatThrownBy(() -> Ensure.notNull(null, () -> null))
                .isInstanceOf(EnsureException.class)
                .hasMessage("supplier was given a null value");
    }

    @Test
    @DisplayName("Should test notNull variants")
    void testNotNull() {
        String value = "test";
        assertThat(Ensure.notNull(value)).isEqualTo(value);
        assertThat(Ensure.notNull(value, "message")).isEqualTo(value);
        assertThat(Ensure.notNull(value, () -> new RuntimeException("custom"))).isEqualTo(value);

        assertThatThrownBy(() -> Ensure.notNull(null))
                .isInstanceOf(EnsureException.class)
                .hasMessage("object must not be null");
        assertThatThrownBy(() -> Ensure.notNull(null, "custom message"))
                .isInstanceOf(EnsureException.class)
                .hasMessage("custom message");
        assertThatThrownBy(() -> Ensure.notNull(null, () -> new RuntimeException("custom exception")))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("custom exception");
    }

    @Test
    @DisplayName("Should test notNullOrElse and notNullOrElseGet")
    void testNotNullOrElse() {
        String value = "test";
        String defaultValue = "default";
        assertThat(Ensure.notNullOrElse(value, defaultValue)).isEqualTo(value);
        assertThat(Ensure.notNullOrElse(null, defaultValue)).isEqualTo(defaultValue);

        assertThat(Ensure.notNullOrElseGet(value, () -> defaultValue)).isEqualTo(value);
        assertThat(Ensure.notNullOrElseGet(null, () -> defaultValue)).isEqualTo(defaultValue);

        assertThatThrownBy(() -> Ensure.notNullOrElseGet(null, null))
                .isInstanceOf(EnsureException.class)
                .hasMessage("supplier was null");
    }

    @Test
    @DisplayName("Should test notNullOrElseThrow variants")
    void testNotNullOrElseThrow() {
        String value = "test";
        assertThat(Ensure.notNullOrElseThrow(value)).isEqualTo(value);
        assertThat(Ensure.notNullOrElseThrow(value, () -> new RuntimeException("custom"))).isEqualTo(value);

        assertThatThrownBy(() -> Ensure.notNullOrElseThrow(null))
                .isInstanceOf(EnsureException.class)
                .hasMessage("object must not be null");
        assertThatThrownBy(() -> Ensure.notNullOrElseThrow(null, () -> new RuntimeException("custom exception")))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("custom exception");
    }

    @Test
    @DisplayName("Should test isTrue variants")
    void testIsTrue() {
        Ensure.isTrue(true);
        Ensure.isTrue(true, "message");
        Ensure.isTrue(true, () -> new RuntimeException("custom"));

        assertThatThrownBy(() -> Ensure.isTrue(false))
                .isInstanceOf(EnsureException.class)
                .hasMessage("boolean must be true");
        assertThatThrownBy(() -> Ensure.isTrue(false, "custom message"))
                .isInstanceOf(EnsureException.class)
                .hasMessage("custom message");
        assertThatThrownBy(() -> Ensure.isTrue(false, () -> new RuntimeException("custom exception")))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("custom exception");
    }

    @Test
    @DisplayName("Should test isFalse variants")
    void testIsFalse() {
        Ensure.isFalse(false);
        Ensure.isFalse(false, "message");
        Ensure.isFalse(false, () -> new RuntimeException("custom"));

        assertThatThrownBy(() -> Ensure.isFalse(true))
                .isInstanceOf(EnsureException.class)
                .hasMessage("boolean must be false");
        assertThatThrownBy(() -> Ensure.isFalse(true, "custom message"))
                .isInstanceOf(EnsureException.class)
                .hasMessage("custom message");
        assertThatThrownBy(() -> Ensure.isFalse(true, () -> new RuntimeException("custom exception")))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("custom exception");
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

}
