package io.github.mangila.ensure4j;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class NumbersTest {


    @Test
    @DisplayName("Happy path min(int)")
    void min() {
        assertThatCode(() -> Ensure.min(-1, 0))
                .doesNotThrowAnyException();
        assertThatCode(() -> Ensure.min(0, 0, "test message"))
                .doesNotThrowAnyException();
        assertThatCode(() -> Ensure.min(1, 2, () -> new IllegalArgumentException("test message")))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("Sad path min(int)")
    void min1() {
        assertThatThrownBy(() -> Ensure.min(1, -10))
                .isInstanceOf(EnsureException.class)
                .hasMessage("value must be greater than or equal to 1, but was -10");
        assertThatThrownBy(() -> Ensure.min(1, -10, "test value must be greater than or equal to 1"))
                .isInstanceOf(EnsureException.class)
                .hasMessage("test value must be greater than or equal to 1");
        assertThatThrownBy(() -> Ensure.min(1, -10, () -> new IllegalArgumentException("test value must be greater than or equal to 1")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("test value must be greater than or equal to 1");
    }

    @Test
    @DisplayName("Happy path max(int)")
    void max() {
        assertThatCode(() -> Ensure.max(100, -1))
                .doesNotThrowAnyException();
        assertThatCode(() -> Ensure.max(0, 0, "test message"))
                .doesNotThrowAnyException();
        assertThatCode(() -> Ensure.max(1, -1, () -> new IllegalArgumentException("test message")))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("Sad path max(int)")
    void max1() {
        assertThatThrownBy(() -> Ensure.max(100, 101))
                .isInstanceOf(EnsureException.class)
                .hasMessage("value must be less than or equal to 100, but was 101");
        assertThatThrownBy(() -> Ensure.max(100, 101, "test value must be less than or equal to 100"))
                .isInstanceOf(EnsureException.class)
                .hasMessage("test value must be less than or equal to 100");
        assertThatThrownBy(() -> Ensure.max(100, 101, () -> new IllegalArgumentException("test value must be less than or equal to 100")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("test value must be less than or equal to 100");
    }

}
