package io.github.com.ensure4j.examples;

import io.github.mangila.ensure4j.Ensure;
import java.util.List;

/**
 * Demonstrates validation of collections and their elements.
 * <p>
 * This example shows how to ensure a collection is not null, not empty,
 * contains no nulls, and has a specific size range.
 */
public class Collections {

    public record Team(String name, List<String> members) {
        public Team {
            // Validate basic fields
            Ensure.notBlank(name, "Team name required");
            
            // Validate collection integrity
            Ensure.notNull(members, "Member list cannot be null");
            Ensure.notEmpty(members, "Team must have at least one member");
            Ensure.notContainsNull(members, "Team members cannot be null");
            
            // Validate size constraints
            Ensure.min(2, members.size(), "Team must have at least 2 members to compete");
            Ensure.max(5, members.size(), "Team cannot have more than 5 members");
        }
    }

    public static void main(String[] args) {
        // Valid team
        Team validTeam = new Team("The Coders", List.of("Alice", "Bob", "Charlie"));
        System.out.println("Created team: " + validTeam.name());

        // Invalid: Empty list
        try {
            new Team("Solo", List.of());
        } catch (RuntimeException e) {
            System.err.println("Expected failure (empty): " + e.getMessage());
        }

        // Invalid: Contains null
        try {
            new Team("Ghost Team", List.of("Alice", null));
        } catch (RuntimeException e) {
            System.err.println("Expected failure (null element): " + e.getMessage());
        }

        // Invalid: Too many members
        try {
            new Team("Large Team", List.of("1", "2", "3", "4", "5", "6"));
        } catch (RuntimeException e) {
            System.err.println("Expected failure (too many): " + e.getMessage());
        }
    }
}
