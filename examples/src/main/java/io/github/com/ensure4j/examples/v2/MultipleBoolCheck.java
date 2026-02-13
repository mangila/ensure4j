package io.github.com.ensure4j.examples.v2;

import io.github.mangila.ensure4j.Ensure;

/**
 * Demonstrates best practices when checking multiple boolean conditions.
 * <p>
 * It is recommended to keep each condition on its own line with a descriptive message
 * to make it clear which specific condition failed.
 */
public class MultipleBoolCheck {

    public record UserPermissions(boolean canRead, boolean canWrite, boolean isAdmin) {
    }

    public void performSensitiveOperation(UserPermissions permissions) {
        // Clearer than combine into one if statement:
        // if (!canRead || !canWrite) { ... }
        
        Ensure.isTrue(permissions.canRead(), "User lacks read permission");
        Ensure.isTrue(permissions.canWrite(), "User lacks write permission");
        
        System.out.println("Sensitive operation performed!");
    }

    public static void main(String[] args) {
        MultipleBoolCheck example = new MultipleBoolCheck();
        
        UserPermissions readOnly = new UserPermissions(true, false, false);
        
        try {
            example.performSensitiveOperation(readOnly);
        } catch (RuntimeException e) {
            System.err.println("Operation blocked: " + e.getMessage());
        }
    }
}
