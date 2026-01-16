package io.github.com.ensure4j.examples;

import io.github.mangila.ensure4j.Ensure;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Demonstrates how Ensure4j integrates seamlessly with Java Streams.
 * <p>
 * The library is designed to support functional pipelines by returning
 * the validated value, making it ideal for use in `.map()`.
 */
public class JavaStream {

    /**
     * Cleans up a list of strings, ensuring no blank entries are processed.
     */
    public void processStringStream(List<String> inputs) {
        System.out.println("Processing strings...");
        List<String> result = inputs.stream()
                // Use Ensure::notBlank as a mapper to fail fast on invalid input
                .map(Ensure::notBlank)
                .map(String::trim)
                .map(String::toUpperCase)
                .toList();
        
        System.out.println("Result: " + result);
    }

    /**
     * Demonstrates using Ensure within a stream to enforce numeric boundaries.
     */
    public void processNumericStream(List<Integer> ages) {
        System.out.println("Processing ages...");
        List<Integer> validatedAges = ages.stream()
                // Enforce minimum age of 18
                .map(age -> Ensure.min(18, age, "Must be an adult"))
                .toList();
        
        System.out.println("Validated ages: " + validatedAges);
    }

    /**
     * Demonstrates using notNullOrElseThrow to handle potential nulls in a stream.
     */
    public void handleNullsInStream(List<String> names) {
        System.out.println("Handling nulls...");
        List<String> upperNames = names.stream()
                .map(Ensure::notNullOrElseThrow)
                .map(String::toUpperCase)
                .toList();
        
        System.out.println("Upper names: " + upperNames);
    }

    public static void main(String[] args) {
        JavaStream examples = new JavaStream();

        try {
            examples.processStringStream(List.of("  hello  ", "world", ""));
        } catch (RuntimeException e) {
            System.err.println("String stream failed as expected: " + e.getMessage());
        }

        try {
            examples.processNumericStream(List.of(20, 25, 15));
        } catch (RuntimeException e) {
            System.err.println("Numeric stream failed as expected: " + e.getMessage());
        }
    }
}
