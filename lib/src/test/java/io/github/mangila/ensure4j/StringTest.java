package io.github.mangila.ensure4j;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class StringTest {

    @Test
    @DisplayName("Happy path notBlank")
    void notBlank() {
        assertThatCode(() -> {
            String s = Ensure.notBlank("test");
            assertThat(s).isEqualTo("test");
        })
                .doesNotThrowAnyException();
        assertThatCode(() -> Ensure.notBlank("test", "test message")).doesNotThrowAnyException();
        assertThatCode(() -> Ensure.notBlank("test", () -> new IllegalArgumentException("test message"))).doesNotThrowAnyException();
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

    @Test
    @DisplayName("Happy notBlankOrElse")
    void notBlankOrElse() {
        assertThat(Ensure.notBlankOrElse("test", "default")).isEqualTo("test");
        assertThat(Ensure.notBlankOrElse("", "default")).isEqualTo("default");
        assertThat(Ensure.notBlankOrElse("  ", "default")).isEqualTo("default");
    }

    @Test
    @DisplayName("Sad notBlankOrElse")
    void notBlankOrElse1() {
        assertThatThrownBy(() -> Ensure.notBlankOrElse(null, "default"))
                .isInstanceOf(EnsureException.class)
                .hasMessage("object must not be null");
    }

    @Test
    @DisplayName("Happy notBlankOrElseGet")
    void notBlankOrElseGet() {
        assertThat(Ensure.notBlankOrElseGet("test", () -> "default")).isEqualTo("test");
        assertThat(Ensure.notBlankOrElseGet("", () -> "default")).isEqualTo("default");
        assertThat(Ensure.notBlankOrElseGet("  ", () -> "default")).isEqualTo("default");
    }
}
