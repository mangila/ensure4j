/**
 * Ensure4j is a lightweight, fluent Java library for parameter validation and preconditions.
 *
 * <p>It provides a comprehensive set of static methods in the {@link
 * io.github.mangila.ensure4j.Ensure} class to validate various data types including objects,
 * strings, numbers, collections, maps, arrays, and date/time objects.
 *
 * <h2>Key Features</h2>
 *
 * <ul>
 *   <li><b>Fluent API:</b> Easy to read and write validation logic.
 *   <li><b>Type-Safe:</b> Returns the validated object to allow for method chaining or direct
 *       assignment.
 *   <li><b>Customizable:</b> Supports default exception messages, custom messages, or custom
 *       exceptions via {@link java.util.function.Supplier}.
 *   <li><b>Lightweight:</b> Minimal dependencies and fast execution.
 * </ul>
 *
 * <h2>Usage Examples</h2>
 *
 * <h3>Basic Usage</h3>
 *
 * <pre>{@code
 * public void processOrder(Order order) {
 *     Ensure.notNull(order);
 *     Ensure.positive(order.getAmount());
 *     // ...
 * }
 * }</pre>
 *
 * <h3>Custom Exception Messages</h3>
 *
 * <pre>{@code
 * public void sendEmail(String email) {
 *  Ensure.notBlank(email, "Email must not be blank");
 *  Ensure.matchesEmail(email, "Invalid email format");
 *  // ...
 * }
 * }</pre>
 *
 * <h3>Custom Exceptions</h3>
 *
 * <pre>{@code
 * public void withdraw(int amount, int balance) {
 *     Ensure.positive(amount, () -> new InsufficientFundsException("Amount must be positive"));
 *     Ensure.max(amount, balance, () -> new InsufficientFundsException("Insufficient funds"));
 *     // ...
 * }
 * }</pre>
 *
 * @see io.github.mangila.ensure4j.Ensure
 */
package io.github.mangila.ensure4j;
