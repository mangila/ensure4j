/**
 * Provides the core API for Ensure4j, a lightweight, zero-dependency Java library designed for
 * robust precondition and postcondition validation.
 *
 * <p>The primary entry point is {@link io.github.mangila.ensure4j.Ensure}, which provides common
 * checks (such as nullity and boolean state) and accessors for specialized operations in the {@link
 * io.github.mangila.ensure4j.ops} subpackage.
 *
 * <h2>Key Features</h2>
 *
 * <ul>
 *   <li><b>Zero Dependencies:</b> Only depends on {@code jspecify} for nullness annotations and
 *       {@code jetbrains-annotations} for static analysis.
 *   <li><b>Fluent API:</b> Methods return the validated value to support chaining and functional
 *       pipelines.
 *   <li><b>Consistency:</b> Offers variants for default exceptions, custom messages, and custom
 *       exception suppliers.
 *   <li><b>High Performance:</b> Optimized for performance-critical paths with minimal overhead.
 *   <li><b>IDE Support:</b> Enriched with {@code @Contract} and nullness annotations for better
 *       static analysis.
 * </ul>
 *
 * <h2>Basic Usage Example</h2>
 *
 * <pre>{@code
 * public void setUsername(String username) {
 *     this.username = Ensure.notNull(username, "Username must not be null");
 * }
 *
 * public void process(List<String> items) {
 *     Ensure.isTrue(!items.isEmpty(), () -> new IllegalStateException("Items must not be empty"));
 *     // ...
 * }
 * }</pre>
 *
 * <h2>Specialized Operations</h2>
 *
 * <p>Specialized validations are available via the {@code ops} subpackage:
 *
 * <pre>{@code
 * Ensure.strings().notBlank(email);
 * Ensure.numbers().positive(price);
 * Ensure.collections().notEmpty(items);
 * }</pre>
 */
@NullMarked
package io.github.mangila.ensure4j;

import org.jspecify.annotations.NullMarked;
