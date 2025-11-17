package io.github.mangila.ensure4j;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class StringTest {

    @Test
    @DisplayName("Happy path notBlank")
    void notBlank() {
        assertThatCode(() -> Ensure.notBlank("test")).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("Sad path notBlank")
    void notBlank1() {
        assertThatThrownBy(() -> Ensure.notBlank(null))
                .isInstanceOf(EnsureException.class)
                .hasMessage("string must not be blank");
        assertThatThrownBy(() -> Ensure.notBlank(""))
                .isInstanceOf(EnsureException.class)
                .hasMessage("string must not be blank");
        assertThatThrownBy(() -> Ensure.notBlank("", "test value must not be blank"))
                .isInstanceOf(EnsureException.class)
                .hasMessage("test value must not be blank");
        assertThatThrownBy(() -> Ensure.notBlank("", () -> new IllegalArgumentException("test value must not be blank")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("test value must not be blank");
    }

}
