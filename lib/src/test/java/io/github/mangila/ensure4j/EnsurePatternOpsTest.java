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

import java.util.regex.Pattern;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EnsurePatternOpsTest implements EnsureOpsArchTest<EnsurePatternOps> {

  @Override
  public Class<EnsurePatternOps> clazz() {
    return EnsurePatternOps.class;
  }

  @Override
  public long expectedMethodCount() {
    return 3;
  }

  @Test
  @DisplayName("matchesAlphanumeric should return string when it matches alphanumeric pattern")
  void matchesAlphanumericShouldReturnStringWhenItMatchesAlphanumericPattern() {
    String value = "abc 123";
    assertThat(Ensure.matchesAlphanumeric(value)).isEqualTo(value);
  }

  @Test
  @DisplayName("matchesAlphanumeric should throw custom exception")
  void matchesAlphanumericShouldThrowCustomException() {
    assertThatThrownBy(
            () ->
                Ensure.matchesAlphanumeric("abc_123", () -> new IllegalArgumentException("custom")))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("custom");
  }

  @Test
  @DisplayName(
      "matchesAlphanumeric should throw exception when it does not match alphanumeric pattern")
  void matchesAlphanumericShouldThrowExceptionWhenItDoesNotMatchAlphanumericPattern() {
    assertThatThrownBy(() -> Ensure.matchesAlphanumeric("abc_123"))
        .isInstanceOf(EnsureException.class)
        .hasMessage("string must match alphanumeric pattern");
  }

  @Test
  @DisplayName("matchesAlphanumeric should throw exception with custom message")
  void matchesAlphanumericShouldThrowExceptionWithCustomMessage() {
    assertThatThrownBy(() -> Ensure.matchesAlphanumeric("abc_123", "custom"))
        .isInstanceOf(EnsureException.class)
        .hasMessage("custom");
  }

  @Test
  @DisplayName("matchesEmail should return string when it matches email pattern")
  void matchesEmailShouldReturnStringWhenItMatchesEmailPattern() {
    String value = "test@example.com";
    assertThat(Ensure.matchesEmail(value)).isEqualTo(value);
  }

  @Test
  @DisplayName("matchesEmail should throw custom exception")
  void matchesEmailShouldThrowCustomException() {
    assertThatThrownBy(
            () ->
                Ensure.matchesEmail("invalid-email", () -> new IllegalArgumentException("custom")))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("custom");
  }

  @Test
  @DisplayName("matchesEmail should throw exception when it does not match email pattern")
  void matchesEmailShouldThrowExceptionWhenItDoesNotMatchEmailPattern() {
    assertThatThrownBy(() -> Ensure.matchesEmail("invalid-email"))
        .isInstanceOf(EnsureException.class)
        .hasMessage("string must match email pattern");
  }

  @Test
  @DisplayName("matchesEmail should throw exception with custom message")
  void matchesEmailShouldThrowExceptionWithCustomMessage() {
    assertThatThrownBy(() -> Ensure.matchesEmail("invalid-email", "custom"))
        .isInstanceOf(EnsureException.class)
        .hasMessage("custom");
  }

  @Test
  @DisplayName("matches should return string when it matches pattern")
  void matchesShouldReturnStringWhenItMatchesPattern() {
    String value = "test";
    Pattern pattern = Pattern.compile("^t.*t$");
    assertThat(Ensure.matches(value, pattern)).isEqualTo(value);
  }

  @Test
  @DisplayName("matches should throw custom exception")
  void matchesShouldThrowCustomException() {
    Pattern pattern = Pattern.compile("^abc$");
    assertThatThrownBy(
            () -> Ensure.matches("test", pattern, () -> new IllegalArgumentException("custom")))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("custom");
  }

  @Test
  @DisplayName("matches should throw exception when it does not match pattern")
  void matchesShouldThrowExceptionWhenItDoesNotMatchPattern() {
    Pattern pattern = Pattern.compile("^abc$");
    assertThatThrownBy(() -> Ensure.matches("test", pattern))
        .isInstanceOf(EnsureException.class)
        .hasMessage("string must match regex: ^abc$");
  }

  @Test
  @DisplayName("matches should throw exception when string is null")
  void matchesShouldThrowExceptionWhenNull() {
    Pattern pattern = Pattern.compile("^abc$");
    assertThatThrownBy(() -> Ensure.matches(null, pattern))
        .isInstanceOf(EnsureException.class)
        .hasMessage("string must match regex: ^abc$");
  }

  @Test
  @DisplayName("matches should throw exception when pattern is null")
  void matchesShouldThrowExceptionWhenPatternIsNull() {
    assertThatThrownBy(() -> Ensure.matches("test", (Pattern) null))
        .isInstanceOf(EnsureException.class)
        .hasMessage("string must match regex: null");
  }

  @Test
  @DisplayName("matches should throw exception with custom message")
  void matchesShouldThrowExceptionWithCustomMessage() {
    Pattern pattern = Pattern.compile("^abc$");
    assertThatThrownBy(() -> Ensure.matches("test", pattern, "custom message"))
        .isInstanceOf(EnsureException.class)
        .hasMessage("custom message");
  }
}
