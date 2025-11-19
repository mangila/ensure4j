package io.github.mangila.ensure4j;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ArrayTest {

    @Test
    @DisplayName("Happy path notEmpty([])")
    void notEmpty() {
        var array = new String[]{"test"};
        assertThatCode(() -> Ensure.notEmpty(array)).doesNotThrowAnyException();
        assertThatCode(() -> Ensure.notEmpty(array, "test message")).doesNotThrowAnyException();
        assertThatCode(() -> Ensure.notEmpty(array, () -> new IllegalArgumentException("test message"))).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("Sad path notEmpty([])")
    void notEmpty1() {
        var array = new String[]{};
        assertThatThrownBy(() -> Ensure.notEmpty(array))
                .isInstanceOf(EnsureException.class)
                .hasMessage("array must not be empty");
        Object[] array1 = null;
        assertThatThrownBy(() -> Ensure.notEmpty(array1))
                .isInstanceOf(EnsureException.class)
                .hasMessage("array must not be empty");
    }

}
