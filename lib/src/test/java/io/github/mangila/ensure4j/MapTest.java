package io.github.mangila.ensure4j;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MapTest {

    @Test
    @DisplayName("Should pass when map is not empty")
    void notEmpty() {
        var map = Map.of("test", "test");
        assertThat(Ensure.notEmpty(map)).isSameAs(map);
        assertThat(Ensure.notEmpty(map, "test message")).isSameAs(map);
        assertThat(Ensure.notEmpty(map, () -> new IllegalArgumentException("test message"))).isSameAs(map);
    }

    @Test
    @DisplayName("Should throw exception when map is empty")
    void notEmpty1() {
        assertThatThrownBy(() -> {
            Ensure.notEmpty((Map<?, ?>) null);
        }).isInstanceOf(EnsureException.class)
                .hasMessage("map must not be empty or null");
        assertThatThrownBy(() -> {
            var map = new java.util.HashMap<>();
            Ensure.notEmpty(map);
        }).isInstanceOf(EnsureException.class)
                .hasMessage("map must not be empty or null");
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
