package io.github.mangila.ensure4j;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class ArrayTest {

    @Test
    @DisplayName("Should pass when array is not empty")
    void notEmpty() {
        var array = new String[]{"test"};
        assertThat(Ensure.notEmpty(array)).isSameAs(array);
        assertThat(Ensure.notEmpty(array, "test message")).isSameAs(array);
        assertThat(Ensure.notEmpty(array, () -> new IllegalArgumentException("test message"))).isSameAs(array);
    }

    @Test
    @DisplayName("Should throw exception when array is empty or null")
    void notEmpty1() {
        var array = new String[]{};
        assertThatThrownBy(() -> Ensure.notEmpty(array))
                .isInstanceOf(EnsureException.class)
                .hasMessage("array must not be empty");
        Object[] array1 = null;
        assertThatThrownBy(() -> Ensure.notEmpty(array1, "test message"))
                .isInstanceOf(EnsureException.class)
                .hasMessage("test message");
        assertThatThrownBy(() -> Ensure.notEmpty(array1, () -> new IllegalArgumentException("test message")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("test message");
    }

}
