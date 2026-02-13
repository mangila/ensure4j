package io.github.mangila.ensure4j.internal;

import io.github.mangila.ensure4j.EnsureException;
import org.jspecify.annotations.NonNull;

import java.util.function.Supplier;

/**
 * Internal utility class for Ensure4j.
 */
public final class EnsureUtils {

    private EnsureUtils() {
        throw new IllegalArgumentException("Utility class");
    }

    /**
     * Checks if the provided string is blank. A string is considered blank if it is empty
     * or contains only white-space characters.
     *
     * @param string the input string to check for blankness
     * @return true if the string is blank, false otherwise
     */
    public static boolean isBlank(String string) {
        return string.isBlank();
    }

    /**
     * Checks whether the given object is null.
     *
     * @param object the object to check for nullity
     * @return true if the given object is null, false otherwise
     */
    public static boolean isNull(Object object) {
        return object == null;
    }

    /**
     * Retrieves the value provided by the given {@link Supplier}. If the supplier is null or the supplied
     * value is null, an {@link EnsureException} is thrown.
     *
     * @param <T>      the type of the object supplied by the {@code Supplier}
     * @param supplier the {@link Supplier} to provide the value; must not be null and must not return null
     * @return the non-null value provided by the {@code supplier}
     * @throws EnsureException if the {@code supplier} is null or produces a null value
     */
    @NonNull
    public static <T> T getSupplierOrThrow(Supplier<T> supplier) {
        if (supplier == null) {
            throw new EnsureException("supplier was null");
        }
        final T t = supplier.get();
        if (t == null) {
            throw new EnsureException("supplier was given a null value");
        }
        return t;
    }

}
