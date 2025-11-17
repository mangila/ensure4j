package io.github.mangila.ensure4j;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class NullTest {

    @Test
    @DisplayName("Happy path notNullOrElseGet()")
    void notNullOrElseGet() {
        String s = Ensure.notNullOrElseGet("happy", () -> "test");
        assertThat(s).isEqualTo("happy");
        s = Ensure.notNullOrElseGet(null, () -> "test");
        assertThat(s).isEqualTo("test");
    }

    @Test
    @DisplayName("Happy path notNullOrElse()")
    void notNullOrElse() {
        String s = Ensure.notNullOrElse("happy", "test");
        assertThat(s).isEqualTo("happy");
        s = Ensure.notNullOrElse(null, "test");
        assertThat(s).isEqualTo("test");
        // REMINDME: still don't know if this should throw an exception or not
        s = Ensure.notNullOrElse(null, null);
        assertThat(s).isNull();
    }

    @Test
    @DisplayName("Happy - notNullOrElseThrow()")
    void notNullOrElseThrow() {
        String s = Ensure.notNullOrElseThrow("test",
                () -> new IllegalArgumentException("test"));
        assertThat(s).isEqualTo("test");
        s = Ensure.notNullOrElseThrow("test");
        assertThat(s).isEqualTo("test");
    }

    @Test
    @DisplayName("Sad - notNullOrElseThrow()")
    void notNullOrElseThrow1() {
        assertThatThrownBy(() -> Ensure.notNullOrElseThrow(null, () -> new IllegalArgumentException("test")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("test");
        assertThatThrownBy(() -> Ensure.notNullOrElseThrow(null))
                .isInstanceOf(EnsureException.class)
                .hasMessage("object must not be null");
    }

    @Test
    void notNull() {
        assertThatCode(() -> Ensure.notNull("test"))
                .doesNotThrowAnyException();
    }

    @Test
    void notNullSadPath() {
        assertThatThrownBy(() -> Ensure.notNull(null))
                .isInstanceOf(EnsureException.class)
                .hasMessage("object must not be null");
        assertThatThrownBy(() -> Ensure.notNull(null, "test"))
                .isInstanceOf(EnsureException.class)
                .hasMessage("test");
        assertThatThrownBy(() -> Ensure.notNull(null, () -> new IllegalArgumentException("test")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("test");
    }
}
