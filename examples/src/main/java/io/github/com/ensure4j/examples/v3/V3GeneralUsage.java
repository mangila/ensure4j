package io.github.com.ensure4j.examples.v3;

import io.github.mangila.ensure4j.Ensure;
import io.github.mangila.ensure4j.ops.EnsureCollectionOps;
import io.github.mangila.ensure4j.ops.EnsureObjectOps;

import java.util.List;

/**
 * General usage examples for Ensure4j v3
 */
public class V3GeneralUsage {

    private final EnsureObjectOps ensureObjectOps = Ensure.objects();
    private final EnsureCollectionOps ensureCollectionOps = Ensure.collections();

    public void validateTask(String taskName, List<String> tags) {
        // Fluent API - returns the value
        String validName = Ensure.strings().notBlank(taskName);
        
        // Using field members
        ensureObjectOps.isInstanceOf(List.class, tags, "Tags must be a list");
        ensureCollectionOps.notEmpty(tags, "At least one tag is required");
        ensureCollectionOps.notContainsNull(tags, "Tags cannot contain null elements");

        System.out.println("Task '" + validName + "' with tags " + tags + " is valid.");
    }

    public static void main(String[] args) {
        V3GeneralUsage usage = new V3GeneralUsage();

        // Success case
        usage.validateTask("Finish Report", List.of("work", "urgent"));

        // Failure case
        try {
            usage.validateTask(null, List.of());
        } catch (RuntimeException e) {
            System.err.println("Validation failed: " + e.getMessage());
        }
    }
}
