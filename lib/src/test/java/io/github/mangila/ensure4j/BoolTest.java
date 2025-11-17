package io.github.mangila.ensure4j;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BoolTest {

    @Test
    @DisplayName("Happy path isTrue()")
    void isTrue() {
        assertThatCode(() -> Ensure.isTrue(true))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("Sad path isTrue()")
    void isTrue1() {
        assertThatThrownBy(() -> Ensure.isTrue(false))
                .isInstanceOf(EnsureException.class)
                .hasMessage("boolean must be true");
        assertThatThrownBy(() -> Ensure.isTrue(false, "test"))
                .isInstanceOf(EnsureException.class)
                .hasMessage("test");
        assertThatThrownBy(() -> Ensure.isTrue(false, (Supplier<RuntimeException>) null))
                .isInstanceOf(EnsureException.class)
                .hasMessage("supplier was null");
        assertThatThrownBy(() -> Ensure.isTrue(false, () -> null))
                .isInstanceOf(EnsureException.class)
                .hasMessage("supplier was given a null value");
    }

    @Test
    @DisplayName("Happy path isFalse()")
    void isFalse() {
        assertThatCode(() -> Ensure.isFalse(false))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("Sad path isFalse()")
    void isFalse1() {
        assertThatThrownBy(() -> Ensure.isFalse(true))
                .isInstanceOf(EnsureException.class)
                .hasMessage("boolean must be false");
        assertThatThrownBy(() -> Ensure.isFalse(true, "test"))
                .isInstanceOf(EnsureException.class)
                .hasMessage("test");
        assertThatThrownBy(() -> Ensure.isFalse(true, (Supplier<RuntimeException>) null))
                .isInstanceOf(EnsureException.class)
                .hasMessage("supplier was null");
        assertThatThrownBy(() -> Ensure.isFalse(true, () -> null))
                .isInstanceOf(EnsureException.class)
                .hasMessage("supplier was given a null value");
    }

}
