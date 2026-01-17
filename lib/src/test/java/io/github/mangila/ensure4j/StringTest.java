package io.github.mangila.ensure4j;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
        assertThat(Ensure.notBlankOrElse(null, "default")).isEqualTo("default");
    }

    @Test
    @DisplayName("Should return string when not blank (OrElseGet)")
    void notBlankOrElseGet() {
        assertThat(Ensure.notBlankOrElseGet("test", () -> "default")).isEqualTo("test");
        assertThat(Ensure.notBlankOrElseGet("", () -> "default")).isEqualTo("default");
        assertThat(Ensure.notBlankOrElseGet("  ", () -> "default")).isEqualTo("default");
        assertThat(Ensure.notBlankOrElseGet(null, () -> "default")).isEqualTo("default");
    }

    @Test
    @DisplayName("Should pass when string length is at least min")
    void minLength() {
        assertThat(Ensure.minLength(3, "abc")).isEqualTo("abc");
        assertThat(Ensure.minLength(3, "abcd")).isEqualTo("abcd");
    }

    @Test
    @DisplayName("Should throw exception when string length is less than min")
    void minLength1() {
        assertThatThrownBy(() -> Ensure.minLength(3, "ab"))
                .isInstanceOf(EnsureException.class)
                .hasMessage("string length must be at least 3");
        assertThatThrownBy(() -> Ensure.minLength(3, null))
                .isInstanceOf(EnsureException.class)
                .hasMessage("string length must be at least 3");
    }

    @Test
    @DisplayName("Should pass when string length is at most max")
    void maxLength() {
        assertThat(Ensure.maxLength(3, "abc")).isEqualTo("abc");
        assertThat(Ensure.maxLength(3, "ab")).isEqualTo("ab");
    }

    @Test
    @DisplayName("Should throw exception when string length is more than max")
    void maxLength1() {
        assertThatThrownBy(() -> Ensure.maxLength(3, "abcd"))
                .isInstanceOf(EnsureException.class)
                .hasMessage("string length must be at most 3");
        assertThatThrownBy(() -> Ensure.maxLength(3, null))
                .isInstanceOf(EnsureException.class)
                .hasMessage("string length must be at most 3");
    }
}
