package io.github.com.ensure4j.examples;

import io.github.mangila.ensure4j.Ensure;

public class MultipleBoolCheck {

    public static void main(String[] args) {
        boolean isFullmoon = isFullMoon();
        boolean hasGarlicBread = hasGarlicBread();
        Ensure.isTrue(isFullmoon && hasGarlicBread);
    }

    /**
     * Calculate if full moon
     */
    private static boolean isFullMoon() {
        return false;
    }

    /**
     * Calculate garlic probability
     */
    private static boolean hasGarlicBread() {
        return true;
    }

}
