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

class EnsureNumberOpsTest implements EnsureOpsArchTest<EnsureNumberOps> {

  @Override
  public Class<EnsureNumberOps> clazz() {
    return EnsureNumberOps.class;
  }

  @Override
  public long expectedMethodCount() {
    return 12;
  }

  @Test
  @DisplayName("max should return value when less than or equal to boundary")
  void maxShouldReturnValueWhenLessThanOrEqualToBoundary() {
    assertThat(Ensure.max(5L, 10L)).isEqualTo(5L);
    assertThat(Ensure.max(10L, 10L)).isEqualTo(10L);
    assertThat(Ensure.max(5, 10)).isEqualTo(5);
    assertThat(Ensure.max(10, 10)).isEqualTo(10);
  }

  @Test
  @DisplayName("max should throw custom exception")
  void maxShouldThrowCustomException() {
    assertThatThrownBy(() -> Ensure.max(15L, 10L, () -> new IllegalArgumentException("custom")))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("custom");
    assertThatThrownBy(() -> Ensure.max(15, 10, () -> new IllegalArgumentException("custom")))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("custom");
  }

  @Test
  @DisplayName("max should throw exception when greater than boundary")
  void maxShouldThrowExceptionWhenGreaterThanBoundary() {
    assertThatThrownBy(() -> Ensure.max(15L, 10L))
        .isInstanceOf(EnsureException.class)
        .hasMessage("number must be less than or equal to 10, but was 15");
    assertThatThrownBy(() -> Ensure.max(15, 10))
        .isInstanceOf(EnsureException.class)
        .hasMessage("number must be less than or equal to 10, but was 15");
  }

  @Test
  @DisplayName("max should throw exception with custom message")
  void maxShouldThrowExceptionWithCustomMessage() {
    assertThatThrownBy(() -> Ensure.max(15L, 10L, "custom"))
        .isInstanceOf(EnsureException.class)
        .hasMessage("custom");
    assertThatThrownBy(() -> Ensure.max(15, 10, "custom"))
        .isInstanceOf(EnsureException.class)
        .hasMessage("custom");
  }

  @Test
  @DisplayName("min should return value when greater than or equal to boundary")
  void minShouldReturnValueWhenGreaterThanOrEqualToBoundary() {
    assertThat(Ensure.min(15L, 10L)).isEqualTo(15L);
    assertThat(Ensure.min(10L, 10L)).isEqualTo(10L);
    assertThat(Ensure.min(15, 10)).isEqualTo(15);
    assertThat(Ensure.min(10, 10)).isEqualTo(10);
  }

  @Test
  @DisplayName("min should throw custom exception")
  void minShouldThrowCustomException() {
    assertThatThrownBy(() -> Ensure.min(5L, 10L, () -> new IllegalArgumentException("custom")))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("custom");
    assertThatThrownBy(() -> Ensure.min(5, 10, () -> new IllegalArgumentException("custom")))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("custom");
  }

  @Test
  @DisplayName("min should throw exception when less than boundary")
  void minShouldThrowExceptionWhenLessThanBoundary() {
    assertThatThrownBy(() -> Ensure.min(5L, 10L))
        .isInstanceOf(EnsureException.class)
        .hasMessage("number must be greater than or equal to 10, but was 5");
    assertThatThrownBy(() -> Ensure.min(5, 10))
        .isInstanceOf(EnsureException.class)
        .hasMessage("number must be greater than or equal to 10, but was 5");
  }

  @Test
  @DisplayName("min should throw exception with custom message")
  void minShouldThrowExceptionWithCustomMessage() {
    assertThatThrownBy(() -> Ensure.min(5L, 10L, "custom"))
        .isInstanceOf(EnsureException.class)
        .hasMessage("custom");
    assertThatThrownBy(() -> Ensure.min(5, 10, "custom"))
        .isInstanceOf(EnsureException.class)
        .hasMessage("custom");
  }

  @Test
  @DisplayName("negative should return value when negative")
  void negativeShouldReturnValueWhenNegative() {
    assertThat(Ensure.negative(-1L)).isEqualTo(-1L);
    assertThat(Ensure.negative(-1)).isEqualTo(-1);
  }

  @Test
  @DisplayName("negative should throw custom exception")
  void negativeShouldThrowCustomException() {
    assertThatThrownBy(() -> Ensure.negative(0L, () -> new IllegalArgumentException("custom")))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("custom");
    assertThatThrownBy(() -> Ensure.negative(0, () -> new IllegalArgumentException("custom")))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("custom");
  }

  @Test
  @DisplayName("negative should throw exception when not negative")
  void negativeShouldThrowExceptionWhenNotNegative() {
    assertThatThrownBy(() -> Ensure.negative(0L))
        .isInstanceOf(EnsureException.class)
        .hasMessage("number must be negative: 0");
    assertThatThrownBy(() -> Ensure.negative(0))
        .isInstanceOf(EnsureException.class)
        .hasMessage("number must be negative: 0");
  }

  @Test
  @DisplayName("negative should throw exception with custom message")
  void negativeShouldThrowExceptionWithCustomMessage() {
    assertThatThrownBy(() -> Ensure.negative(0L, "custom"))
        .isInstanceOf(EnsureException.class)
        .hasMessage("custom");
    assertThatThrownBy(() -> Ensure.negative(0, "custom"))
        .isInstanceOf(EnsureException.class)
        .hasMessage("custom");
  }

  @Test
  @DisplayName("negativeWithZero should return value when negative or zero")
  void negativeWithZeroShouldReturnValueWhenNegativeOrZero() {
    assertThat(Ensure.negativeWithZero(-1L)).isEqualTo(-1L);
    assertThat(Ensure.negativeWithZero(0L)).isZero();
    assertThat(Ensure.negativeWithZero(-1)).isEqualTo(-1);
    assertThat(Ensure.negativeWithZero(0)).isZero();
  }

  @Test
  @DisplayName("negativeWithZero should throw custom exception")
  void negativeWithZeroShouldThrowCustomException() {
    assertThatThrownBy(
            () -> Ensure.negativeWithZero(1L, () -> new IllegalArgumentException("custom")))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("custom");
    assertThatThrownBy(
            () -> Ensure.negativeWithZero(1, () -> new IllegalArgumentException("custom")))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("custom");
  }

  @Test
  @DisplayName("negativeWithZero should throw exception when positive")
  void negativeWithZeroShouldThrowExceptionWhenPositive() {
    assertThatThrownBy(() -> Ensure.negativeWithZero(1L))
        .isInstanceOf(EnsureException.class)
        .hasMessage("number must be negative or zero: 1");
    assertThatThrownBy(() -> Ensure.negativeWithZero(1))
        .isInstanceOf(EnsureException.class)
        .hasMessage("number must be negative or zero: 1");
  }

  @Test
  @DisplayName("negativeWithZero should throw exception with custom message")
  void negativeWithZeroShouldThrowExceptionWithCustomMessage() {
    assertThatThrownBy(() -> Ensure.negativeWithZero(1L, "custom"))
        .isInstanceOf(EnsureException.class)
        .hasMessage("custom");
    assertThatThrownBy(() -> Ensure.negativeWithZero(1, "custom"))
        .isInstanceOf(EnsureException.class)
        .hasMessage("custom");
  }

  @Test
  @DisplayName("positive should return value when positive")
  void positiveShouldReturnValueWhenPositive() {
    assertThat(Ensure.positive(1L)).isEqualTo(1L);
    assertThat(Ensure.positive(1)).isEqualTo(1);
  }

  @Test
  @DisplayName("positive should throw custom exception")
  void positiveShouldThrowCustomException() {
    assertThatThrownBy(() -> Ensure.positive(0L, () -> new IllegalArgumentException("custom")))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("custom");
    assertThatThrownBy(() -> Ensure.positive(0, () -> new IllegalArgumentException("custom")))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("custom");
  }

  @Test
  @DisplayName("positive should throw exception when not positive")
  void positiveShouldThrowExceptionWhenNotPositive() {
    assertThatThrownBy(() -> Ensure.positive(0L))
        .isInstanceOf(EnsureException.class)
        .hasMessage("number must be positive: 0");
    assertThatThrownBy(() -> Ensure.positive(0))
        .isInstanceOf(EnsureException.class)
        .hasMessage("number must be positive: 0");
  }

  @Test
  @DisplayName("positive should throw exception with custom message")
  void positiveShouldThrowExceptionWithCustomMessage() {
    assertThatThrownBy(() -> Ensure.positive(0L, "custom"))
        .isInstanceOf(EnsureException.class)
        .hasMessage("custom");
    assertThatThrownBy(() -> Ensure.positive(0, "custom"))
        .isInstanceOf(EnsureException.class)
        .hasMessage("custom");
  }

  @Test
  @DisplayName("positiveWithZero should return value when positive or zero")
  void positiveWithZeroShouldReturnValueWhenPositiveOrZero() {
    assertThat(Ensure.positiveWithZero(1L)).isEqualTo(1L);
    assertThat(Ensure.positiveWithZero(0L)).isZero();
    assertThat(Ensure.positiveWithZero(1)).isEqualTo(1);
    assertThat(Ensure.positiveWithZero(0)).isZero();
  }

  @Test
  @DisplayName("positiveWithZero should throw custom exception")
  void positiveWithZeroShouldThrowCustomException() {
    assertThatThrownBy(
            () -> Ensure.positiveWithZero(-1L, () -> new IllegalArgumentException("custom")))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("custom");
    assertThatThrownBy(
            () -> Ensure.positiveWithZero(-1, () -> new IllegalArgumentException("custom")))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("custom");
  }

  @Test
  @DisplayName("positiveWithZero should throw exception when negative")
  void positiveWithZeroShouldThrowExceptionWhenNegative() {
    assertThatThrownBy(() -> Ensure.positiveWithZero(-1L))
        .isInstanceOf(EnsureException.class)
        .hasMessage("number must be positive or zero: -1");
    assertThatThrownBy(() -> Ensure.positiveWithZero(-1))
        .isInstanceOf(EnsureException.class)
        .hasMessage("number must be positive or zero: -1");
  }

  @Test
  @DisplayName("positiveWithZero should throw exception with custom message")
  void positiveWithZeroShouldThrowExceptionWithCustomMessage() {
    assertThatThrownBy(() -> Ensure.positiveWithZero(-1L, "custom"))
        .isInstanceOf(EnsureException.class)
        .hasMessage("custom");
    assertThatThrownBy(() -> Ensure.positiveWithZero(-1, "custom"))
        .isInstanceOf(EnsureException.class)
        .hasMessage("custom");
  }
}
