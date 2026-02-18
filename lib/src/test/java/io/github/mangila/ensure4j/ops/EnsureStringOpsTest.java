package io.github.mangila.ensure4j.ops;

import io.github.mangila.ensure4j.EnsureException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class EnsureStringOpsTest {

    private final EnsureStringOps ops = EnsureStringOps.INSTANCE;

    @Test
    void notBlankSuccess() {
        String value = "test";
        String result = ops.notBlank(value);
        assertThat(result).isEqualTo(value);
    }

    @Test
    void notBlankFailure() {
        assertThatThrownBy(() -> ops.notBlank(" "))
                .isInstanceOf(EnsureException.class)
                .hasMessage("string must not be blank");
        assertThatThrownBy(() -> ops.notBlank(null))
                .isInstanceOf(EnsureException.class)
                .hasMessage("string must not be blank");
    }

    @Test
    void notBlankCustomMessage() {
        assertThatThrownBy(() -> ops.notBlank(" ", "custom message"))
                .isInstanceOf(EnsureException.class)
                .hasMessage("custom message");
    }

    @Test
    void notBlankCustomSupplier() {
        assertThatThrownBy(() -> ops.notBlank(" ", () -> new RuntimeException("custom exception")))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("custom exception");
    }

    @Test
    void notBlankOrElseSuccess() {
        assertThat(ops.notBlankOrElse("test", "default")).isEqualTo("test");
    }

    @Test
    void notBlankOrElseFallback() {
        assertThat(ops.notBlankOrElse("", "default")).isEqualTo("default");
        assertThat(ops.notBlankOrElse("  ", "default")).isEqualTo("default");
        assertThat(ops.notBlankOrElse(null, "default")).isEqualTo("default");
    }

    @Test
    void notBlankOrElseGetSuccess() {
        assertThat(ops.notBlankOrElseGet("test", () -> "default")).isEqualTo("test");
    }

    @Test
    void notBlankOrElseGetFallback() {
        assertThat(ops.notBlankOrElseGet(null, () -> "default")).isEqualTo("default");
        assertThat(ops.notBlankOrElseGet("", () -> "default")).isEqualTo("default");
        assertThat(ops.notBlankOrElseGet("  ", () -> "default")).isEqualTo("default");
    }

    @Test
    void notBlankOrElseGetNullSupplier() {
        assertThatThrownBy(() -> ops.notBlankOrElseGet(null, null))
                .isInstanceOf(EnsureException.class)
                .hasMessage("supplier was null");
    }

    @Test
    void minLengthSuccess() {
        String value = "abcd";
        assertThat(ops.minLength(3, value)).isEqualTo(value);
        assertThat(ops.minLength(4, value)).isEqualTo(value);
    }

    @Test
    void minLengthFailure() {
        assertThatThrownBy(() -> ops.minLength(5, "abcd"))
                .isInstanceOf(EnsureException.class)
                .hasMessage("string length must be at least 5");
        assertThatThrownBy(() -> ops.minLength(1, null))
                .isInstanceOf(EnsureException.class)
                .hasMessage("string length must be at least 1");
    }

    @Test
    void minLengthCustomMessage() {
        assertThatThrownBy(() -> ops.minLength(5, "abcd", "custom message"))
                .isInstanceOf(EnsureException.class)
                .hasMessage("custom message");
    }

    @Test
    void minLengthCustomSupplier() {
        assertThatThrownBy(() -> ops.minLength(5, "abcd", () -> new RuntimeException("custom exception")))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("custom exception");
    }

    @Test
    void maxLengthSuccess() {
        String value = "abcd";
        assertThat(ops.maxLength(5, value)).isEqualTo(value);
        assertThat(ops.maxLength(4, value)).isEqualTo(value);
    }

    @Test
    void maxLengthFailure() {
        assertThatThrownBy(() -> ops.maxLength(3, "abcd"))
                .isInstanceOf(EnsureException.class)
                .hasMessage("string length must be at most 3");
        assertThatThrownBy(() -> ops.maxLength(3, null))
                .isInstanceOf(EnsureException.class)
                .hasMessage("string length must be at most 3");
    }

    @Test
    void maxLengthCustomMessage() {
        assertThatThrownBy(() -> ops.maxLength(3, "abcd", "custom message"))
                .isInstanceOf(EnsureException.class)
                .hasMessage("custom message");
    }

    @Test
    void maxLengthCustomSupplier() {
        assertThatThrownBy(() -> ops.maxLength(3, "abcd", () -> new RuntimeException("custom exception")))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("custom exception");
    }

    @Test
    void startsWithSuccess() {
        String value = "hello world";
        assertThat(ops.startsWith("hello", value)).isEqualTo(value);
    }

    @Test
    void startsWithFailure() {
        assertThatThrownBy(() -> ops.startsWith("world", "hello world"))
                .isInstanceOf(EnsureException.class)
                .hasMessage("string must start with world");
        assertThatThrownBy(() -> ops.startsWith("hello", null))
                .isInstanceOf(EnsureException.class)
                .hasMessage("string must start with hello");
        assertThatThrownBy(() -> ops.startsWith(null, "hello world"))
                .isInstanceOf(EnsureException.class)
                .hasMessage("prefix must not be null");
    }

    @Test
    void startsWithCustomMessage() {
        assertThatThrownBy(() -> ops.startsWith("world", "hello world", "custom message"))
                .isInstanceOf(EnsureException.class)
                .hasMessage("custom message");
    }

    @Test
    void startsWithCustomSupplier() {
        assertThatThrownBy(() -> ops.startsWith("world", "hello world", () -> new RuntimeException("custom exception")))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("custom exception");
    }

    @Test
    void endsWithSuccess() {
        String value = "hello world";
        assertThat(ops.endsWith("world", value)).isEqualTo(value);
    }

    @Test
    void endsWithFailure() {
        assertThatThrownBy(() -> ops.endsWith("hello", "hello world"))
                .isInstanceOf(EnsureException.class)
                .hasMessage("string must end with hello");
        assertThatThrownBy(() -> ops.endsWith("world", null))
                .isInstanceOf(EnsureException.class)
                .hasMessage("string must end with world");
        assertThatThrownBy(() -> ops.endsWith(null, "hello world"))
                .isInstanceOf(EnsureException.class)
                .hasMessage("suffix must not be null");
    }

    @Test
    void endsWithCustomMessage() {
        assertThatThrownBy(() -> ops.endsWith("hello", "hello world", "custom message"))
                .isInstanceOf(EnsureException.class)
                .hasMessage("custom message");
    }

    @Test
    void endsWithCustomSupplier() {
        assertThatThrownBy(() -> ops.endsWith("hello", "hello world", () -> new RuntimeException("custom exception")))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("custom exception");
    }
}
