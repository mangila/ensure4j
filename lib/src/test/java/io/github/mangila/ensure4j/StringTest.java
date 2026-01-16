package io.github.mangila.ensure4j;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class StringTest {

    @Test
    @DisplayName("Should pass when string is not blank")
    void notBlank() {
        String test = "test";
        assertThat(Ensure.notBlank(test)).isSameAs(test);
        assertThat(Ensure.notBlank(test, "test message")).isSameAs(test);
        assertThat(Ensure.notBlank(test, () -> new IllegalArgumentException("test message"))).isSameAs(test);
    }

    @Test
    @DisplayName("Should throw exception when string is blank")
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
    @DisplayName("Should return string when not blank (OrElse)")
    void notBlankOrElse() {
        assertThat(Ensure.notBlankOrElse("test", "default")).isEqualTo("test");
        assertThat(Ensure.notBlankOrElse("", "default")).isEqualTo("default");
        assertThat(Ensure.notBlankOrElse("  ", "default")).isEqualTo("default");
    }

    @Test
    @DisplayName("Should throw exception when null (OrElse)")
    void notBlankOrElse1() {
        assertThatThrownBy(() -> Ensure.notBlankOrElse(null, "default"))
                .isInstanceOf(EnsureException.class)
                .hasMessage("object must not be null");
    }

    @Test
    @DisplayName("Should return string when not blank (OrElseGet)")
    void notBlankOrElseGet() {
        assertThat(Ensure.notBlankOrElseGet("test", () -> "default")).isEqualTo("test");
        assertThat(Ensure.notBlankOrElseGet("", () -> "default")).isEqualTo("default");
        assertThat(Ensure.notBlankOrElseGet("  ", () -> "default")).isEqualTo("default");
    }
}
