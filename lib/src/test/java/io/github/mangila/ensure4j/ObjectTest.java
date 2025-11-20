package io.github.mangila.ensure4j;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.BigInteger;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ObjectTest {

    @Test
    @DisplayName("Happy path equals(Object)")
    void equals() {
        assertThatCode(() -> Ensure.equals(new BigDecimal("1"), new BigDecimal("1"))).doesNotThrowAnyException();
        assertThatCode(() -> Ensure.equals(1, 1, "test message")).doesNotThrowAnyException();
        assertThatCode(() -> Ensure.equals(1, 1, () -> new IllegalArgumentException("test message"))).doesNotThrowAnyException();
        // REMINDME: idk... ok?
        assertThatCode(() -> Ensure.equals(null, null)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("Sad path equals(Object)")
    void equals1() {
        assertThatThrownBy(() -> Ensure.equals(new BigDecimal("1"), new BigInteger("1")))
                .isInstanceOf(EnsureException.class)
                .hasMessage("objects must be equal");
        assertThatThrownBy(() -> Ensure.equals(new BigDecimal("1"), new BigInteger("1"), "test objects not equal"))
                .isInstanceOf(EnsureException.class)
                .hasMessage("test objects not equal");
        assertThatThrownBy(() -> Ensure.equals(new BigDecimal("1"), new BigInteger("1"), () -> new IllegalArgumentException("test objects not equal")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("test objects not equal");
        assertThatThrownBy(() -> Ensure.equals(null, new BigInteger("1"), () -> new IllegalArgumentException("test objects not equal")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("test objects not equal");
    }

    @Test
    @DisplayName("Happy path isInstanceOf(Class)")
    void isInstanceOf() {
        assertThatCode(() -> Ensure.isInstanceOf(String.class, "test"))
                .doesNotThrowAnyException();
        assertThatCode(() -> Ensure.isInstanceOf(Integer.class, 1, "test message"))
                .doesNotThrowAnyException();
        assertThatCode(() -> Ensure.isInstanceOf(Float.class, 32.00f, () -> new IllegalArgumentException("test message")))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("Sad path isInstanceOf(Class)")
    void isInstanceOf1() {
        assertThatThrownBy(() -> Ensure.isInstanceOf(null, "test"))
                .isInstanceOf(EnsureException.class)
                .hasMessage("clazz must not be null");
        assertThatThrownBy(() -> Ensure.isInstanceOf(null, "test", "test message"))
                .isInstanceOf(EnsureException.class)
                .hasMessage("test message");
        assertThatThrownBy(() -> Ensure.isInstanceOf(null, "test", () -> new IllegalArgumentException("test message")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("test message");
        assertThatThrownBy(() -> Ensure.isInstanceOf(String.class, 'c'))
                .isInstanceOf(EnsureException.class)
                .hasMessage("object must be an instance of java.lang.String");
        assertThatThrownBy(() -> Ensure.isInstanceOf(Integer.class, 2L, "test message"))
                .isInstanceOf(EnsureException.class)
                .hasMessage("test message");
        assertThatThrownBy(() -> Ensure.isInstanceOf(Double.class, 2f, () -> new IllegalArgumentException("test message")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("test message");
    }

}
