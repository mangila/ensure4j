package io.github.com.ensure4j.examples.v3;

import io.github.mangila.ensure4j.Ensure;
import io.github.mangila.ensure4j.ops.EnsureNumberOps;
import io.github.mangila.ensure4j.ops.EnsureStringOps;

/**
 * Demonstrates how to use Ensure ops as field members.
 * This can be useful for reducing repeated calls to Ensure.strings(), Ensure.numbers(), etc.
 * and to skip the "naked" approach when using static utility classes.
 */
public class MemberOpsExample {

    // Using ensureops as field members
    private final EnsureStringOps strings = Ensure.strings();
    private final EnsureNumberOps numbers = Ensure.numbers();

    public void processUser(String username, int age) {
        // Use the field members for validation
        strings.notBlank(username, "Username must not be blank");
        numbers.min(18, age, "User must be at least 18 years old");

        System.out.println("Processing user: " + username + " (" + age + ")");
    }

    public static void main(String[] args) {
        MemberOpsExample example = new MemberOpsExample();

        // Valid input
        example.processUser("john_doe", 25);

        // Invalid input
        try {
            example.processUser("", 15);
        } catch (RuntimeException e) {
            System.err.println("Validation failed: " + e.getMessage());
        }
    }
}
