package io.github.mangila.ensure4j;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.BigInteger;

import static org.assertj.core.api.Assertions.*;

public class ObjectTest {

    @Test
    @DisplayName("Should pass when objects are equal")
    void equals() {
        BigDecimal val = new BigDecimal("1");
        assertThat(Ensure.isEquals(val, new BigDecimal("1"))).isSameAs(val);
        assertThat(Ensure.isEquals(1, 1, "test message")).isEqualTo(1);
        assertThat(Ensure.isEquals(1, 1, () -> new IllegalArgumentException("test message"))).isEqualTo(1);
        // REMINDME: idk... ok?
        assertThat(Ensure.isEquals((Object) null, null)).isNull();
    }

    @Test
    @DisplayName("Should throw exception when objects are not equal")
    void equals1() {
        assertThatThrownBy(() -> Ensure.isEquals(new BigDecimal("1"), new BigInteger("1")))
                .isInstanceOf(EnsureException.class)
                .hasMessage("objects must be equal");
        assertThatThrownBy(() -> Ensure.isEquals(new BigDecimal("1"), new BigInteger("1"), "test objects not equal"))
                .isInstanceOf(EnsureException.class)
                .hasMessage("test objects not equal");
        assertThatThrownBy(() -> Ensure.isEquals(new BigDecimal("1"), new BigInteger("1"), () -> new IllegalArgumentException("test objects not equal")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("test objects not equal");
        assertThatThrownBy(() -> Ensure.isEquals(null, new BigInteger("1"), () -> new IllegalArgumentException("test objects not equal")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("test objects not equal");
    }

    enum TestEnum {
        A, B, C
    }

    @Test
    @DisplayName("Should pass when enums are equal")
    void equals2() {
        assertThat(Ensure.isEquals(TestEnum.A, TestEnum.A)).isSameAs(TestEnum.A);
        assertThat(Ensure.isEquals(TestEnum.A, TestEnum.A, "test message")).isSameAs(TestEnum.A);
        assertThat(Ensure.isEquals(TestEnum.A, TestEnum.A, () -> new IllegalArgumentException("test message"))).isSameAs(TestEnum.A);
        // REMINDME: idk... ok?
        assertThat(Ensure.isEquals((TestEnum) null, null)).isNull();
    }

    @Test
    @DisplayName("Should throw exception when enums are not equal")
    void equals3() {
        assertThatThrownBy(() -> Ensure.isEquals(TestEnum.A, TestEnum.B))
                .isInstanceOf(EnsureException.class)
                .hasMessage("enums must be equal");
        assertThatThrownBy(() -> Ensure.isEquals(TestEnum.A, TestEnum.B, "test enums not equal"))
                .isInstanceOf(EnsureException.class)
                .hasMessage("test enums not equal");
        assertThatThrownBy(() -> Ensure.isEquals(TestEnum.A, TestEnum.B, () -> new IllegalArgumentException("test enums not equal")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("test enums not equal");
    }

    @Test
    @DisplayName("Should pass when object is instance of class")
    void isInstanceOf() {
        String test = "test";
        assertThat(Ensure.isInstanceOf(String.class, test)).isSameAs(test);
        Integer val = 1;
        assertThat(Ensure.isInstanceOf(Integer.class, val, "test message")).isSameAs(val);
        Float f = 32.00f;
        assertThat(Ensure.isInstanceOf(Float.class, f, () -> new IllegalArgumentException("test message"))).isSameAs(f);
    }

    @Test
    @DisplayName("Should throw exception when object is not instance of class")
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
