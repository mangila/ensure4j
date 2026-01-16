package io.github.mangila.ensure4j;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class NumbersTest {


    @Test
    @DisplayName("Should pass when int value is greater than or equal to min")
    void min() {
        int n = Ensure.min(-1, 0);
        assertThat(n).isEqualTo(0);
        assertThat(Ensure.min(0, 0, "test message")).isEqualTo(0);
        assertThat(Ensure.min(1, 2, () -> new IllegalArgumentException("test message"))).isEqualTo(2);
    }

    @Test
    @DisplayName("Should throw exception when int value is less than min")
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
    @DisplayName("Should pass when int value is less than or equal to max")
    void max() {
        int n = Ensure.max(20, 5);
        assertThat(n).isEqualTo(5);
        assertThat(Ensure.max(0, 0, "test message")).isEqualTo(0);
        assertThat(Ensure.max(1, -1, () -> new IllegalArgumentException("test message"))).isEqualTo(-1);
    }

    @Test
    @DisplayName("Should throw exception when int value is greater than max")
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

    @Test
    @DisplayName("Should pass when long value is greater than or equal to min")
    void minLong() {
        long n = Ensure.min(-1L, 0L);
        assertThat(n).isEqualTo(0);
        assertThat(Ensure.min(0L, 0L, "test message")).isEqualTo(0);
        assertThat(Ensure.min(1L, 2L, () -> new IllegalArgumentException("test message"))).isEqualTo(2);
    }

    @Test
    @DisplayName("Should throw exception when long value is less than min")
    void minLong1() {
        assertThatThrownBy(() -> Ensure.min(1L, -10L))
                .isInstanceOf(EnsureException.class)
                .hasMessage("value must be greater than or equal to 1, but was -10");
        assertThatThrownBy(() -> Ensure.min(1L, -10L, "test value must be greater than or equal to 1"))
                .isInstanceOf(EnsureException.class)
                .hasMessage("test value must be greater than or equal to 1");
        assertThatThrownBy(() -> Ensure.min(1L, -10L, () -> new IllegalArgumentException("test value must be greater than or equal to 1")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("test value must be greater than or equal to 1");
    }

    @Test
    @DisplayName("Should pass when long value is less than or equal to max")
    void maxLong() {
        long n = Ensure.max(20L, 5L);
        assertThat(n).isEqualTo(5);
        assertThat(Ensure.max(0L, 0L, "test message")).isEqualTo(0);
        assertThat(Ensure.max(1L, -1L, () -> new IllegalArgumentException("test message"))).isEqualTo(-1);
    }

    @Test
    @DisplayName("Should throw exception when long value is greater than max")
    void maxLong1() {
        assertThatThrownBy(() -> Ensure.max(100L, 101L))
                .isInstanceOf(EnsureException.class)
                .hasMessage("value must be less than or equal to 100, but was 101");
        assertThatThrownBy(() -> Ensure.max(100L, 101L, "test value must be less than or equal to 100"))
                .isInstanceOf(EnsureException.class)
                .hasMessage("test value must be less than or equal to 100");
        assertThatThrownBy(() -> Ensure.max(100L, 101L, () -> new IllegalArgumentException("test value must be less than or equal to 100")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("test value must be less than or equal to 100");
    }

}
