package io.github.mangila.ensure4j.ops;

import io.github.mangila.ensure4j.EnsureException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class EnsureNumberOpsTest {

    private final EnsureNumberOps ops = EnsureNumberOps.INSTANCE;

    @Test
    void maxIntSuccess() {
        assertThat(ops.max(10, 5)).isEqualTo(5);
        assertThat(ops.max(10, 10)).isEqualTo(10);
    }

    @Test
    void maxIntFailure() {
        assertThatThrownBy(() -> ops.max(10, 11))
                .isInstanceOf(EnsureException.class)
                .hasMessage("value must be less than or equal to 10, but was 11");
    }

    @Test
    void maxLongSuccess() {
        assertThat(ops.max(10L, 5L)).isEqualTo(5L);
        assertThat(ops.max(10L, 10L)).isEqualTo(10L);
    }

    @Test
    void maxLongFailure() {
        assertThatThrownBy(() -> ops.max(10L, 11L))
                .isInstanceOf(EnsureException.class)
                .hasMessage("value must be less than or equal to 10, but was 11");
    }

    @Test
    void minIntSuccess() {
        assertThat(ops.min(5, 10)).isEqualTo(10);
        assertThat(ops.min(5, 5)).isEqualTo(5);
    }

    @Test
    void minIntFailure() {
        assertThatThrownBy(() -> ops.min(5, 4))
                .isInstanceOf(EnsureException.class)
                .hasMessage("value must be greater than or equal to 5, but was 4");
    }

    @Test
    void minLongSuccess() {
        assertThat(ops.min(5L, 10L)).isEqualTo(10L);
        assertThat(ops.min(5L, 5L)).isEqualTo(5L);
    }

    @Test
    void minLongFailure() {
        assertThatThrownBy(() -> ops.min(5L, 4L))
                .isInstanceOf(EnsureException.class)
                .hasMessage("value must be greater than or equal to 5, but was 4");
    }

    @Test
    void positiveIntSuccess() {
        assertThat(ops.positive(1)).isEqualTo(1);
    }

    @Test
    void positiveIntFailure() {
        assertThatThrownBy(() -> ops.positive(0))
                .isInstanceOf(EnsureException.class)
                .hasMessage("value must be positive - (0)");
    }

    @Test
    void positiveLongSuccess() {
        assertThat(ops.positive(1L)).isEqualTo(1L);
    }

    @Test
    void positiveLongFailure() {
        assertThatThrownBy(() -> ops.positive(0L))
                .isInstanceOf(EnsureException.class)
                .hasMessage("value must be positive - (0)");
    }

    @Test
    void negativeIntSuccess() {
        assertThat(ops.negative(-1)).isEqualTo(-1);
    }

    @Test
    void negativeIntFailure() {
        assertThatThrownBy(() -> ops.negative(0))
                .isInstanceOf(EnsureException.class)
                .hasMessage("value must be negative - (0)");
    }

    @Test
    void negativeLongSuccess() {
        assertThat(ops.negative(-1L)).isEqualTo(-1L);
    }

    @Test
    void negativeLongFailure() {
        assertThatThrownBy(() -> ops.negative(0L))
                .isInstanceOf(EnsureException.class)
                .hasMessage("value must be negative - (0)");
    }

    @Test
    void positiveWithZeroIntSuccess() {
        assertThat(ops.positiveWithZero(0)).isEqualTo(0);
        assertThat(ops.positiveWithZero(1)).isEqualTo(1);
    }

    @Test
    void positiveWithZeroIntFailure() {
        assertThatThrownBy(() -> ops.positiveWithZero(-1))
                .isInstanceOf(EnsureException.class)
                .hasMessage("value must be positive or zero - (-1)");
    }

    @Test
    void positiveWithZeroLongSuccess() {
        assertThat(ops.positiveWithZero(0L)).isEqualTo(0L);
        assertThat(ops.positiveWithZero(1L)).isEqualTo(1L);
    }

    @Test
    void positiveWithZeroLongFailure() {
        assertThatThrownBy(() -> ops.positiveWithZero(-1L))
                .isInstanceOf(EnsureException.class)
                .hasMessage("value must be positive or zero - (-1)");
    }

    @Test
    void negativeWithZeroIntSuccess() {
        assertThat(ops.negativeWithZero(0)).isEqualTo(0);
        assertThat(ops.negativeWithZero(-1)).isEqualTo(-1);
    }

    @Test
    void negativeWithZeroIntFailure() {
        assertThatThrownBy(() -> ops.negativeWithZero(1))
                .isInstanceOf(EnsureException.class)
                .hasMessage("value must be negative or zero - (1)");
    }

    @Test
    void negativeWithZeroLongSuccess() {
        assertThat(ops.negativeWithZero(0L)).isEqualTo(0L);
        assertThat(ops.negativeWithZero(-1L)).isEqualTo(-1L);
    }

    @Test
    void negativeWithZeroLongFailure() {
        assertThatThrownBy(() -> ops.negativeWithZero(1L))
                .isInstanceOf(EnsureException.class)
                .hasMessage("value must be negative or zero - (1)");
    }
}
