package io.github.mangila.ensure4j;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CollectionTest {

    @Test
    @DisplayName("Happy path notEmpty(Collection)")
    void notEmpty() {
        assertThatCode(() -> {
            var l = new ArrayList<>();
            l.add("test");
            Ensure.notEmpty(l);
        }).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("Sad path notEmpty(Collection)")
    void notEmpty1() {
        assertThatThrownBy(() -> {
            var l = new ArrayList<>();
            Ensure.notEmpty(l);
        }).isInstanceOf(EnsureException.class)
                .hasMessage("collection must not be empty");
        assertThatThrownBy(() -> {
            var l = new ArrayList<>();
            Ensure.notEmpty(l, "list must not be empty");
        }).isInstanceOf(EnsureException.class)
                .hasMessage("list must not be empty");
        assertThatThrownBy(() -> {
            var l = new ArrayList<>();
            Ensure.notEmpty(l, () -> new IllegalArgumentException("list must not be empty"));
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("list must not be empty");
    }

}
