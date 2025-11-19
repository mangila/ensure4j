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
        assertThatCode(() -> {
            var l = new ArrayList<>();
            l.add("test");
            Ensure.notEmpty(l, "list must not be empty");
        }).doesNotThrowAnyException();
        assertThatCode(() -> {
            var l = new ArrayList<>();
            l.add("test");
            Ensure.notEmpty(l, () -> new IllegalArgumentException("list must not be empty"));
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

    @Test
    @DisplayName("Happy path notContainsNull(Collection)")
    void notContainsNull() {
        assertThatCode(() -> {
            var l = new ArrayList<>();
            Ensure.notContainsNull(l);
        }).doesNotThrowAnyException();
        assertThatCode(() -> {
            var l = new ArrayList<>();
            l.add("test");
            l.add("test1");
            Ensure.notContainsNull(l, "test message");
        }).doesNotThrowAnyException();
        assertThatCode(() -> {
            var l = new ArrayList<>();
            l.add("test");
            l.add("test1");
            Ensure.notContainsNull(l, () -> new IllegalArgumentException("test message"));
        }).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("Sad path notContainsNull(Collection)")
    void notContainsNull1() {
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

}
