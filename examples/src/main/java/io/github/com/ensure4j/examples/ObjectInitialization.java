package io.github.com.ensure4j.examples;

import io.github.mangila.ensure4j.Ensure;

import java.util.Map;

/**
 * Demonstrates different ways to initialize object fields using Ensure4j.
 * <p>
 * This shows how Ensure4j can be used to validate, throw custom exceptions,
 * or provide default values during object construction.
 */
public class ObjectInitialization {

    /**
     * Standard validation in constructor.
     */
    public static class Config {
        private final String key;
        private final String value;

        public Config(String key, String value) {
            // Validate and assign in one line
            this.key = Ensure.notBlank(key, "Config key required");
            this.value = Ensure.notBlank(value, "Config value required");
        }
    }

    /**
     * Fallback to default values if input is null.
     */
    public static class UserProfile {
        private final String bio;
        private final String theme;

        public UserProfile(String bio, String theme) {
            // Provide a default if null
            this.bio = Ensure.notNullOrElse(bio, "No bio provided.");

            // Lazy-load a default value if null
            this.theme = Ensure.notNullOrElseGet(theme, () -> "light");
        }
    }

    public record UserProfile1(String username, int score, Map<String, String> tags) {
        public UserProfile1 {
            // String length constraints
            Ensure.minLength(3, username, "Username too short");
            Ensure.maxLength(20, username, "Username too long");
            // Numeric comparisons
            Ensure.positive(score, "Score must be positive");

            // Map validation
            Ensure.notNull(tags, "Tags map cannot be null");
            Ensure.notEmpty(tags, "At least one tag is required");
        }
    }

    public static void main(String[] args) {
        Config config = new Config("app.name", "Ensure4j-Example");
        System.out.println("Config created: " + config.key);

        UserProfile profile = new UserProfile(null, null);
        System.out.println("Profile bio: " + profile.bio);
        System.out.println("Profile theme: " + profile.theme);
    }
}
