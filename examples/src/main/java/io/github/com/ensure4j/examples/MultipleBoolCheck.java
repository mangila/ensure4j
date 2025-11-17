package io.github.com.ensure4j.examples;

import io.github.mangila.ensure4j.Ensure;

public class MultipleBoolCheck {

    public static void main(String[] args) {
        // Good practice is to keep one pre-condition per line.
        // It's easier to know which pre-condition failed when it fails
        Ensure.isTrue(isFullMoon(), "no full moon");
        Ensure.isTrue(hasGarlicBread(), "no garlic bread");
    }

    /**
     * Calculate if full moon
     */
    private static boolean isFullMoon() {
        return false;
    }

    /**
     * Calculate garlic if bread or bread is garlic
     */
    private static boolean hasGarlicBread() {
        return true;
    }

}
