package io.github.com.ensure4j.examples;

import io.github.mangila.ensure4j.Ensure;

import java.util.List;

public class JavaStreamExample {

    private static final List<String> stringCollection = List.of("hello se", "world se", "mot a valid strig");

    static void streamIt() {
        Ensure.notEmpty(stringCollection);
        stringCollection.stream()
                .map(Ensure::notBlank)
                .map(Ensure::matchesAlphanumeric)
                .map(s -> Ensure.endsWith(s, "se"))
                .forEach(s -> System.out.println(s + "was ensured!"));
    }

    public static void main(String[] args) {
        streamIt();
    }

}
