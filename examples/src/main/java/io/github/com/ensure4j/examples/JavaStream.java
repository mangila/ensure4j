package io.github.com.ensure4j.examples;

import io.github.mangila.ensure4j.Ensure;

import java.util.stream.Stream;

/**
 * Ensure a Java Stream
 */
public class JavaStream {

    void notBlank() {
        Stream.of("a", "b", "c", "")
                .map(Ensure::notBlank)
                .map(String::toUpperCase)
                .forEach(System.out::println);
    }


    void min() {
        Stream.of(1, 2, 3, 11)
                .map(integer -> Ensure.min(integer, 10))
                .forEach(System.out::println);
    }

    void notNullOrElseThrow() {
        Stream.of("aa", "abc", null)
                .map(Ensure::notNullOrElseThrow)
                .map(String::toUpperCase)
                .forEach(System.out::println);

        Stream.of("aa", "abc", null)
                .map(string -> {
                    Ensure.notNull(string, "String cannot be null");
                    return string.toUpperCase();
                })
                .forEach(System.out::println);
    }

}
