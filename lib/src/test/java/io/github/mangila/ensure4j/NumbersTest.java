package io.github.mangila.ensure4j;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class NumbersTest {


    @Test
    @DisplayName("Happy path min()")
    void min() {
        assertThatCode(() -> Ensure.min(-1, 0))
                .doesNotThrowAnyException();
        assertThatCode(() -> Ensure.min(0, 0))
                .doesNotThrowAnyException();
        assertThatCode(() -> Ensure.min(1, 2))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("Sad path min()")
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

}
