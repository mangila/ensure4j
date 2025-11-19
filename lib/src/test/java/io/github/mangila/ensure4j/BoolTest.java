package io.github.mangila.ensure4j;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BoolTest {

    @Test
    @DisplayName("Happy path isTrue()")
    void isTrue() {
        assertThatCode(() -> Ensure.isTrue(true))
                .doesNotThrowAnyException();
        assertThatCode(() -> Ensure.isTrue(true, "test message"))
                .doesNotThrowAnyException();
        assertThatCode(() -> Ensure.isTrue(true, () -> new IllegalArgumentException("test message")))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("Sad path isTrue()")
    void isTrue1() {
        assertThatThrownBy(() -> Ensure.isTrue(false))
                .isInstanceOf(EnsureException.class)
                .hasMessage("boolean must be true");
        assertThatThrownBy(() -> Ensure.isTrue(false, "test message"))
                .isInstanceOf(EnsureException.class)
                .hasMessage("test message");
        assertThatThrownBy(() -> Ensure.isTrue(false, () -> new IllegalArgumentException("test message")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("test message");
    }

    @Test
    @DisplayName("Happy path isFalse()")
    void isFalse() {
        assertThatCode(() -> Ensure.isFalse(false))
                .doesNotThrowAnyException();
        assertThatCode(() -> Ensure.isFalse(false, "test message"))
                .doesNotThrowAnyException();
        assertThatCode(() -> Ensure.isFalse(false, () -> new IllegalArgumentException("test message")))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("Sad path isFalse()")
    void isFalse1() {
        assertThatThrownBy(() -> Ensure.isFalse(true))
                .isInstanceOf(EnsureException.class)
                .hasMessage("boolean must be false");
        assertThatThrownBy(() -> Ensure.isFalse(true, "test message"))
                .isInstanceOf(EnsureException.class)
                .hasMessage("test message");
        assertThatThrownBy(() -> Ensure.isFalse(true, () -> new IllegalArgumentException("test message")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("test message");
    }

}
