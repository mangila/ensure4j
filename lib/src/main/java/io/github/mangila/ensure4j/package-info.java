/**
 * <p>
 * Ensure4j is a lightweight, zero-dependency Java library designed to simplify pre-condition and post-condition checks.
 * It provides a fluent API for validating arguments, state, and other conditions in a concise and readable manner.
 * </p>
 * <p>
 * Annotated with {@link org.jspecify.annotations.NullUnmarked} since this package unspecified nullness.
 * </p>
 * <h2>Key Components:</h2>
 * <ul>
 *     <li>{@link io.github.mangila.ensure4j.Ensure} - The primary entry point and utility hub for common checks like nullity and booleans.</li>
 *     <li>{@link io.github.mangila.ensure4j.ops.NumberOps} - Specialized checks for numeric values.</li>
 *     <li>{@link io.github.mangila.ensure4j.ops.StringOps} - Specialized checks for strings.</li>
 *     <li>{@link io.github.mangila.ensure4j.ops.CollectionOps} - Specialized checks for collections.</li>
 *     <li>{@link io.github.mangila.ensure4j.ops.ArrayOps} - Specialized checks for arrays.</li>
 *     <li>{@link io.github.mangila.ensure4j.ops.MapOps} - Specialized checks for maps.</li>
 *     <li>{@link io.github.mangila.ensure4j.ops.ObjectOps} - Specialized checks for objects.</li>
 *     <li>{@link io.github.mangila.ensure4j.EnsureException} - The default runtime exception thrown when a condition is not met.</li>
 * </ul>
 */
@NullUnmarked
package io.github.mangila.ensure4j;

import org.jspecify.annotations.NullUnmarked;