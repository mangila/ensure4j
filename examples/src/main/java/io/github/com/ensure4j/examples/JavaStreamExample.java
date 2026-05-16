package io.github.com.ensure4j.examples;

import io.github.mangila.ensure4j.Ensure;

import java.util.List;

public class JavaStreamExample {

    private static final List<String> stringCollection = List.of("a", "b", "c");

    void streamIt() {
        Ensure.notEmpty(stringCollection);
        stringCollection.stream()
                .map(Ensure::notBlank)
                .map(s -> Ensure.matches(s, "[a-z]+", "invalid string"))
                .forEach(s -> System.out.println("i have ensured my string!"));
    }

}
