/*
 * MIT License
 *
 * Copyright (c) 2025 Erik Olsson (olsson.erik1993@gmail.com)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package io.github.mangila.ensure4j;

import static io.github.mangila.ensure4j.EnsureArrayOps.ARRAYS_MUST_BE_DEEP_EQUAL_MESSAGE;
import static io.github.mangila.ensure4j.EnsureArrayOps.ARRAYS_MUST_BE_EQUAL_MESSAGE;
import static io.github.mangila.ensure4j.EnsureArrayOps.ARRAY_MUST_NOT_BE_EMPTY_MESSAGE;
import static io.github.mangila.ensure4j.EnsureBooleanOps.BOOLEAN_MUST_BE_FALSE_MESSAGE;
import static io.github.mangila.ensure4j.EnsureBooleanOps.BOOLEAN_MUST_BE_TRUE_MESSAGE;
import static io.github.mangila.ensure4j.EnsureCollectionOps.COLLECTION_MUST_CONTAIN_ELEMENT_MESSAGE;
import static io.github.mangila.ensure4j.EnsureCollectionOps.COLLECTION_MUST_NOT_BE_EMPTY_OR_NULL_MESSAGE;
import static io.github.mangila.ensure4j.EnsureDateTimeOps.INSTANT_MUST_BE_IN_FUTURE;
import static io.github.mangila.ensure4j.EnsureDateTimeOps.INSTANT_MUST_BE_PAST_OR_PRESENT;
import static io.github.mangila.ensure4j.EnsureMapOps.MAP_MUST_CONTAIN_KEY_MESSAGE;
import static io.github.mangila.ensure4j.EnsureMapOps.MAP_MUST_CONTAIN_VALUE_MESSAGE;
import static io.github.mangila.ensure4j.EnsureMapOps.MAP_MUST_NOT_BE_EMPTY_MESSAGE;
import static io.github.mangila.ensure4j.EnsureNullOps.OBJECT_MUST_NOT_BE_NULL_MESSAGE;
import static io.github.mangila.ensure4j.EnsureNumberOps.NUMBER_MUST_BE_NEGATIVE_FORMAT;
import static io.github.mangila.ensure4j.EnsureNumberOps.NUMBER_MUST_BE_NEGATIVE_OR_ZERO_FORMAT;
import static io.github.mangila.ensure4j.EnsureNumberOps.NUMBER_MUST_BE_POSITIVE_FORMAT;
import static io.github.mangila.ensure4j.EnsureNumberOps.NUMBER_MUST_BE_POSITIVE_OR_ZERO_FORMAT;
import static io.github.mangila.ensure4j.EnsureNumberOps.NUMBER_NOT_GREATER_THAN_OR_EQUAL_FORMAT;
import static io.github.mangila.ensure4j.EnsureNumberOps.NUMBER_NOT_IN_RANGE_FORMAT;
import static io.github.mangila.ensure4j.EnsureNumberOps.NUMBER_NOT_LESS_THAN_OR_EQUAL_FORMAT;
import static io.github.mangila.ensure4j.EnsureObjectOps.OBJECTS_DEEP_EQUAL_MESSAGE;
import static io.github.mangila.ensure4j.EnsureObjectOps.OBJECTS_MUST_BE_EQUAL_MESSAGE;
import static io.github.mangila.ensure4j.EnsureObjectOps.OBJECT_MUST_BE_OF_TYPE_MESSAGE;
import static io.github.mangila.ensure4j.EnsurePatternOps.STRING_MUST_MATCH_ALPHANUMERIC_MESSAGE;
import static io.github.mangila.ensure4j.EnsurePatternOps.STRING_MUST_MATCH_EMAIL_MESSAGE;
import static io.github.mangila.ensure4j.EnsureStringOps.STRING_LENGTH_AT_LEAST_FORMAT;
import static io.github.mangila.ensure4j.EnsureStringOps.STRING_LENGTH_AT_MOST_FORMAT;
import static io.github.mangila.ensure4j.EnsureStringOps.STRING_MUST_END_WITH_FORMAT;
import static io.github.mangila.ensure4j.EnsureStringOps.STRING_MUST_MATCH_REGEX_FORMAT;
import static io.github.mangila.ensure4j.EnsureStringOps.STRING_MUST_NOT_BE_BLANK_MESSAGE;
import static io.github.mangila.ensure4j.EnsureStringOps.STRING_MUST_START_WITH_FORMAT;

import java.time.Instant;
import java.util.Collection;
import java.util.Map;
import java.util.function.Supplier;
import java.util.regex.Pattern;
import org.intellij.lang.annotations.RegExp;
import org.jetbrains.annotations.Contract;

/**
 * Ensure4j is a lightweight, fluent Java library for parameter validation and preconditions.
 *
 * <p>It provides a comprehensive set of static methods in the {@link
 * io.github.mangila.ensure4j.Ensure} class to validate various data types including objects,
 * strings, numbers, collections, maps, regex, arrays, and date/time objects.
 */
public final class Ensure {

  /**
   * Ensures that the provided collection contains the specified element.
   *
   * @param <T> the type of the collection
   * @param collection the collection to check
   * @param element the element to check for
   * @return the provided collection if it contains the element
   * @throws EnsureException if the collection does not contain the element, with the message {@code
   *     "collection must contain element"}
   * @see #containsElement(Collection, Object, String)
   * @see #containsElement(Collection, Object, Supplier)
   */
  @Contract("null, _ -> fail; !null, _ -> param1")
  public static <T extends Collection<?>> T containsElement(T collection, Object element) {
    return containsElement(collection, element, COLLECTION_MUST_CONTAIN_ELEMENT_MESSAGE);
  }

  /**
   * Ensures that the provided collection contains the specified element.
   *
   * @param <T> the type of the collection
   * @param collection the collection to check
   * @param element the element to check for
   * @param exceptionMessage the message to include in the exception if validation fails
   * @return the provided collection if it contains the element
   * @throws EnsureException if the collection does not contain the element, with the provided
   *     message
   * @see #containsElement(Collection, Object)
   * @see #containsElement(Collection, Object, Supplier)
   */
  @Contract("null, _, _ -> fail; !null, _, _ -> param1")
  public static <T extends Collection<?>> T containsElement(
      T collection, Object element, String exceptionMessage) {
    return containsElement(collection, element, () -> EnsureException.from(exceptionMessage));
  }

  /**
   * Ensures that the provided collection contains the specified element.
   *
   * @param <T> the type of the collection
   * @param collection the collection to check
   * @param element the element to check for
   * @param exceptionSupplier the supplier that provides the exception to be thrown if validation
   *     fails
   * @return the provided collection if it contains the element
   * @throws RuntimeException if the collection does not contain the element; the thrown exception
   *     is provided by {@code exceptionSupplier}
   * @see #containsElement(Collection, Object)
   * @see #containsElement(Collection, Object, String)
   */
  @Contract("null, _, _ -> fail; !null, _, _ -> param1")
  public static <T extends Collection<?>> T containsElement(
      T collection, Object element, Supplier<? extends RuntimeException> exceptionSupplier) {
    return EnsureCollectionOps.containsElement(collection, element, exceptionSupplier);
  }

  /**
   * Ensures that the provided map contains the specified key.
   *
   * @param <T> the type of the map
   * @param map the map to check
   * @param key the key whose presence in the map is to be tested
   * @return the provided map if it contains the specified key
   * @throws EnsureException if the map is {@code null} or does not contain the specified key, with
   *     the message {@code "map must contain key: %s"}
   * @see #containsKey(Map, Object, String)
   * @see #containsKey(Map, Object, Supplier)
   */
  @Contract("null, _, _ -> fail; !null, _, _ -> param1")
  public static <T extends Map<?, ?>> T containsKey(T map, Object key) {
    return containsKey(map, key, MAP_MUST_CONTAIN_KEY_MESSAGE.formatted(key));
  }

  /**
   * Ensures that the provided map contains the specified key.
   *
   * @param <T> the type of the map
   * @param map the map to check
   * @param key the key whose presence in the map is to be tested
   * @param exceptionMessage the message to include in the exception if validation fails
   * @return the provided map if it contains the specified key
   * @throws EnsureException if the map is {@code null} or does not contain the specified key, with
   *     the provided message
   * @see #containsKey(Map, Object)
   * @see #containsKey(Map, Object, Supplier)
   */
  @Contract("null, _, _ -> fail; !null, _, _ -> param1")
  public static <T extends Map<?, ?>> T containsKey(T map, Object key, String exceptionMessage) {
    return containsKey(map, key, () -> EnsureException.from(exceptionMessage));
  }

  /**
   * Ensures that the provided map contains the specified key.
   *
   * @param <T> the type of the map
   * @param map the map to check
   * @param key the key whose presence in the map is to be tested
   * @param exceptionSupplier the supplier that provides the exception to be thrown if validation
   *     fails
   * @return the provided map if it contains the specified key
   * @throws RuntimeException if the map is {@code null} or does not contain the specified key; the
   *     thrown exception is provided by {@code exceptionSupplier}
   * @see #containsKey(Map, Object)
   * @see #containsKey(Map, Object, String)
   */
  @Contract("null, _, _ -> fail; !null, _, _ -> param1")
  public static <T extends Map<?, ?>> T containsKey(
      T map, Object key, Supplier<? extends RuntimeException> exceptionSupplier) {
    return EnsureMapOps.containsKey(map, key, exceptionSupplier);
  }

  /**
   * Ensures that the provided map contains the specified value.
   *
   * @param <T> the type of the map
   * @param map the map to check
   * @param value the value whose presence in the map is to be tested
   * @return the provided map if it contains the specified value
   * @throws EnsureException if the map is {@code null} or does not contain the specified value,
   *     with the message {@code "map must contain value: %s"}
   * @see #containsValue(Map, Object, String)
   * @see #containsValue(Map, Object, Supplier)
   */
  @Contract("null, _, _ -> fail; !null, _, _ -> param1")
  public static <T extends Map<?, ?>> T containsValue(T map, Object value) {
    return containsValue(map, value, MAP_MUST_CONTAIN_VALUE_MESSAGE.formatted(value));
  }

  /**
   * Ensures that the provided map contains the specified value.
   *
   * @param <T> the type of the map
   * @param map the map to check
   * @param value the value whose presence in the map is to be tested
   * @param exceptionMessage the message to include in the exception if validation fails
   * @return the provided map if it contains the specified value
   * @throws EnsureException if the map is {@code null} or does not contain the specified value,
   *     with the provided message
   * @see #containsValue(Map, Object)
   * @see #containsValue(Map, Object, Supplier)
   */
  @Contract("null, _, _ -> fail; !null, _, _ -> param1")
  public static <T extends Map<?, ?>> T containsValue(
      T map, Object value, String exceptionMessage) {
    return containsValue(map, value, () -> EnsureException.from(exceptionMessage));
  }

  /**
   * Ensures that the provided map contains the specified value.
   *
   * @param <T> the type of the map
   * @param map the map to check
   * @param value the value whose presence in the map is to be tested
   * @param exceptionSupplier the supplier that provides the exception to be thrown if validation
   *     fails
   * @return the provided map if it contains the specified value
   * @throws RuntimeException if the map is {@code null} or does not contain the specified value;
   *     the thrown exception is provided by {@code exceptionSupplier}
   * @see #containsValue(Map, Object)
   * @see #containsValue(Map, Object, String)
   */
  @Contract("null, _, _ -> fail; !null, _, _ -> param1")
  public static <T extends Map<?, ?>> T containsValue(
      T map, Object value, Supplier<? extends RuntimeException> exceptionSupplier) {
    return EnsureMapOps.containsValue(map, value, exceptionSupplier);
  }

  /**
   * Ensures that the provided object is deeply equal to the expected object.
   *
   * @param <T> the type of the object
   * @param actual the object to check
   * @param expected the object to compare against
   * @return the provided object if it is deeply equal to the expected object
   * @throws EnsureException if the objects are not deeply equal, with the message {@code "objects
   *     must be deeply equal"}
   * @see #deepEqualTo(Object, Object, String)
   * @see #deepEqualTo(Object, Object, Supplier)
   */
  @Contract(
      "null, null -> param1; null, !null -> fail; !null, null -> fail; !null, !null -> param1")
  public static <T> T deepEqualTo(T actual, Object expected) {
    return deepEqualTo(actual, expected, OBJECTS_DEEP_EQUAL_MESSAGE);
  }

  /**
   * Ensures that the provided object is deeply equal to the expected object.
   *
   * @param <T> the type of the object
   * @param actual the object to check
   * @param expected the object to compare against
   * @param exceptionMessage the message to include in the exception if validation fails
   * @return the provided object if it is deeply equal to the expected object
   * @throws EnsureException if the objects are not deeply equal, with the provided message
   * @see #deepEqualTo(Object, Object)
   * @see #deepEqualTo(Object, Object, Supplier)
   */
  @Contract(
      "null, null, _ -> param1; null, !null, _ -> fail; !null, null, _ -> fail; !null, !null, _ ->"
          + " param1")
  public static <T> T deepEqualTo(T actual, Object expected, String exceptionMessage) {
    return deepEqualTo(actual, expected, () -> EnsureException.from(exceptionMessage));
  }

  /**
   * Ensures that the provided object is deeply equal to the expected object.
   *
   * @param <T> the type of the object
   * @param actual the object to check
   * @param expected the object to compare against
   * @param exceptionSupplier the supplier that provides the exception to be thrown if validation
   *     fails
   * @return the provided object if it is deeply equal to the expected object
   * @throws RuntimeException if the objects are not deeply equal; the thrown exception is provided
   *     by {@code exceptionSupplier}
   * @see #deepEqualTo(Object, Object)
   * @see #deepEqualTo(Object, Object, String)
   */
  @Contract(
      "null, null, _ -> param1; null, !null, _ -> fail; !null, null, _ -> fail; !null, !null, _ ->"
          + " param1")
  public static <T> T deepEqualTo(
      T actual, Object expected, Supplier<? extends RuntimeException> exceptionSupplier) {
    return EnsureObjectOps.deepEqualTo(actual, expected, exceptionSupplier);
  }

  /**
   * Ensures that the provided arrays are deeply equal.
   *
   * @param <T> the component type of the arrays
   * @param array the first array
   * @param otherArray the second array
   * @return the first array if they are deeply equal
   * @throws EnsureException if the arrays are not deeply equal, with the message {@code "arrays
   *     must be deep equal"}
   * @see #deepEqualTo(Object[], Object[], String)
   * @see #deepEqualTo(Object[], Object[], Supplier)
   */
  @Contract(
      "null, null -> param1; null, !null -> fail; !null, null -> fail; !null, !null -> param1")
  public static <T> T[] deepEqualTo(T[] array, T[] otherArray) {
    return deepEqualTo(array, otherArray, ARRAYS_MUST_BE_DEEP_EQUAL_MESSAGE);
  }

  /**
   * Ensures that the provided arrays are deeply equal.
   *
   * @param <T> the component type of the arrays
   * @param array the first array
   * @param otherArray the second array
   * @param exceptionMessage the message to include in the exception if validation fails
   * @return the first array if they are deeply equal
   * @throws EnsureException if the arrays are not deeply equal, with the provided message
   * @see #deepEqualTo(Object[], Object[])
   * @see #deepEqualTo(Object[], Object[], Supplier)
   */
  @Contract(
      "null, null, _ -> param1; null, !null, _ -> fail; !null, null, _ -> fail; !null, !null, _ ->"
          + " param1")
  public static <T> T[] deepEqualTo(T[] array, T[] otherArray, String exceptionMessage) {
    return deepEqualTo(array, otherArray, () -> EnsureException.from(exceptionMessage));
  }

  /**
   * Ensures that the provided arrays are deeply equal.
   *
   * @param <T> the component type of the arrays
   * @param array the first array
   * @param otherArray the second array
   * @param exceptionSupplier the supplier that provides the exception to be thrown if validation
   *     fails
   * @return the first array if they are deeply equal
   * @throws RuntimeException if the arrays are not deeply equal; the thrown exception is provided
   *     by {@code exceptionSupplier}
   * @see #deepEqualTo(Object[], Object[])
   * @see #deepEqualTo(Object[], Object[], String)
   */
  @Contract(
      "null, null, _ -> param1; null, !null, _ -> fail; !null, null, _ -> fail; !null, !null, _ ->"
          + " param1")
  public static <T> T[] deepEqualTo(
      T[] array, T[] otherArray, Supplier<? extends RuntimeException> exceptionSupplier) {
    return EnsureArrayOps.deepEqualTo(array, otherArray, exceptionSupplier);
  }

  /**
   * Ensures that the provided string ends with the specified suffix.
   *
   * @param string the string to check
   * @param suffix the required suffix
   * @return the provided string if it ends with the suffix
   * @throws EnsureException if the string is {@code null} or does not end with the suffix, with the
   *     message {@code "string must end with %s"}
   * @see #endsWith(String, String, String)
   * @see #endsWith(String, String, Supplier)
   */
  @Contract("null, _ -> fail; !null, _ -> param1")
  public static String endsWith(String string, String suffix) {
    return endsWith(string, suffix, STRING_MUST_END_WITH_FORMAT.formatted(suffix));
  }

  /**
   * Ensures that the provided string ends with the specified suffix.
   *
   * @param string the string to check
   * @param suffix the required suffix
   * @param exceptionMessage the message to include in the exception if validation fails
   * @return the provided string if it ends with the suffix
   * @throws EnsureException if the string is {@code null} or does not end with the suffix, with the
   *     provided message
   * @see #endsWith(String, String)
   * @see #endsWith(String, String, Supplier)
   */
  @Contract("null, _, _ -> fail; !null, _, _ -> param1")
  public static String endsWith(String string, String suffix, String exceptionMessage) {
    return endsWith(string, suffix, () -> EnsureException.from(exceptionMessage));
  }

  /**
   * Ensures that the provided string ends with the specified suffix.
   *
   * @param string the string to check
   * @param suffix the required suffix
   * @param exceptionSupplier the supplier that provides the exception to be thrown if validation
   *     fails
   * @return the provided string if it ends with the suffix
   * @throws RuntimeException if the string is {@code null} or does not end with the suffix; the
   *     thrown exception is provided by {@code exceptionSupplier}
   * @see #endsWith(String, String)
   * @see #endsWith(String, String, String)
   */
  @Contract("null, _, _ -> fail; !null, _, _ -> param1")
  public static String endsWith(
      String string, String suffix, Supplier<? extends RuntimeException> exceptionSupplier) {
    return EnsureStringOps.endsWith(string, suffix, exceptionSupplier);
  }

  /**
   * Ensures that the provided object is equal to the expected object.
   *
   * @param <T> the type of the object
   * @param actual the object to check
   * @param expected the object to compare against
   * @return the provided object if it is equal to the expected object
   * @throws EnsureException if the objects are not equal, with the message {@code "objects must be
   *     equal"}
   * @see #equalTo(Object, Object, String)
   * @see #equalTo(Object, Object, Supplier)
   */
  @Contract(
      "null, null -> param1; null, !null -> fail; !null, null -> fail; !null, !null -> param1")
  public static <T> T equalTo(T actual, Object expected) {
    return equalTo(actual, expected, OBJECTS_MUST_BE_EQUAL_MESSAGE);
  }

  /**
   * Ensures that the provided object is equal to the expected object.
   *
   * @param <T> the type of the object
   * @param actual the object to check
   * @param expected the object to compare against
   * @param exceptionMessage the message to include in the exception if validation fails
   * @return the provided object if it is equal to the expected object
   * @throws EnsureException if the objects are not equal, with the provided message
   * @see #equalTo(Object, Object)
   * @see #equalTo(Object, Object, Supplier)
   */
  @Contract(
      "null, null, _ -> param1; null, !null, _ -> fail; !null, null, _ -> fail; !null, !null, _ ->"
          + " param1")
  public static <T> T equalTo(T actual, Object expected, String exceptionMessage) {
    return equalTo(actual, expected, () -> EnsureException.from(exceptionMessage));
  }

  /**
   * Ensures that the provided object is equal to the expected object.
   *
   * @param <T> the type of the object
   * @param actual the object to check
   * @param expected the object to compare against
   * @param exceptionSupplier the supplier that provides the exception to be thrown if validation
   *     fails
   * @return the provided object if it is equal to the expected object
   * @throws RuntimeException if the objects are not equal; the thrown exception is provided by
   *     {@code exceptionSupplier}
   * @see #equalTo(Object, Object)
   * @see #equalTo(Object, Object, String)
   */
  @Contract(
      "null, null, _ -> param1; null, !null, _ -> fail; !null, null, _ -> fail; !null, !null, _ ->"
          + " param1")
  public static <T> T equalTo(
      T actual, Object expected, Supplier<? extends RuntimeException> exceptionSupplier) {
    return EnsureObjectOps.equalTo(actual, expected, exceptionSupplier);
  }

  /**
   * Ensures that the provided arrays are equal.
   *
   * @param <T> the component type of the arrays
   * @param array the first array
   * @param otherArray the second array
   * @return the first array if they are equal
   * @throws EnsureException if the arrays are not equal, with the message {@code "arrays must be
   *     equal"}
   * @see #equalTo(Object[], Object[], String)
   * @see #equalTo(Object[], Object[], Supplier)
   */
  @Contract(
      "null, null -> param1; null, !null -> fail; !null, null -> fail; !null, !null -> param1")
  public static <T> T[] equalTo(T[] array, T[] otherArray) {
    return equalTo(array, otherArray, ARRAYS_MUST_BE_EQUAL_MESSAGE);
  }

  /**
   * Ensures that the provided arrays are equal.
   *
   * @param <T> the component type of the arrays
   * @param array the first array
   * @param otherArray the second array
   * @param exceptionMessage the message to include in the exception if validation fails
   * @return the first array if they are equal
   * @throws EnsureException if the arrays are not equal, with the provided message
   * @see #equalTo(Object[], Object[])
   * @see #equalTo(Object[], Object[], Supplier)
   */
  @Contract(
      "null, null, _ -> param1; null, !null, _ -> fail; !null, null, _ -> fail; !null, !null, _ ->"
          + " param1")
  public static <T> T[] equalTo(T[] array, T[] otherArray, String exceptionMessage) {
    return equalTo(array, otherArray, () -> EnsureException.from(exceptionMessage));
  }

  /**
   * Ensures that the provided arrays are equal.
   *
   * @param <T> the component type of the arrays
   * @param array the first array
   * @param otherArray the second array
   * @param exceptionSupplier the supplier that provides the exception to be thrown if validation
   *     fails
   * @return the first array if they are equal
   * @throws RuntimeException if the arrays are not equal; the thrown exception is provided by
   *     {@code exceptionSupplier}
   * @see #equalTo(Object[], Object[])
   * @see #equalTo(Object[], Object[], String)
   */
  @Contract(
      "null, null, _ -> param1; null, !null, _ -> fail; !null, null, _ -> fail; !null, !null, _ ->"
          + " param1")
  public static <T> T[] equalTo(
      T[] array, T[] otherArray, Supplier<? extends RuntimeException> exceptionSupplier) {
    return EnsureArrayOps.equalTo(array, otherArray, exceptionSupplier);
  }

  /**
   * Ensures that the provided instant is in the future.
   *
   * @param instant the instant to check
   * @param boundary the boundary instant
   * @return the provided instant if it is in the future
   * @throws EnsureException if the instant is not in the future, with the message {@code "instant
   *     must be in the future"}
   * @see #future(Instant, Instant, String)
   * @see #future(Instant, Instant, Supplier)
   */
  @Contract("null, _ -> fail; _, null -> fail; !null, !null -> param1")
  public static Instant future(Instant instant, Instant boundary) {
    return future(instant, boundary, INSTANT_MUST_BE_IN_FUTURE);
  }

  /**
   * Ensures that the provided instant is in the future.
   *
   * @param instant the instant to check
   * @param boundary the boundary instant
   * @param exceptionMessage the message to include in the exception if validation fails
   * @return the provided instant if it is in the future
   * @throws EnsureException if the instant is not in the future, with the provided message
   * @see #future(Instant, Instant)
   * @see #future(Instant, Instant, Supplier)
   */
  @Contract("null, _, _ -> fail; _, null, _ -> fail; !null, !null, _ -> param1")
  public static Instant future(Instant instant, Instant boundary, String exceptionMessage) {
    return future(instant, boundary, () -> EnsureException.from(exceptionMessage));
  }

  /**
   * Ensures that the provided instant is in the future.
   *
   * @param instant the instant to check
   * @param boundary the boundary instant
   * @param exceptionSupplier the supplier that provides the exception to be thrown if validation
   *     fails
   * @return the provided instant if it is in the future
   * @throws RuntimeException if the instant is not in the future; the thrown exception is provided
   *     by {@code exceptionSupplier}
   * @see #future(Instant, Instant)
   * @see #future(Instant, Instant, String)
   */
  @Contract("null, _, _ -> fail; _, null, _ -> fail; !null, !null, _ -> param1")
  public static Instant future(
      Instant instant, Instant boundary, Supplier<? extends RuntimeException> exceptionSupplier) {
    return EnsureDateTimeOps.future(instant, boundary, exceptionSupplier);
  }

  /**
   * Ensures that the provided value is within the specified range [start, end] (inclusive).
   *
   * @param value the value to check
   * @param start the minimum allowed value (inclusive)
   * @param end the maximum allowed value (inclusive)
   * @return the provided value if it is within the range
   * @throws EnsureException if the value is not in the range, with the message {@code "number must
   *     be in range [%d, %d], but was %d"}
   * @see #inRange(int, int, int, String)
   * @see #inRange(int, int, int, Supplier)
   */
  @Contract("_, _, _ -> param1")
  public static int inRange(int value, int start, int end) {
    return inRange(value, start, end, NUMBER_NOT_IN_RANGE_FORMAT.formatted(start, end, value));
  }

  /**
   * Ensures that the provided value is within the specified range [start, end] (inclusive).
   *
   * @param value the value to check
   * @param start the minimum allowed value (inclusive)
   * @param end the maximum allowed value (inclusive)
   * @param exceptionMessage the message to include in the exception if validation fails
   * @return the provided value if it is within the range
   * @throws EnsureException if the value is not in the range, with the provided message
   * @see #inRange(int, int, int)
   * @see #inRange(int, int, int, Supplier)
   */
  @Contract("_, _, _, _ -> param1")
  public static int inRange(int value, int start, int end, String exceptionMessage) {
    return inRange(value, start, end, () -> EnsureException.from(exceptionMessage));
  }

  /**
   * Ensures that the provided value is within the specified range [start, end] (inclusive).
   *
   * @param value the value to check
   * @param start the minimum allowed value (inclusive)
   * @param end the maximum allowed value (inclusive)
   * @param exceptionSupplier the supplier that provides the exception to be thrown if validation
   *     fails
   * @return the provided value if it is within the range
   * @throws RuntimeException if the value is not in the range; the thrown exception is provided by
   *     {@code exceptionSupplier}
   * @see #inRange(int, int, int)
   * @see #inRange(int, int, int, String)
   */
  @Contract("_, _, _, _ -> param1")
  public static int inRange(
      int value, int start, int end, Supplier<? extends RuntimeException> exceptionSupplier) {
    return EnsureNumberOps.inRange(value, start, end, exceptionSupplier);
  }

  /**
   * Ensures that the provided value is within the specified range [start, end] (inclusive).
   *
   * @param value the value to check
   * @param start the minimum allowed value (inclusive)
   * @param end the maximum allowed value (inclusive)
   * @return the provided value if it is within the range
   * @throws EnsureException if the value is not in the range, with the message {@code "number must
   *     be in range [%d, %d], but was %d"}
   * @see #inRange(long, long, long, String)
   * @see #inRange(long, long, long, Supplier)
   */
  @Contract("_, _, _ -> param1")
  public static long inRange(long value, long start, long end) {
    return inRange(value, start, end, NUMBER_NOT_IN_RANGE_FORMAT.formatted(start, end, value));
  }

  /**
   * Ensures that the provided value is within the specified range [start, end] (inclusive).
   *
   * @param value the value to check
   * @param start the minimum allowed value (inclusive)
   * @param end the maximum allowed value (inclusive)
   * @param exceptionMessage the message to include in the exception if validation fails
   * @return the provided value if it is within the range
   * @throws EnsureException if the value is not in the range, with the provided message
   * @see #inRange(long, long, long)
   * @see #inRange(long, long, long, Supplier)
   */
  @Contract("_, _, _, _ -> param1")
  public static long inRange(long value, long start, long end, String exceptionMessage) {
    return inRange(value, start, end, () -> EnsureException.from(exceptionMessage));
  }

  /**
   * Ensures that the provided value is within the specified range [start, end] (inclusive).
   *
   * @param value the value to check
   * @param start the minimum allowed value (inclusive)
   * @param end the maximum allowed value (inclusive)
   * @param exceptionSupplier the supplier that provides the exception to be thrown if validation
   *     fails
   * @return the provided value if it is within the range
   * @throws RuntimeException if the value is not in the range; the thrown exception is provided by
   *     {@code exceptionSupplier}
   * @see #inRange(long, long, long)
   * @see #inRange(long, long, long, String)
   */
  @Contract("_, _, _, _ -> param1")
  public static long inRange(
      long value, long start, long end, Supplier<? extends RuntimeException> exceptionSupplier) {
    return EnsureNumberOps.inRange(value, start, end, exceptionSupplier);
  }

  /**
   * Ensures that the provided boolean expression is {@code false}.
   *
   * @param expression the boolean expression to check
   * @throws EnsureException if the expression is {@code true}, with the message {@code "boolean
   *     must be false"}
   * @see #isFalse(boolean, String)
   * @see #isFalse(boolean, Supplier)
   */
  @Contract("true -> fail")
  public static void isFalse(boolean expression) {
    isFalse(expression, BOOLEAN_MUST_BE_FALSE_MESSAGE);
  }

  /**
   * Ensures that the provided boolean expression is {@code false}.
   *
   * @param expression the boolean expression to check
   * @param exceptionMessage the message to include in the exception if validation fails
   * @throws EnsureException if the expression is {@code true}, with the provided message
   * @see #isFalse(boolean)
   * @see #isFalse(boolean, Supplier)
   */
  @Contract("true, _ -> fail")
  public static void isFalse(boolean expression, String exceptionMessage) {
    isFalse(expression, () -> EnsureException.from(exceptionMessage));
  }

  /**
   * Ensures that the provided boolean expression is {@code false}.
   *
   * @param expression the boolean expression to check
   * @param exceptionSupplier the supplier that provides the exception to be thrown if validation
   *     fails
   * @throws RuntimeException if the expression is {@code true}; the thrown exception is provided by
   *     {@code exceptionSupplier}
   * @see #isFalse(boolean)
   * @see #isFalse(boolean, String)
   */
  @Contract("true, _ -> fail")
  public static void isFalse(
      boolean expression, Supplier<? extends RuntimeException> exceptionSupplier) {
    EnsureBooleanOps.isFalse(expression, exceptionSupplier);
  }

  /**
   * Ensures that the provided boolean expression is {@code true}.
   *
   * @param expression the boolean expression to check
   * @throws EnsureException if the expression is {@code false}, with the message {@code "boolean
   *     must be true"}
   * @see #isTrue(boolean, String)
   * @see #isTrue(boolean, Supplier)
   */
  @Contract("false -> fail")
  public static void isTrue(boolean expression) {
    isTrue(expression, BOOLEAN_MUST_BE_TRUE_MESSAGE);
  }

  /**
   * Ensures that the provided boolean expression is {@code true}.
   *
   * @param expression the boolean expression to check
   * @param exceptionMessage the message to include in the exception if validation fails
   * @throws EnsureException if the expression is {@code false}, with the provided message
   * @see #isTrue(boolean)
   * @see #isTrue(boolean, Supplier)
   */
  @Contract("false, _ -> fail")
  public static void isTrue(boolean expression, String exceptionMessage) {
    isTrue(expression, () -> EnsureException.from(exceptionMessage));
  }

  /**
   * Ensures that the provided boolean expression is {@code true}.
   *
   * @param expression the boolean expression to check
   * @param exceptionSupplier the supplier that provides the exception to be thrown if validation
   *     fails
   * @throws RuntimeException if the expression is {@code false}; the thrown exception is provided
   *     by {@code exceptionSupplier}
   * @see #isTrue(boolean)
   * @see #isTrue(boolean, String)
   */
  @Contract("false, _ -> fail")
  public static void isTrue(
      boolean expression, Supplier<? extends RuntimeException> exceptionSupplier) {
    EnsureBooleanOps.isTrue(expression, exceptionSupplier);
  }

  /**
   * Ensures that the provided string matches the specified pattern.
   *
   * @param string the string to check
   * @param pattern the pattern to match against
   * @return the provided string if it matches the pattern
   * @throws EnsureException if the string is {@code null} or does not match the pattern, with the
   *     message {@code "string must match pattern %s"}
   * @see #matches(String, Pattern, String)
   * @see #matches(String, Pattern, Supplier)
   */
  @Contract("null, _ -> fail; !null, _ -> param1")
  public static String matches(String string, Pattern pattern) {
    return matches(string, pattern, STRING_MUST_MATCH_REGEX_FORMAT.formatted(pattern));
  }

  /**
   * Ensures that the provided string matches the specified pattern.
   *
   * @param string the string to check
   * @param pattern the pattern to match against
   * @param exceptionMessage the message to include in the exception if validation fails
   * @return the provided string if it matches the pattern
   * @throws EnsureException if the string is {@code null} or does not match the pattern, with the
   *     provided message
   * @see #matches(String, Pattern)
   * @see #matches(String, Pattern, Supplier)
   */
  @Contract("null, _, _ -> fail; !null, _, _ -> param1")
  public static String matches(String string, Pattern pattern, String exceptionMessage) {
    return matches(string, pattern, () -> EnsureException.from(exceptionMessage));
  }

  /**
   * Ensures that the provided string matches the specified pattern.
   *
   * @param string the string to check
   * @param pattern the pattern to match against
   * @param exceptionSupplier the supplier that provides the exception to be thrown if validation
   *     fails
   * @return the provided string if it matches the pattern
   * @throws RuntimeException if the string is {@code null} or does not match the pattern; the
   *     thrown exception is provided by {@code exceptionSupplier}
   * @see #matches(String, Pattern)
   * @see #matches(String, Pattern, String)
   */
  @Contract("null, _, _ -> fail; !null, _, _ -> param1")
  public static String matches(
      String string, Pattern pattern, Supplier<? extends RuntimeException> exceptionSupplier) {
    return EnsurePatternOps.matches(string, pattern, exceptionSupplier);
  }

  /**
   * Ensures that the provided string matches the specified regular expression.
   *
   * @param string the string to check
   * @param regex the regular expression to match against
   * @return the provided string if it matches the regular expression
   * @throws EnsureException if the string is {@code null} or does not match the regular expression,
   *     with the message {@code "string must match regex %s"}
   * @see #matches(String, String, String)
   * @see #matches(String, String, Supplier)
   */
  @Contract("null, _ -> fail; !null, _ -> param1")
  public static String matches(String string, @RegExp String regex) {
    return matches(string, regex, STRING_MUST_MATCH_REGEX_FORMAT.formatted(regex));
  }

  /**
   * Ensures that the provided string matches the specified regular expression.
   *
   * @param string the string to check
   * @param regex the regular expression to match against
   * @param exceptionMessage the message to include in the exception if validation fails
   * @return the provided string if it matches the regular expression
   * @throws EnsureException if the string is {@code null} or does not match the regular expression,
   *     with the provided message
   * @see #matches(String, String)
   * @see #matches(String, String, Supplier)
   */
  @Contract("null, _, _ -> fail; !null, _, _ -> param1")
  public static String matches(String string, @RegExp String regex, String exceptionMessage) {
    return matches(string, regex, () -> EnsureException.from(exceptionMessage));
  }

  /**
   * Ensures that the provided string matches the specified regular expression.
   *
   * @param string the string to check
   * @param regex the regular expression to match against
   * @param exceptionSupplier the supplier that provides the exception to be thrown if validation
   *     fails
   * @return the provided string if it matches the regular expression
   * @throws RuntimeException if the string is {@code null} or does not match the regular
   *     expression; the thrown exception is provided by {@code exceptionSupplier}
   * @see #matches(String, String)
   * @see #matches(String, String, String)
   */
  @Contract("null, _, _ -> fail; !null, _, _ -> param1")
  public static String matches(
      String string, @RegExp String regex, Supplier<? extends RuntimeException> exceptionSupplier) {
    return EnsureStringOps.matches(string, regex, exceptionSupplier);
  }

  /**
   * Ensures that the provided string matches the alphanumeric pattern (including spaces).
   *
   * @param string the string to check
   * @return the provided string if it matches the alphanumeric pattern
   * @throws EnsureException if the string does not match the alphanumeric pattern, with the message
   *     {@code "string must match alphanumeric pattern"}
   * @see #matchesAlphanumeric(String, String)
   * @see #matchesAlphanumeric(String, Supplier)
   */
  @Contract("null -> fail; !null -> param1")
  public static String matchesAlphanumeric(String string) {
    return matchesAlphanumeric(string, STRING_MUST_MATCH_ALPHANUMERIC_MESSAGE);
  }

  /**
   * Ensures that the provided string matches the alphanumeric pattern (including spaces).
   *
   * @param string the string to check
   * @param exceptionMessage the message to include in the exception if validation fails
   * @return the provided string if it matches the alphanumeric pattern
   * @throws EnsureException if the string does not match the alphanumeric pattern, with the
   *     provided message
   * @see #matchesAlphanumeric(String)
   * @see #matchesAlphanumeric(String, Supplier)
   */
  @Contract("null, _ -> fail; !null, _ -> param1")
  public static String matchesAlphanumeric(String string, String exceptionMessage) {
    return matchesAlphanumeric(string, () -> EnsureException.from(exceptionMessage));
  }

  /**
   * Ensures that the provided string matches the alphanumeric pattern (including spaces).
   *
   * @param string the string to check
   * @param exceptionSupplier the supplier that provides the exception to be thrown if validation
   *     fails
   * @return the provided string if it matches the alphanumeric pattern
   * @throws RuntimeException if the string does not match the alphanumeric pattern; the thrown
   *     exception is provided by {@code exceptionSupplier}
   * @see #matchesAlphanumeric(String)
   * @see #matchesAlphanumeric(String, String)
   */
  @Contract("null, _ -> fail; !null, _ -> param1")
  public static String matchesAlphanumeric(
      String string, Supplier<? extends RuntimeException> exceptionSupplier) {
    return EnsurePatternOps.matchesAlphanumeric(string, exceptionSupplier);
  }

  /**
   * Ensures that the provided string matches the email pattern.
   *
   * @param string the string to check
   * @return the provided string if it matches the email pattern
   * @throws EnsureException if the string does not match the email pattern, with the message {@code
   *     "string must match email pattern"}
   * @see #matchesEmail(String, String)
   * @see #matchesEmail(String, Supplier)
   */
  @Contract("null -> fail; !null -> param1")
  public static String matchesEmail(String string) {
    return matchesEmail(string, STRING_MUST_MATCH_EMAIL_MESSAGE);
  }

  /**
   * Ensures that the provided string matches the email pattern.
   *
   * @param string the string to check
   * @param exceptionMessage the message to include in the exception if validation fails
   * @return the provided string if it matches the email pattern
   * @throws EnsureException if the string does not match the email pattern, with the provided
   *     message
   * @see #matchesEmail(String)
   * @see #matchesEmail(String, Supplier)
   */
  @Contract("null, _ -> fail; !null, _ -> param1")
  public static String matchesEmail(String string, String exceptionMessage) {
    return matchesEmail(string, () -> EnsureException.from(exceptionMessage));
  }

  /**
   * Ensures that the provided string matches the email pattern.
   *
   * @param string the string to check
   * @param exceptionSupplier the supplier that provides the exception to be thrown if validation
   *     fails
   * @return the provided string if it matches the email pattern
   * @throws RuntimeException if the string does not match the email pattern; the thrown exception
   *     is provided by {@code exceptionSupplier}
   * @see #matchesEmail(String)
   * @see #matchesEmail(String, String)
   */
  @Contract("null, _ -> fail; !null, _ -> param1")
  public static String matchesEmail(
      String string, Supplier<? extends RuntimeException> exceptionSupplier) {
    return EnsurePatternOps.matchesEmail(string, exceptionSupplier);
  }

  /**
   * Ensures that the provided value does not exceed the specified boundary.
   *
   * @param value the value to check
   * @param boundary the maximum allowed value
   * @return the provided value if it does not exceed the boundary
   * @throws EnsureException if the value exceeds the boundary, with the message {@code "number must
   *     be less than or equal to %d, but was %d"}
   * @see #max(int, int, String)
   * @see #max(int, int, Supplier)
   */
  @Contract("_, _ -> param1")
  public static int max(int value, int boundary) {
    return max(value, boundary, NUMBER_NOT_LESS_THAN_OR_EQUAL_FORMAT.formatted(boundary, value));
  }

  /**
   * Ensures that the provided value does not exceed the specified boundary.
   *
   * @param value the value to check
   * @param boundary the maximum allowed value
   * @param exceptionMessage the message to include in the exception if validation fails
   * @return the provided value if it does not exceed the boundary
   * @throws EnsureException if the value exceeds the boundary, with the provided message
   * @see #max(int, int)
   * @see #max(int, int, Supplier)
   */
  @Contract("_, _, _ -> param1")
  public static int max(int value, int boundary, String exceptionMessage) {
    return max(value, boundary, () -> EnsureException.from(exceptionMessage));
  }

  /**
   * Ensures that the provided value does not exceed the specified boundary.
   *
   * @param value the value to check
   * @param boundary the maximum allowed value
   * @param exceptionSupplier the supplier that provides the exception to be thrown if validation
   *     fails
   * @return the provided value if it does not exceed the boundary
   * @throws RuntimeException if the value exceeds the boundary; the thrown exception is provided by
   *     {@code exceptionSupplier}
   * @see #max(int, int)
   * @see #max(int, int, String)
   */
  @Contract("_, _, _ -> param1")
  public static int max(
      int value, int boundary, Supplier<? extends RuntimeException> exceptionSupplier) {
    return EnsureNumberOps.max(value, boundary, exceptionSupplier);
  }

  /**
   * Ensures that the provided value does not exceed the specified boundary.
   *
   * @param value the value to check
   * @param boundary the maximum allowed value
   * @return the provided value if it does not exceed the boundary
   * @throws EnsureException if the value exceeds the boundary, with the message {@code "number must
   *     be less than or equal to %d, but was %d"}
   * @see #max(long, long, String)
   * @see #max(long, long, Supplier)
   */
  @Contract("_, _ -> param1")
  public static long max(long value, long boundary) {
    return max(value, boundary, NUMBER_NOT_LESS_THAN_OR_EQUAL_FORMAT.formatted(boundary, value));
  }

  /**
   * Ensures that the provided value does not exceed the specified boundary.
   *
   * @param value the value to check
   * @param boundary the maximum allowed value
   * @param exceptionMessage the message to include in the exception if validation fails
   * @return the provided value if it does not exceed the boundary
   * @throws EnsureException if the value exceeds the boundary, with the provided message
   * @see #max(long, long)
   * @see #max(long, long, Supplier)
   */
  @Contract("_, _, _ -> param1")
  public static long max(long value, long boundary, String exceptionMessage) {
    return max(value, boundary, () -> EnsureException.from(exceptionMessage));
  }

  /**
   * Ensures that the provided value does not exceed the specified boundary.
   *
   * @param value the value to check
   * @param boundary the maximum allowed value
   * @param exceptionSupplier the supplier that provides the exception to be thrown if validation
   *     fails
   * @return the provided value if it does not exceed the boundary
   * @throws RuntimeException if the value exceeds the boundary; the thrown exception is provided by
   *     {@code exceptionSupplier}
   * @see #max(long, long)
   * @see #max(long, long, String)
   */
  @Contract("_, _, _ -> param1")
  public static long max(
      long value, long boundary, Supplier<? extends RuntimeException> exceptionSupplier) {
    return EnsureNumberOps.max(value, boundary, exceptionSupplier);
  }

  /**
   * Ensures that the provided string does not exceed the maximum length requirement.
   *
   * @param string the string to check
   * @param boundary the maximum length
   * @return the provided string if it does not exceed the maximum length requirement
   * @throws EnsureException if the string is {@code null} or longer than the maximum length, with
   *     the message {@code "string length must be at most %d characters"}
   * @see #maxLength(String, int, String)
   * @see #maxLength(String, int, Supplier)
   */
  @Contract("null, _ -> fail; !null, _ -> param1")
  public static String maxLength(String string, int boundary) {
    return maxLength(string, boundary, STRING_LENGTH_AT_MOST_FORMAT.formatted(boundary));
  }

  /**
   * Ensures that the provided string does not exceed the maximum length requirement.
   *
   * @param string the string to check
   * @param boundary the maximum length
   * @param exceptionMessage the message to include in the exception if validation fails
   * @return the provided string if it does not exceed the maximum length requirement
   * @throws EnsureException if the string is {@code null} or longer than the maximum length, with
   *     the provided message
   * @see #maxLength(String, int)
   * @see #maxLength(String, int, Supplier)
   */
  @Contract("null, _, _ -> fail; !null, _, _ -> param1")
  public static String maxLength(String string, int boundary, String exceptionMessage) {
    return maxLength(string, boundary, () -> EnsureException.from(exceptionMessage));
  }

  /**
   * Ensures that the provided string does not exceed the maximum length requirement.
   *
   * @param string the string to check
   * @param boundary the maximum length
   * @param exceptionSupplier the supplier that provides the exception to be thrown if validation
   *     fails
   * @return the provided string if it does not exceed the maximum length requirement
   * @throws RuntimeException if the string is {@code null} or longer than the maximum length; the
   *     thrown exception is provided by {@code exceptionSupplier}
   * @see #maxLength(String, int)
   * @see #maxLength(String, int, String)
   */
  @Contract("null, _, _ -> fail; !null, _, _ -> param1")
  public static String maxLength(
      String string, int boundary, Supplier<? extends RuntimeException> exceptionSupplier) {
    return EnsureStringOps.maxLength(string, boundary, exceptionSupplier);
  }

  /**
   * Ensures that the provided value meets or exceeds the specified boundary.
   *
   * @param value the value to check
   * @param boundary the minimum allowed value
   * @return the provided value if it meets or exceeds the boundary
   * @throws EnsureException if the value is less than the boundary, with the message {@code "number
   *     must be greater than or equal to %d, but was %d"}
   * @see #min(int, int, String)
   * @see #min(int, int, Supplier)
   */
  @Contract("_, _ -> param1")
  public static int min(int value, int boundary) {
    return min(value, boundary, NUMBER_NOT_GREATER_THAN_OR_EQUAL_FORMAT.formatted(boundary, value));
  }

  /**
   * Ensures that the provided value meets or exceeds the specified boundary.
   *
   * @param value the value to check
   * @param boundary the minimum allowed value
   * @param exceptionMessage the message to include in the exception if validation fails
   * @return the provided value if it meets or exceeds the boundary
   * @throws EnsureException if the value is less than the boundary, with the provided message
   * @see #min(int, int)
   * @see #min(int, int, Supplier)
   */
  @Contract("_, _, _ -> param1")
  public static int min(int value, int boundary, String exceptionMessage) {
    return min(value, boundary, () -> EnsureException.from(exceptionMessage));
  }

  /**
   * Ensures that the provided value meets or exceeds the specified boundary.
   *
   * @param value the value to check
   * @param boundary the minimum allowed value
   * @param exceptionSupplier the supplier that provides the exception to be thrown if validation
   *     fails
   * @return the provided value if it meets or exceeds the boundary
   * @throws RuntimeException if the value is less than the boundary; the thrown exception is
   *     provided by {@code exceptionSupplier}
   * @see #min(int, int)
   * @see #min(int, int, String)
   */
  @Contract("_, _, _ -> param1")
  public static int min(
      int value, int boundary, Supplier<? extends RuntimeException> exceptionSupplier) {
    return EnsureNumberOps.min(value, boundary, exceptionSupplier);
  }

  /**
   * Ensures that the provided value meets or exceeds the specified boundary.
   *
   * @param value the value to check
   * @param boundary the minimum allowed value
   * @return the provided value if it meets or exceeds the boundary
   * @throws EnsureException if the value is less than the boundary, with the message {@code "number
   *     must be greater than or equal to %d, but was %d"}
   * @see #min(long, long, String)
   * @see #min(long, long, Supplier)
   */
  @Contract("_, _ -> param1")
  public static long min(long value, long boundary) {
    return min(value, boundary, NUMBER_NOT_GREATER_THAN_OR_EQUAL_FORMAT.formatted(boundary, value));
  }

  /**
   * Ensures that the provided value meets or exceeds the specified boundary.
   *
   * @param value the value to check
   * @param boundary the minimum allowed value
   * @param exceptionMessage the message to include in the exception if validation fails
   * @return the provided value if it meets or exceeds the boundary
   * @throws EnsureException if the value is less than the boundary, with the provided message
   * @see #min(long, long)
   * @see #min(long, long, Supplier)
   */
  @Contract("_, _, _ -> param1")
  public static long min(long value, long boundary, String exceptionMessage) {
    return min(value, boundary, () -> EnsureException.from(exceptionMessage));
  }

  /**
   * Ensures that the provided value meets or exceeds the specified boundary.
   *
   * @param value the value to check
   * @param boundary the minimum allowed value
   * @param exceptionSupplier the supplier that provides the exception to be thrown if validation
   *     fails
   * @return the provided value if it meets or exceeds the boundary
   * @throws RuntimeException if the value is less than the boundary; the thrown exception is
   *     provided by {@code exceptionSupplier}
   * @see #min(long, long)
   * @see #min(long, long, String)
   */
  @Contract("_, _, _ -> param1")
  public static long min(
      long value, long boundary, Supplier<? extends RuntimeException> exceptionSupplier) {
    return EnsureNumberOps.min(value, boundary, exceptionSupplier);
  }

  /**
   * Ensures that the provided string meets the minimum length requirement.
   *
   * @param string the string to check
   * @param boundary the minimum length
   * @return the provided string if it meets the minimum length requirement
   * @throws EnsureException if the string is {@code null} or shorter than the minimum length, with
   *     the message {@code "string length must be at least %d characters"}
   * @see #minLength(String, int, String)
   * @see #minLength(String, int, Supplier)
   */
  @Contract("null, _ -> fail; !null, _ -> param1")
  public static String minLength(String string, int boundary) {
    return minLength(string, boundary, STRING_LENGTH_AT_LEAST_FORMAT.formatted(boundary));
  }

  /**
   * Ensures that the provided string meets the minimum length requirement.
   *
   * @param string the string to check
   * @param boundary the minimum length
   * @param exceptionMessage the message to include in the exception if validation fails
   * @return the provided string if it meets the minimum length requirement
   * @throws EnsureException if the string is {@code null} or shorter than the minimum length, with
   *     the provided message
   * @see #minLength(String, int)
   * @see #minLength(String, int, Supplier)
   */
  @Contract("null, _, _ -> fail; !null, _, _ -> param1")
  public static String minLength(String string, int boundary, String exceptionMessage) {
    return minLength(string, boundary, () -> EnsureException.from(exceptionMessage));
  }

  /**
   * Ensures that the provided string meets the minimum length requirement.
   *
   * @param string the string to check
   * @param boundary the minimum length
   * @param exceptionSupplier the supplier that provides the exception to be thrown if validation
   *     fails
   * @return the provided string if it meets the minimum length requirement
   * @throws RuntimeException if the string is {@code null} or shorter than the minimum length; the
   *     thrown exception is provided by {@code exceptionSupplier}
   * @see #minLength(String, int)
   * @see #minLength(String, int, String)
   */
  @Contract("null, _, _ -> fail; !null, _, _ -> param1")
  public static String minLength(
      String string, int boundary, Supplier<? extends RuntimeException> exceptionSupplier) {
    return EnsureStringOps.minLength(string, boundary, exceptionSupplier);
  }

  /**
   * Ensures that the provided value is negative.
   *
   * @param value the value to check
   * @return the provided value if it is negative
   * @throws EnsureException if the value is not negative, with the message {@code "number must be
   *     negative: %d"}
   * @see #negative(int, String)
   * @see #negative(int, Supplier)
   */
  @Contract("_ -> param1")
  public static int negative(int value) {
    return negative(value, NUMBER_MUST_BE_NEGATIVE_FORMAT.formatted(value));
  }

  /**
   * Ensures that the provided value is negative.
   *
   * @param value the value to check
   * @param exceptionMessage the message to include in the exception if validation fails
   * @return the provided value if it is negative
   * @throws EnsureException if the value is not negative, with the provided message
   * @see #negative(int)
   * @see #negative(int, Supplier)
   */
  @Contract("_, _ -> param1")
  public static int negative(int value, String exceptionMessage) {
    return negative(value, () -> EnsureException.from(exceptionMessage));
  }

  /**
   * Ensures that the provided value is negative.
   *
   * @param value the value to check
   * @param exceptionSupplier the supplier that provides the exception to be thrown if validation
   *     fails
   * @return the provided value if it is negative
   * @throws RuntimeException if the value is not negative; the thrown exception is provided by
   *     {@code exceptionSupplier}
   * @see #negative(int)
   * @see #negative(int, String)
   */
  @Contract("_, _ -> param1")
  public static int negative(int value, Supplier<? extends RuntimeException> exceptionSupplier) {
    return EnsureNumberOps.negative(value, exceptionSupplier);
  }

  /**
   * Ensures that the provided value is negative.
   *
   * @param value the value to check
   * @return the provided value if it is negative
   * @throws EnsureException if the value is not negative, with the message {@code "number must be
   *     negative: %d"}
   * @see #negative(long, String)
   * @see #negative(long, Supplier)
   */
  @Contract("_ -> param1")
  public static long negative(long value) {
    return negative(value, NUMBER_MUST_BE_NEGATIVE_FORMAT.formatted(value));
  }

  /**
   * Ensures that the provided value is negative.
   *
   * @param value the value to check
   * @param exceptionMessage the message to include in the exception if validation fails
   * @return the provided value if it is negative
   * @throws EnsureException if the value is not negative, with the provided message
   * @see #negative(long)
   * @see #negative(long, Supplier)
   */
  @Contract("_, _ -> param1")
  public static long negative(long value, String exceptionMessage) {
    return negative(value, () -> EnsureException.from(exceptionMessage));
  }

  /**
   * Ensures that the provided value is negative.
   *
   * @param value the value to check
   * @param exceptionSupplier the supplier that provides the exception to be thrown if validation
   *     fails
   * @return the provided value if it is negative
   * @throws RuntimeException if the value is not negative; the thrown exception is provided by
   *     {@code exceptionSupplier}
   * @see #negative(long)
   * @see #negative(long, String)
   */
  @Contract("_, _ -> param1")
  public static long negative(long value, Supplier<? extends RuntimeException> exceptionSupplier) {
    return EnsureNumberOps.negative(value, exceptionSupplier);
  }

  /**
   * Ensures that the provided value is negative or zero.
   *
   * @param value the value to check
   * @return the provided value if it is negative or zero
   * @throws EnsureException if the value is not negative or zero, with the message {@code "number
   *     must be negative or zero: %d"}
   * @see #negativeWithZero(int, String)
   * @see #negativeWithZero(int, Supplier)
   */
  @Contract("_ -> param1")
  public static int negativeWithZero(int value) {
    return negativeWithZero(value, NUMBER_MUST_BE_NEGATIVE_OR_ZERO_FORMAT.formatted(value));
  }

  /**
   * Ensures that the provided value is negative or zero.
   *
   * @param value the value to check
   * @param exceptionMessage the message to include in the exception if validation fails
   * @return the provided value if it is negative or zero
   * @throws EnsureException if the value is not negative or zero, with the provided message
   * @see #negativeWithZero(int)
   * @see #negativeWithZero(int, Supplier)
   */
  @Contract("_, _ -> param1")
  public static int negativeWithZero(int value, String exceptionMessage) {
    return negativeWithZero(value, () -> EnsureException.from(exceptionMessage));
  }

  /**
   * Ensures that the provided value is negative or zero.
   *
   * @param value the value to check
   * @param exceptionSupplier the supplier that provides the exception to be thrown if validation
   *     fails
   * @return the provided value if it is negative or zero
   * @throws RuntimeException if the value is not negative or zero; the thrown exception is provided
   *     by {@code exceptionSupplier}
   * @see #negativeWithZero(int)
   * @see #negativeWithZero(int, String)
   */
  @Contract("_, _ -> param1")
  public static int negativeWithZero(
      int value, Supplier<? extends RuntimeException> exceptionSupplier) {
    return EnsureNumberOps.negativeWithZero(value, exceptionSupplier);
  }

  /**
   * Ensures that the provided value is negative or zero.
   *
   * @param value the value to check
   * @return the provided value if it is negative or zero
   * @throws EnsureException if the value is not negative or zero, with the message {@code "number
   *     must be negative or zero: %d"}
   * @see #negativeWithZero(long, String)
   * @see #negativeWithZero(long, Supplier)
   */
  @Contract("_ -> param1")
  public static long negativeWithZero(long value) {
    return negativeWithZero(value, NUMBER_MUST_BE_NEGATIVE_OR_ZERO_FORMAT.formatted(value));
  }

  /**
   * Ensures that the provided value is negative or zero.
   *
   * @param value the value to check
   * @param exceptionMessage the message to include in the exception if validation fails
   * @return the provided value if it is negative or zero
   * @throws EnsureException if the value is not negative or zero, with the provided message
   * @see #negativeWithZero(long)
   * @see #negativeWithZero(long, Supplier)
   */
  @Contract("_, _ -> param1")
  public static long negativeWithZero(long value, String exceptionMessage) {
    return negativeWithZero(value, () -> EnsureException.from(exceptionMessage));
  }

  /**
   * Ensures that the provided value is negative or zero.
   *
   * @param value the value to check
   * @param exceptionSupplier the supplier that provides the exception to be thrown if validation
   *     fails
   * @return the provided value if it is negative or zero
   * @throws RuntimeException if the value is not negative or zero; the thrown exception is provided
   *     by {@code exceptionSupplier}
   * @see #negativeWithZero(long)
   * @see #negativeWithZero(long, String)
   */
  @Contract("_, _ -> param1")
  public static long negativeWithZero(
      long value, Supplier<? extends RuntimeException> exceptionSupplier) {
    return EnsureNumberOps.negativeWithZero(value, exceptionSupplier);
  }

  /**
   * Ensures that the provided string is not {@code null}, empty, or blank.
   *
   * @param string the string to check
   * @return the provided string if it is not {@code null}, empty, or blank
   * @throws EnsureException if the string is {@code null}, empty, or blank, with the message {@code
   *     "string must not be blank"}
   * @see #notBlank(String, String)
   * @see #notBlank(String, Supplier)
   */
  @Contract("null -> fail; !null -> param1")
  public static String notBlank(String string) {
    return notBlank(string, STRING_MUST_NOT_BE_BLANK_MESSAGE);
  }

  /**
   * Ensures that the provided string is not {@code null}, empty, or blank.
   *
   * @param string the string to check
   * @param exceptionMessage the message to include in the exception if validation fails
   * @return the provided string if it is not {@code null}, empty, or blank
   * @throws EnsureException if the string is {@code null}, empty, or blank, with the provided
   *     message
   * @see #notBlank(String)
   * @see #notBlank(String, Supplier)
   */
  @Contract("null, _ -> fail; !null, _ -> param1")
  public static String notBlank(String string, String exceptionMessage) {
    return notBlank(string, () -> EnsureException.from(exceptionMessage));
  }

  /**
   * Ensures that the provided string is not {@code null}, empty, or blank.
   *
   * @param string the string to check
   * @param exceptionSupplier the supplier that provides the exception to be thrown if validation
   *     fails
   * @return the provided string if it is not {@code null}, empty, or blank
   * @throws RuntimeException if the string is {@code null}, empty, or blank; the thrown exception
   *     is provided by {@code exceptionSupplier}
   * @see #notBlank(String)
   * @see #notBlank(String, String)
   */
  @Contract("null, _ -> fail; !null, _ -> param1")
  public static String notBlank(
      String string, Supplier<? extends RuntimeException> exceptionSupplier) {
    return EnsureStringOps.notBlank(string, exceptionSupplier);
  }

  /**
   * Ensures that the provided collection is not {@code null} or empty.
   *
   * @param <T> the type of the collection
   * @param collection the collection to check
   * @return the provided collection if it is not {@code null} or empty
   * @throws EnsureException if the collection is {@code null} or empty, with the message {@code
   *     "collection must not be empty or null"}
   * @see #notEmpty(Collection, String)
   * @see #notEmpty(Collection, Supplier)
   */
  @Contract("null -> fail; !null -> param1")
  public static <T extends Collection<?>> T notEmpty(T collection) {
    return notEmpty(collection, COLLECTION_MUST_NOT_BE_EMPTY_OR_NULL_MESSAGE);
  }

  /**
   * Ensures that the provided map is not {@code null} or empty.
   *
   * @param <T> the type of the map
   * @param map the map to check
   * @return the provided map if it is not {@code null} or empty
   * @throws EnsureException if the map is {@code null} or empty, with the message {@code "map must
   *     not be empty"}
   * @see #notEmpty(Map, String)
   * @see #notEmpty(Map, Supplier)
   */
  @Contract("null -> fail; !null -> param1")
  public static <T extends Map<?, ?>> T notEmpty(T map) {
    return notEmpty(map, MAP_MUST_NOT_BE_EMPTY_MESSAGE);
  }

  /**
   * Ensures that the provided collection is not {@code null} or empty.
   *
   * @param <T> the type of the collection
   * @param collection the collection to check
   * @param exceptionMessage the message to include in the exception if validation fails
   * @return the provided collection if it is not {@code null} or empty
   * @throws EnsureException if the collection is {@code null} or empty, with the provided message
   * @see #notEmpty(Collection)
   * @see #notEmpty(Collection, Supplier)
   */
  @Contract("null, _ -> fail; !null, _ -> param1")
  public static <T extends Collection<?>> T notEmpty(T collection, String exceptionMessage) {
    return notEmpty(collection, () -> EnsureException.from(exceptionMessage));
  }

  /**
   * Ensures that the provided map is not {@code null} or empty.
   *
   * @param <T> the type of the map
   * @param map the map to check
   * @param exceptionMessage the message to include in the exception if validation fails
   * @return the provided map if it is not {@code null} or empty
   * @throws EnsureException if the map is {@code null} or empty, with the provided message
   * @see #notEmpty(Map)
   * @see #notEmpty(Map, Supplier)
   */
  @Contract("null, _ -> fail; !null, _ -> param1")
  public static <T extends Map<?, ?>> T notEmpty(T map, String exceptionMessage) {
    return notEmpty(map, () -> EnsureException.from(exceptionMessage));
  }

  /**
   * Ensures that the provided collection is not {@code null} or empty.
   *
   * @param <T> the type of the collection
   * @param collection the collection to check
   * @param exceptionSupplier the supplier that provides the exception to be thrown if validation
   *     fails
   * @return the provided collection if it is not {@code null} or empty
   * @throws RuntimeException if the collection is {@code null} or empty; the thrown exception is
   *     provided by {@code exceptionSupplier}
   * @see #notEmpty(Collection)
   * @see #notEmpty(Collection, String)
   */
  @Contract("null, _ -> fail; !null, _ -> param1")
  public static <T extends Collection<?>> T notEmpty(
      T collection, Supplier<? extends RuntimeException> exceptionSupplier) {
    return EnsureCollectionOps.notEmpty(collection, exceptionSupplier);
  }

  /**
   * Ensures that the provided map is not {@code null} or empty.
   *
   * @param <T> the type of the map
   * @param map the map to check
   * @param exceptionSupplier the supplier that provides the exception to be thrown if validation
   *     fails
   * @return the provided map if it is not {@code null} or empty
   * @throws RuntimeException if the map is {@code null} or empty; the thrown exception is provided
   *     by {@code exceptionSupplier}
   * @see #notEmpty(Map)
   * @see #notEmpty(Map, String)
   */
  @Contract("null, _ -> fail; !null, _ -> param1")
  public static <T extends Map<?, ?>> T notEmpty(
      T map, Supplier<? extends RuntimeException> exceptionSupplier) {
    return EnsureMapOps.notEmpty(map, exceptionSupplier);
  }

  /**
   * Ensures that the provided array is not {@code null} or empty.
   *
   * @param <T> the component type of the array
   * @param array the array to check
   * @return the provided array if it is not {@code null} or empty
   * @throws EnsureException if the array is {@code null} or empty, with the message {@code "array
   *     must not be empty"}
   * @see #notEmpty(Object[], String)
   * @see #notEmpty(Object[], Supplier)
   */
  @Contract("null -> fail; !null -> param1")
  public static <T> T[] notEmpty(T[] array) {
    return notEmpty(array, ARRAY_MUST_NOT_BE_EMPTY_MESSAGE);
  }

  /**
   * Ensures that the provided array is not {@code null} or empty.
   *
   * @param <T> the component type of the array
   * @param array the array to check
   * @param exceptionMessage the message to include in the exception if validation fails
   * @return the provided array if it is not {@code null} or empty
   * @throws EnsureException if the array is {@code null} or empty, with the provided message
   * @see #notEmpty(Object[])
   * @see #notEmpty(Object[], Supplier)
   */
  @Contract("null, _ -> fail; !null, _ -> param1")
  public static <T> T[] notEmpty(T[] array, String exceptionMessage) {
    return notEmpty(array, () -> EnsureException.from(exceptionMessage));
  }

  /**
   * Ensures that the provided array is not {@code null} or empty.
   *
   * @param <T> the component type of the array
   * @param array the array to check
   * @param exceptionSupplier the supplier that provides the exception to be thrown if validation
   *     fails
   * @return the provided array if it is not {@code null} or empty
   * @throws RuntimeException if the array is {@code null} or empty; the thrown exception is
   *     provided by {@code exceptionSupplier}
   * @see #notEmpty(Object[])
   * @see #notEmpty(Object[], String)
   */
  @Contract("null, _ -> fail; !null, _ -> param1")
  public static <T> T[] notEmpty(
      T[] array, Supplier<? extends RuntimeException> exceptionSupplier) {
    return EnsureArrayOps.notEmpty(array, exceptionSupplier);
  }

  /**
   * Ensures that the provided object is not {@code null}.
   *
   * @param <T> the type of the object
   * @param object the object to check
   * @return the provided object if it is not {@code null}
   * @throws EnsureException if the object is {@code null}, with the message {@code "object must not
   *     be null"}
   * @see #notNull(Object, String)
   * @see #notNull(Object, Supplier)
   */
  @Contract("null -> fail; !null -> param1")
  public static <T> T notNull(T object) {
    return notNull(object, OBJECT_MUST_NOT_BE_NULL_MESSAGE);
  }

  /**
   * Ensures that the provided object is not {@code null}.
   *
   * @param <T> the type of the object
   * @param object the object to check
   * @param exceptionMessage the message to include in the exception if validation fails
   * @return the provided object if it is not {@code null}
   * @throws EnsureException if the object is {@code null}, with the provided message
   * @see #notNull(Object)
   * @see #notNull(Object, Supplier)
   */
  @Contract("null, _ -> fail; !null, _ -> param1")
  public static <T> T notNull(T object, String exceptionMessage) {
    return notNull(object, () -> EnsureException.from(exceptionMessage));
  }

  /**
   * Ensures that the provided object is not {@code null}.
   *
   * @param <T> the type of the object
   * @param object the object to check
   * @param exceptionSupplier the supplier that provides the exception to be thrown if validation
   *     fails
   * @return the provided object if it is not {@code null}
   * @throws RuntimeException if the object is {@code null}; the thrown exception is provided by
   *     {@code exceptionSupplier}
   * @see #notNull(Object)
   * @see #notNull(Object, String)
   */
  @Contract("null, _ -> fail; !null, _ -> param1")
  public static <T> T notNull(T object, Supplier<? extends RuntimeException> exceptionSupplier) {
    return EnsureNullOps.notNull(object, exceptionSupplier);
  }

  /**
   * Ensures that the provided instant is in the past or present.
   *
   * @param instant the instant to check
   * @param boundary the boundary instant
   * @return the provided instant if it is in the past or present
   * @throws EnsureException if the instant is not in the past or present, with the message {@code
   *     "instant must be in the past or present"}
   * @see #pastOrPresent(Instant, Instant, String)
   * @see #pastOrPresent(Instant, Instant, Supplier)
   */
  @Contract("null, _ -> fail; _, null -> fail; !null, !null -> param1")
  public static Instant pastOrPresent(Instant instant, Instant boundary) {
    return pastOrPresent(instant, boundary, INSTANT_MUST_BE_PAST_OR_PRESENT);
  }

  /**
   * Ensures that the provided instant is in the past or present.
   *
   * @param instant the instant to check
   * @param boundary the boundary instant
   * @param exceptionMessage the message to include in the exception if validation fails
   * @return the provided instant if it is in the past or present
   * @throws EnsureException if the instant is not in the past or present, with the provided message
   * @see #pastOrPresent(Instant, Instant)
   * @see #pastOrPresent(Instant, Instant, Supplier)
   */
  @Contract("null, _, _ -> fail; _, null, _ -> fail; !null, !null, _ -> param1")
  public static Instant pastOrPresent(Instant instant, Instant boundary, String exceptionMessage) {
    return pastOrPresent(instant, boundary, () -> EnsureException.from(exceptionMessage));
  }

  /**
   * Ensures that the provided instant is in the past or present.
   *
   * @param instant the instant to check
   * @param boundary the boundary instant
   * @param exceptionSupplier the supplier that provides the exception to be thrown if validation
   *     fails
   * @return the provided instant if it is in the past or present
   * @throws RuntimeException if the instant is not in the past or present; the thrown exception is
   *     provided by {@code exceptionSupplier}
   * @see #pastOrPresent(Instant, Instant)
   * @see #pastOrPresent(Instant, Instant, String)
   */
  @Contract("null, _, _ -> fail; _, null, _ -> fail; !null, !null, _ -> param1")
  public static Instant pastOrPresent(
      Instant instant, Instant boundary, Supplier<? extends RuntimeException> exceptionSupplier) {
    return EnsureDateTimeOps.pastOrPresent(instant, boundary, exceptionSupplier);
  }

  /**
   * Ensures that the provided value is positive.
   *
   * @param value the value to check
   * @return the provided value if it is positive
   * @throws EnsureException if the value is not positive, with the message {@code "number must be
   *     positive: %d"}
   * @see #positive(int, String)
   * @see #positive(int, Supplier)
   */
  @Contract("_ -> param1")
  public static int positive(int value) {
    return positive(value, NUMBER_MUST_BE_POSITIVE_FORMAT.formatted(value));
  }

  /**
   * Ensures that the provided value is positive.
   *
   * @param value the value to check
   * @param exceptionMessage the message to include in the exception if validation fails
   * @return the provided value if it is positive
   * @throws EnsureException if the value is not positive, with the provided message
   * @see #positive(int)
   * @see #positive(int, Supplier)
   */
  @Contract("_, _ -> param1")
  public static int positive(int value, String exceptionMessage) {
    return positive(value, () -> EnsureException.from(exceptionMessage));
  }

  /**
   * Ensures that the provided value is positive.
   *
   * @param value the value to check
   * @param exceptionSupplier the supplier that provides the exception to be thrown if validation
   *     fails
   * @return the provided value if it is positive
   * @throws RuntimeException if the value is not positive; the thrown exception is provided by
   *     {@code exceptionSupplier}
   * @see #positive(int)
   * @see #positive(int, String)
   */
  @Contract("_, _ -> param1")
  public static int positive(int value, Supplier<? extends RuntimeException> exceptionSupplier) {
    return EnsureNumberOps.positive(value, exceptionSupplier);
  }

  /**
   * Ensures that the provided value is positive.
   *
   * @param value the value to check
   * @return the provided value if it is positive
   * @throws EnsureException if the value is not positive, with the message {@code "number must be
   *     positive: %d"}
   * @see #positive(long, String)
   * @see #positive(long, Supplier)
   */
  @Contract("_ -> param1")
  public static long positive(long value) {
    return positive(value, NUMBER_MUST_BE_POSITIVE_FORMAT.formatted(value));
  }

  /**
   * Ensures that the provided value is positive.
   *
   * @param value the value to check
   * @param exceptionMessage the message to include in the exception if validation fails
   * @return the provided value if it is positive
   * @throws EnsureException if the value is not positive, with the provided message
   * @see #positive(long)
   * @see #positive(long, Supplier)
   */
  @Contract("_, _ -> param1")
  public static long positive(long value, String exceptionMessage) {
    return positive(value, () -> EnsureException.from(exceptionMessage));
  }

  /**
   * Ensures that the provided value is positive.
   *
   * @param value the value to check
   * @param exceptionSupplier the supplier that provides the exception to be thrown if validation
   *     fails
   * @return the provided value if it is positive
   * @throws RuntimeException if the value is not positive; the thrown exception is provided by
   *     {@code exceptionSupplier}
   * @see #positive(long)
   * @see #positive(long, String)
   */
  @Contract("_, _ -> param1")
  public static long positive(long value, Supplier<? extends RuntimeException> exceptionSupplier) {
    return EnsureNumberOps.positive(value, exceptionSupplier);
  }

  /**
   * Ensures that the provided value is positive or zero.
   *
   * @param value the value to check
   * @return the provided value if it is positive or zero
   * @throws EnsureException if the value is not positive or zero, with the message {@code "number
   *     must be positive or zero: %d"}
   * @see #positiveWithZero(int, String)
   * @see #positiveWithZero(int, Supplier)
   */
  @Contract("_ -> param1")
  public static int positiveWithZero(int value) {
    return positiveWithZero(value, NUMBER_MUST_BE_POSITIVE_OR_ZERO_FORMAT.formatted(value));
  }

  /**
   * Ensures that the provided value is positive or zero.
   *
   * @param value the value to check
   * @param exceptionMessage the message to include in the exception if validation fails
   * @return the provided value if it is positive or zero
   * @throws EnsureException if the value is not positive or zero, with the provided message
   * @see #positiveWithZero(int)
   * @see #positiveWithZero(int, Supplier)
   */
  @Contract("_, _ -> param1")
  public static int positiveWithZero(int value, String exceptionMessage) {
    return positiveWithZero(value, () -> EnsureException.from(exceptionMessage));
  }

  /**
   * Ensures that the provided value is positive or zero.
   *
   * @param value the value to check
   * @param exceptionSupplier the supplier that provides the exception to be thrown if validation
   *     fails
   * @return the provided value if it is positive or zero
   * @throws RuntimeException if the value is not positive or zero; the thrown exception is provided
   *     by {@code exceptionSupplier}
   * @see #positiveWithZero(int)
   * @see #positiveWithZero(int, String)
   */
  @Contract("_, _ -> param1")
  public static int positiveWithZero(
      int value, Supplier<? extends RuntimeException> exceptionSupplier) {
    return EnsureNumberOps.positiveWithZero(value, exceptionSupplier);
  }

  /**
   * Ensures that the provided value is positive or zero.
   *
   * @param value the value to check
   * @return the provided value if it is positive or zero
   * @throws EnsureException if the value is not positive or zero, with the message {@code "number
   *     must be positive or zero: %d"}
   * @see #positiveWithZero(long, String)
   * @see #positiveWithZero(long, Supplier)
   */
  @Contract("_ -> param1")
  public static long positiveWithZero(long value) {
    return positiveWithZero(value, NUMBER_MUST_BE_POSITIVE_OR_ZERO_FORMAT.formatted(value));
  }

  /**
   * Ensures that the provided value is positive or zero.
   *
   * @param value the value to check
   * @param exceptionMessage the message to include in the exception if validation fails
   * @return the provided value if it is positive or zero
   * @throws EnsureException if the value is not positive or zero, with the provided message
   * @see #positiveWithZero(long)
   * @see #positiveWithZero(long, Supplier)
   */
  @Contract("_, _ -> param1")
  public static long positiveWithZero(long value, String exceptionMessage) {
    return positiveWithZero(value, () -> EnsureException.from(exceptionMessage));
  }

  /**
   * Ensures that the provided value is positive or zero.
   *
   * @param value the value to check
   * @param exceptionSupplier the supplier that provides the exception to be thrown if validation
   *     fails
   * @return the provided value if it is positive or zero
   * @throws RuntimeException if the value is not positive or zero; the thrown exception is provided
   *     by {@code exceptionSupplier}
   * @see #positiveWithZero(long)
   * @see #positiveWithZero(long, String)
   */
  @Contract("_, _ -> param1")
  public static long positiveWithZero(
      long value, Supplier<? extends RuntimeException> exceptionSupplier) {
    return EnsureNumberOps.positiveWithZero(value, exceptionSupplier);
  }

  /**
   * Ensures that the provided string starts with the specified prefix.
   *
   * @param string the string to check
   * @param prefix the required prefix
   * @return the provided string if it starts with the prefix
   * @throws EnsureException if the string is {@code null} or does not start with the prefix, with
   *     the message {@code "string must start with %s"}
   * @see #startsWith(String, String, String)
   * @see #startsWith(String, String, Supplier)
   */
  @Contract("null, _ -> fail; !null, _ -> param1")
  public static String startsWith(String string, String prefix) {
    return startsWith(string, prefix, STRING_MUST_START_WITH_FORMAT.formatted(prefix));
  }

  /**
   * Ensures that the provided string starts with the specified prefix.
   *
   * @param string the string to check
   * @param prefix the required prefix
   * @param exceptionMessage the message to include in the exception if validation fails
   * @return the provided string if it starts with the prefix
   * @throws EnsureException if the string is {@code null} or does not start with the prefix, with
   *     the provided message
   * @see #startsWith(String, String)
   * @see #startsWith(String, String, Supplier)
   */
  @Contract("null, _, _ -> fail; !null, _, _ -> param1")
  public static String startsWith(String string, String prefix, String exceptionMessage) {
    return startsWith(string, prefix, () -> EnsureException.from(exceptionMessage));
  }

  /**
   * Ensures that the provided string starts with the specified prefix.
   *
   * @param string the string to check
   * @param prefix the required prefix
   * @param exceptionSupplier the supplier that provides the exception to be thrown if validation
   *     fails
   * @return the provided string if it starts with the prefix
   * @throws RuntimeException if the string is {@code null} or does not start with the prefix; the
   *     thrown exception is provided by {@code exceptionSupplier}
   * @see #startsWith(String, String)
   * @see #startsWith(String, String, String)
   */
  @Contract("null, _, _ -> fail; !null, _, _ -> param1")
  public static String startsWith(
      String string, String prefix, Supplier<? extends RuntimeException> exceptionSupplier) {
    return EnsureStringOps.startsWith(string, prefix, exceptionSupplier);
  }

  /**
   * Ensures that the provided object is of the specified type.
   *
   * @param <T> the expected type
   * @param object the object to check
   * @param clazz the expected class
   * @return the provided object, cast to the expected type if it is of that type
   * @throws EnsureException if the object is not of the specified type, with the message {@code
   *     "object must be a type of"}
   * @see #typeOf(Object, Class, String)
   * @see #typeOf(Object, Class, Supplier)
   */
  @Contract("null, _ -> fail; !null, _ -> param1")
  public static <T> T typeOf(Object object, Class<T> clazz) {
    return typeOf(object, clazz, OBJECT_MUST_BE_OF_TYPE_MESSAGE);
  }

  /**
   * Ensures that the provided object is of the specified type.
   *
   * @param <T> the expected type
   * @param object the object to check
   * @param clazz the expected class
   * @param exceptionMessage the message to include in the exception if validation fails
   * @return the provided object, cast to the expected type if it is of that type
   * @throws EnsureException if the object is not of the specified type, with the provided message
   * @see #typeOf(Object, Class)
   * @see #typeOf(Object, Class, Supplier)
   */
  @Contract("null, _, _ -> fail; !null, _, _ -> param1")
  public static <T> T typeOf(Object object, Class<T> clazz, String exceptionMessage) {
    return typeOf(object, clazz, () -> EnsureException.from(exceptionMessage));
  }

  /**
   * Ensures that the provided object is of the specified type.
   *
   * @param <T> the expected type
   * @param object the object to check
   * @param clazz the expected class
   * @param exceptionSupplier the supplier that provides the exception to be thrown if validation
   *     fails
   * @return the provided object, cast to the expected type if it is of that type
   * @throws RuntimeException if the object is not of the specified type; the thrown exception is
   *     provided by {@code exceptionSupplier}
   * @see #typeOf(Object, Class)
   * @see #typeOf(Object, Class, String)
   */
  @Contract("null, _, _ -> fail; !null, _, _ -> param1")
  public static <T> T typeOf(
      Object object, Class<T> clazz, Supplier<? extends RuntimeException> exceptionSupplier) {
    return EnsureObjectOps.typeOf(object, clazz, exceptionSupplier);
  }

  private Ensure() {
    throw new AssertionError("No Ensure4j for you!");
  }
}
