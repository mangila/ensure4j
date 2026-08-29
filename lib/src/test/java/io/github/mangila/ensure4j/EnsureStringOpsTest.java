/*
 * MIT License
 *
 * Copyright (c) 2025 Erik Olsson (olsson.erik1993@gmail.cm)
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EnsureStringOpsTest implements EnsureOpsArchTest<EnsureStringOps> {

  @Override
  public Class<EnsureStringOps> clazz() {
    return EnsureStringOps.class;
  }

  @Override
  public long expectedMethodCount() {
    return 6;
  }

  @Test
  @DisplayName("endsWith should return string when it ends with suffix")
  void endsWithShouldReturnStringWhenItEndsWithSuffix() {
    String value = "test";
    assertThat(Ensure.endsWith(value, "st")).isEqualTo(value);
  }

  @Test
  @DisplayName("endsWith should throw custom exception")
  void endsWithShouldThrowCustomException() {
    assertThatThrownBy(
            () -> Ensure.endsWith("test", "abc", () -> new IllegalArgumentException("custom")))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("custom");
  }

  @Test
  @DisplayName("endsWith should throw exception when it does not end with suffix")
  void endsWithShouldThrowExceptionWhenItDoesNotEndWithSuffix() {
    assertThatThrownBy(() -> Ensure.endsWith("test", "abc"))
        .isInstanceOf(EnsureException.class)
        .hasMessage("string must end with: abc");
  }

  @Test
  @DisplayName("endsWith should throw exception when string is null")
  void endsWithShouldThrowExceptionWhenStringIsNull() {
    assertThatThrownBy(() -> Ensure.endsWith(null, "abc"))
        .isInstanceOf(EnsureException.class)
        .hasMessage("string must end with: abc");
  }

  @Test
  @DisplayName("endsWith should throw exception when suffix is null")
  void endsWithShouldThrowExceptionWhenSuffixIsNull() {
    assertThatThrownBy(() -> Ensure.endsWith("test", null))
        .isInstanceOf(EnsureException.class)
        .hasMessage("string must end with: null");
  }

  @Test
  @DisplayName("endsWith should throw exception with custom message")
  void endsWithShouldThrowExceptionWithCustomMessage() {
    assertThatThrownBy(() -> Ensure.endsWith("test", "abc", "custom"))
        .isInstanceOf(EnsureException.class)
        .hasMessage("custom");
  }

  @Test
  @DisplayName("matches should return string when it matches regex")
  void matchesShouldReturnStringWhenItMatchesRegex() {
    String value = "test";
    assertThat(Ensure.matches(value, "^t.*t$")).isEqualTo(value);
  }

  @Test
  @DisplayName("matches should throw custom exception")
  void matchesShouldThrowCustomException() {
    assertThatThrownBy(
            () -> Ensure.matches("test", "^abc$", () -> new IllegalArgumentException("custom")))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("custom");
  }

  @Test
  @DisplayName("matches should throw exception when it does not match regex")
  void matchesShouldThrowExceptionWhenItDoesNotMatchRegex() {
    assertThatThrownBy(() -> Ensure.matches("test", "^abc$"))
        .isInstanceOf(EnsureException.class)
        .hasMessage("string must match regex: ^abc$");
  }

  @Test
  @DisplayName("matches should throw exception when string is null")
  void matchesShouldThrowExceptionWhenNull() {
    assertThatThrownBy(() -> Ensure.matches(null, "^abc$"))
        .isInstanceOf(EnsureException.class)
        .hasMessage("string must match regex: ^abc$");
  }

  @Test
  @DisplayName("matches should throw exception when regex is null")
  void matchesShouldThrowExceptionWhenRegexIsNull() {
    assertThatThrownBy(() -> Ensure.matches("test", (String) null))
        .isInstanceOf(EnsureException.class)
        .hasMessage("string must match regex: null");
  }

  @Test
  @DisplayName("matches should throw exception with custom message")
  void matchesShouldThrowExceptionWithCustomMessage() {
    assertThatThrownBy(() -> Ensure.matches("test", "^abc$", "custom message"))
        .isInstanceOf(EnsureException.class)
        .hasMessage("custom message");
  }

  @Test
  @DisplayName("maxLength should return string when length is less than or equal to boundary")
  void maxLengthShouldReturnStringWhenLengthIsLessThanOrEqualToBoundary() {
    String value = "test";
    assertThat(Ensure.maxLength(value, 4)).isEqualTo(value);
  }

  @Test
  @DisplayName("maxLength should throw custom exception")
  void maxLengthShouldThrowCustomException() {
    assertThatThrownBy(
            () -> Ensure.maxLength("abcde", 4, () -> new IllegalArgumentException("custom")))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("custom");
  }

  @Test
  @DisplayName("maxLength should throw exception when length is greater than boundary")
  void maxLengthShouldThrowExceptionWhenLengthIsGreaterThanBoundary() {
    assertThatThrownBy(() -> Ensure.maxLength("abcde", 4))
        .isInstanceOf(EnsureException.class)
        .hasMessage("string length must be at most 4 characters");
  }

  @Test
  @DisplayName("maxLength should throw exception with custom message")
  void maxLengthShouldThrowExceptionWithCustomMessage() {
    assertThatThrownBy(() -> Ensure.maxLength("abcde", 4, "custom"))
        .isInstanceOf(EnsureException.class)
        .hasMessage("custom");
  }

  @Test
  @DisplayName("maxLength should throw exception when string is null")
  void maxLengthShouldThrowWhenNull() {
    assertThatThrownBy(() -> Ensure.maxLength(null, 4))
        .isInstanceOf(EnsureException.class)
        .hasMessage("string length must be at most 4 characters");
  }

  @Test
  @DisplayName("minLength should return string when length is greater than or equal to boundary")
  void minLengthShouldReturnStringWhenLengthIsGreaterThanOrEqualToBoundary() {
    String value = "test";
    assertThat(Ensure.minLength(value, 4)).isEqualTo(value);
  }

  @Test
  @DisplayName("minLength should throw custom exception")
  void minLengthShouldThrowCustomException() {
    assertThatThrownBy(
            () -> Ensure.minLength("abc", 4, () -> new IllegalArgumentException("custom")))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("custom");
  }

  @Test
  @DisplayName("minLength should throw exception when length is less than boundary")
  void minLengthShouldThrowExceptionWhenLengthIsLessThanBoundary() {
    assertThatThrownBy(() -> Ensure.minLength("abc", 4))
        .isInstanceOf(EnsureException.class)
        .hasMessage("string length must be at least 4 characters");
  }

  @Test
  @DisplayName("minLength should throw exception with custom message")
  void minLengthShouldThrowExceptionWithCustomMessage() {
    assertThatThrownBy(() -> Ensure.minLength("abc", 4, "custom"))
        .isInstanceOf(EnsureException.class)
        .hasMessage("custom");
  }

  @Test
  @DisplayName("minLength should throw exception when string is null")
  void minLengthShouldThrowWhenNull() {
    assertThatThrownBy(() -> Ensure.minLength(null, 4))
        .isInstanceOf(EnsureException.class)
        .hasMessage("string length must be at least 4 characters");
  }

  @Test
  @DisplayName("notBlank should return string when not blank")
  void notBlankShouldReturnStringWhenNotBlank() {
    String value = "test";
    assertThat(Ensure.notBlank(value)).isEqualTo(value);
  }

  @Test
  @DisplayName("notBlank should throw custom exception")
  void notBlankShouldThrowCustomException() {
    assertThatThrownBy(() -> Ensure.notBlank(" ", () -> new IllegalArgumentException("custom")))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("custom");
  }

  @Test
  @DisplayName("notBlank should throw exception when blank")
  void notBlankShouldThrowExceptionWhenBlank() {
    assertThatThrownBy(() -> Ensure.notBlank(" "))
        .isInstanceOf(EnsureException.class)
        .hasMessage("string must not be blank");
  }

  @Test
  @DisplayName("notBlank should throw exception with custom message")
  void notBlankShouldThrowExceptionWithCustomMessage() {
    assertThatThrownBy(() -> Ensure.notBlank(" ", "custom"))
        .isInstanceOf(EnsureException.class)
        .hasMessage("custom");
  }

  @Test
  @DisplayName("notBlank should throw when null")
  void notBlankShouldThrowWhenNull() {
    assertThatThrownBy(() -> Ensure.notBlank(null, () -> new IllegalArgumentException("custom")))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("custom");
  }

  @Test
  @DisplayName("startsWith should return string when it starts with prefix")
  void startsWithShouldReturnStringWhenItStartsWithPrefix() {
    String value = "test";
    assertThat(Ensure.startsWith(value, "te")).isEqualTo(value);
  }

  @Test
  @DisplayName("startsWith should throw custom exception")
  void startsWithShouldThrowCustomException() {
    assertThatThrownBy(
            () -> Ensure.startsWith("test", "abc", () -> new IllegalArgumentException("custom")))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("custom");
  }

  @Test
  @DisplayName("startsWith should throw exception when it does not start with prefix")
  void startsWithShouldThrowExceptionWhenItDoesNotStartWithPrefix() {
    assertThatThrownBy(() -> Ensure.startsWith("test", "abc"))
        .isInstanceOf(EnsureException.class)
        .hasMessage("string must start with: abc");
  }

  @Test
  @DisplayName("startsWith should throw exception when prefix is null")
  void startsWithShouldThrowExceptionWhenPrefixIsNull() {
    assertThatThrownBy(() -> Ensure.startsWith("test", null))
        .isInstanceOf(EnsureException.class)
        .hasMessage("string must start with: null");
  }

  @Test
  @DisplayName("startsWith should throw exception when string is null")
  void startsWithShouldThrowExceptionWhenStringIsNull() {
    assertThatThrownBy(() -> Ensure.startsWith(null, "abc"))
        .isInstanceOf(EnsureException.class)
        .hasMessage("string must start with: abc");
  }

  @Test
  @DisplayName("startsWith should throw exception with custom message")
  void startsWithShouldThrowExceptionWithCustomMessage() {
    assertThatThrownBy(() -> Ensure.startsWith("test", "abc", "custom"))
        .isInstanceOf(EnsureException.class)
        .hasMessage("custom");
  }
}
