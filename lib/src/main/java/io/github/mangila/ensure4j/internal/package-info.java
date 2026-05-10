/**
 * Internal utility and support classes for the Ensure4j library.
 *
 * <p><b>WARNING:</b> The classes in this package are considered internal implementation details and
 * are not part of the public API. They may be changed, moved, or removed at any time without prior
 * notice. Users of Ensure4j should not depend on classes within this package.
 *
 * <h2>Internal Responsibilities</h2>
 *
 * <ul>
 *   <li>{@link io.github.mangila.ensure4j.internal.EnsureUtils}: Provides low-level utility methods
 *       for null checks, blank checks, and exception supplier handling used across the library.
 * </ul>
 *
 * <p>This package is annotated with {@link org.jspecify.annotations.NullUnmarked} since it handles
 * low-level nullity logic where explicit annotations might be redundant or complex.
 */
@NullUnmarked
package io.github.mangila.ensure4j.internal;

import org.jspecify.annotations.NullUnmarked;
