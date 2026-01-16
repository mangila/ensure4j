package io.github.com.ensure4j.examples;

import io.github.mangila.ensure4j.Ensure;
import java.util.List;
import java.util.stream.Stream;

/**
 * Demonstrates how to use Ensure4j to provide fallback values when encountering nulls.
 * <p>
 * This is particularly useful in stream processing or when handling optional data.
 */
public class FallbackToDefaultValue {

    public record UserSettings(String theme, String language) {
        public static final UserSettings DEFAULT = new UserSettings("dark", "en");
    }

    public static void main(String[] args) {
        // A list that might contain null entries
        List<UserSettings> settingsList = List.of(
                new UserSettings("blue", "fr"),
                null,
                new UserSettings("high-contrast", "de")
        );

        System.out.println("Processing settings with fallbacks:");

        settingsList.stream()
                // Use notNullOrElse to provide a constant default
                .map(s -> Ensure.notNullOrElse(s, UserSettings.DEFAULT))
                .forEach(s -> System.out.println("Active Settings: " + s));

        System.out.println("\nProcessing with lazy fallback:");
        
        Stream.of(null, "custom-value")
                // Use notNullOrElseGet for lazy evaluation of the default
                .map(val -> Ensure.notNullOrElseGet(val, () -> "lazy-default-" + System.currentTimeMillis()))
                .forEach(System.out::println);
    }
}
