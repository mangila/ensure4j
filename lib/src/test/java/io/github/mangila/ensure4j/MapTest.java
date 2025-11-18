package io.github.mangila.ensure4j;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MapTest {

    @Test
    @DisplayName("Happy path notEmpty(Map)")
    void notEmpty() {
        assertThatCode(() -> {
            var map = new java.util.HashMap<>();
            map.put("test", "test");
            Ensure.notEmpty(map);
        }).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("Sad path notEmpty(Map)")
    void notEmpty1() {
        assertThatThrownBy(() -> {
            var map = new java.util.HashMap<>();
            Ensure.notEmpty(map);
        }).isInstanceOf(EnsureException.class)
                .hasMessage("map must not be empty");
        assertThatThrownBy(() -> {
            var map = new java.util.HashMap<String, String>();
            Ensure.notEmpty(map, "string map must not be empty");
        }).isInstanceOf(EnsureException.class)
                .hasMessage("string map must not be empty");
        assertThatThrownBy(() -> {
            var map = new java.util.HashMap<String, String>();
            Ensure.notEmpty(map, () -> new IllegalArgumentException("string map must not be empty"));
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("string map must not be empty");
    }

}
