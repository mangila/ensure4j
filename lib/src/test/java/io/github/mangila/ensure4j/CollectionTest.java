package io.github.mangila.ensure4j;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class CollectionTest {

    @Test
    @DisplayName("Should pass when collection is not empty")
    void notEmpty() {
        var l = List.of("test");
        assertThat(Ensure.notEmpty(l)).isSameAs(l);
        assertThat(Ensure.notEmpty(l, "list must not be empty")).isSameAs(l);
        assertThat(Ensure.notEmpty(l, () -> new IllegalArgumentException("list must not be empty"))).isSameAs(l);
    }

    @Test
    @DisplayName("Should throw exception when collection is empty")
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

    @Test
    @DisplayName("Should pass when collection does not contain null")
    void notContainsNull() {
        var l = new ArrayList<>(List.of("test", "test1"));
        assertThat(Ensure.notContainsNull(l)).isSameAs(l);
        assertThat(Ensure.notContainsNull(l, "test message")).isSameAs(l);
        assertThat(Ensure.notContainsNull(l, () -> new IllegalArgumentException("test message"))).isSameAs(l);
    }

    @Test
    @DisplayName("Should throw exception when collection contains null")
    void notContainsNullSadPath() {
        assertThatThrownBy(() -> {
            Ensure.notContainsNull(null);
        }).isInstanceOf(EnsureException.class);
        assertThatThrownBy(() -> {
            var l = new ArrayList<>();
            l.add(null);
            l.add("test");
            Ensure.notContainsNull(l);
        }).isInstanceOf(EnsureException.class)
                .hasMessage("collection must not contain null elements");
        assertThatThrownBy(() -> {
            var l = new ArrayList<>();
            l.add(null);
            l.add("test");
            Ensure.notContainsNull(l, "list must not contain null elements");
        }).isInstanceOf(EnsureException.class)
                .hasMessage("list must not contain null elements");
        assertThatThrownBy(() -> {
            var l = new ArrayList<>();
            l.add(null);
            l.add("test");
            Ensure.notContainsNull(l, () -> new IllegalArgumentException("list must not contain null elements"));
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("list must not contain null elements");
    }

    @Test
    @DisplayName("Should pass when collection does not contain null (Legacy)")
    void notContainsNullLegacy() {
        var l = List.of("test", "test1");
        assertThat(Ensure.notContainsNullLegacy(l)).isSameAs(l);
        assertThat(Ensure.notContainsNullLegacy(l, "test message")).isSameAs(l);
        assertThat(Ensure.notContainsNullLegacy(l, () -> new IllegalArgumentException("test message"))).isSameAs(l);
    }

    @Test
    @DisplayName("Should throw exception when collection contains null (Legacy)")
    void notContainsNullLegacySadPath() {
        assertThatThrownBy(() -> {
            Ensure.notContainsNullLegacy(null);
        }).isInstanceOf(EnsureException.class);
        assertThatThrownBy(() -> {
            var l = new ArrayList<>();
            l.add(null);
            l.add("test");
            Ensure.notContainsNullLegacy(l);
        }).isInstanceOf(EnsureException.class)
                .hasMessage("collection must not contain null elements");
        assertThatThrownBy(() -> {
            var l = new ArrayList<>();
            l.add(null);
            l.add("test");
            Ensure.notContainsNullLegacy(l, "list must not contain null elements");
        }).isInstanceOf(EnsureException.class)
                .hasMessage("list must not contain null elements");
        assertThatThrownBy(() -> {
            var l = new ArrayList<>();
            l.add(null);
            l.add("test");
            Ensure.notContainsNullLegacy(l, () -> new IllegalArgumentException("list must not contain null elements"));
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("list must not contain null elements");
    }

    @Test
    @DisplayName("Should pass when collection contains element")
    void containsElement() {
        var l = List.of("element");
        assertThat(Ensure.containsElement(l, "element")).isSameAs(l);
        assertThat(Ensure.containsElement(l, "element", "test exception message")).isSameAs(l);
        assertThat(Ensure.containsElement(l, "element", () -> new IllegalArgumentException("test exception message"))).isSameAs(l);
    }

    @Test
    @DisplayName("Should throw exception when collection does not contain element")
    void containsElement1() {
        assertThatThrownBy(() -> {
            var l = new ArrayList<>();
            l.add("test");
            Ensure.containsElement(l, "fail");
        });
        assertThatThrownBy(() -> {
            var l = new ArrayList<>();
            l.add("test");
            Ensure.containsElement(l, "fail", "test exception message");
        }).isInstanceOf(EnsureException.class)
                .hasMessage("test exception message");
        assertThatThrownBy(() -> {
            var l = new ArrayList<>();
            l.add("test");
            Ensure.containsElement(l, "fail", () -> new IllegalArgumentException("test exception message"));
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("test exception message");
    }
}
