/**
 * Provides specialized operation classes for performing type-specific precondition and
 * postcondition checks.
 *
 * <p>This package contains a set of singleton {@code enum} classes, each dedicated to a specific
 * category of data types or objects. These operations extend the basic checks provided by the base
 * {@link io.github.mangila.ensure4j.Ensure} class.
 *
 * <h2>Key Operation Classes</h2>
 *
 * <ul>
 *   <li>{@link io.github.mangila.ensure4j.ops.EnsureNullOps}: Explicit nullity checks.
 *   <li>{@link io.github.mangila.ensure4j.ops.EnsureBooleanOps}: Boolean state validations.
 *   <li>{@link io.github.mangila.ensure4j.ops.EnsureStringOps}: Validations for {@link
 *       java.lang.String}, including {@code notBlank}, length constraints, and regex matching.
 *   <li>{@link io.github.mangila.ensure4j.ops.EnsureNumberOps}: Range and sign validations for
 *       numeric types (e.g., {@code min}, {@code max}, {@code positive}, {@code negative}).
 *   <li>{@link io.github.mangila.ensure4j.ops.EnsureCollectionOps}: Checks for {@link
 *       java.util.Collection} emptiness, null-element presence, and size constraints.
 *   <li>{@link io.github.mangila.ensure4j.ops.EnsureMapOps}: Validations for {@link java.util.Map}
 *       emptiness and size.
 *   <li>{@link io.github.mangila.ensure4j.ops.EnsureArrayOps}: Validations for array emptiness and
 *       null elements.
 *   <li>{@link io.github.mangila.ensure4j.ops.EnsureDateTimeOps}: Validations for {@link
 *       java.time.Instant} and other date-time types.
 *   <li>{@link io.github.mangila.ensure4j.ops.EnsureObjectOps}: General object checks like {@code
 *       isInstanceOf} and equality.
 * </ul>
 *
 * <h2>Usage</h2>
 *
 * <p>Operations are typically accessed through static accessor methods in {@code Ensure}:
 *
 * <pre>{@code
 * Ensure.strings().notBlank(username);
 * Ensure.numbers().positive(age);
 * Ensure.dateTime().isFuture(expirationDate);
 * }</pre>
 *
 * <p>Each operation follows a consistent pattern, offering variants for default exceptions, custom
 * error messages, and custom exception suppliers.
 *
 * @since 3.0.0
 */
@NullMarked
package io.github.mangila.ensure4j.ops;

import org.jspecify.annotations.NullMarked;
