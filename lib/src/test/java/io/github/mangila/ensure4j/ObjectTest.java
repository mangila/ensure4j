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

}
