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

import java.time.Instant;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EnsureDateTimeOpsTest implements EnsureOpsArchTest<EnsureDateTimeOps> {

  private static final Instant FIXED_TIME_POINT = Instant.parse("2026-05-07T10:00:00Z");

  @Override
  public Class<EnsureDateTimeOps> clazz() {
    return EnsureDateTimeOps.class;
  }

  @Override
  public long expectedMethodCount() {
    return 2;
  }

  @Test
  @DisplayName("future should return instant when in the future")
  void futureShouldReturnInstantWhenInTheFuture() {
    Instant future = FIXED_TIME_POINT.plusSeconds(10);
    assertThat(Ensure.future(future, FIXED_TIME_POINT)).isEqualTo(future);
  }

  @Test
  @DisplayName("future should throw custom exception")
  void futureShouldThrowCustomException() {
    Instant past = FIXED_TIME_POINT.minusSeconds(10);
    assertThatThrownBy(
            () ->
                Ensure.future(past, FIXED_TIME_POINT, () -> new IllegalArgumentException("custom")))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("custom");
  }

  @Test
  @DisplayName("future should throw exception when not in the future")
  void futureShouldThrowExceptionWhenNotInTheFuture() {
    Instant past = FIXED_TIME_POINT.minusSeconds(10);
    assertThatThrownBy(() -> Ensure.future(past, FIXED_TIME_POINT))
        .isInstanceOf(EnsureException.class)
        .hasMessage("instant must be in the future");
  }

  @Test
  @DisplayName("future should throw exception with custom message")
  void futureShouldThrowExceptionWithCustomMessage() {
    Instant past = FIXED_TIME_POINT.minusSeconds(10);
    assertThatThrownBy(() -> Ensure.future(past, FIXED_TIME_POINT, "custom message"))
        .isInstanceOf(EnsureException.class)
        .hasMessage("custom message");
  }

  @Test
  @DisplayName("future should throw exception when null")
  void futureShouldThrowWhenNull() {
    assertThatThrownBy(() -> Ensure.future(null, FIXED_TIME_POINT))
        .isInstanceOf(EnsureException.class)
        .hasMessage("instant must be in the future");
    assertThatThrownBy(() -> Ensure.future(FIXED_TIME_POINT, null))
        .isInstanceOf(EnsureException.class)
        .hasMessage("instant must be in the future");
  }

  @Test
  @DisplayName("pastOrPresent should return instant when in the past or present")
  void pastOrPresentShouldReturnInstantWhenInThePastOrPresent() {
    Instant past = FIXED_TIME_POINT.minusSeconds(10);
    assertThat(Ensure.pastOrPresent(past, FIXED_TIME_POINT)).isEqualTo(past);
    assertThat(Ensure.pastOrPresent(FIXED_TIME_POINT, FIXED_TIME_POINT))
        .isEqualTo(FIXED_TIME_POINT);
  }

  @Test
  @DisplayName("pastOrPresent should throw custom exception")
  void pastOrPresentShouldThrowCustomException() {
    Instant future = FIXED_TIME_POINT.plusSeconds(10);
    assertThatThrownBy(
            () ->
                Ensure.pastOrPresent(
                    future, FIXED_TIME_POINT, () -> new IllegalArgumentException("custom")))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("custom");
  }

  @Test
  @DisplayName("pastOrPresent should throw exception when in the future")
  void pastOrPresentShouldThrowExceptionWhenInTheFuture() {
    Instant future = FIXED_TIME_POINT.plusSeconds(10);
    assertThatThrownBy(() -> Ensure.pastOrPresent(future, FIXED_TIME_POINT))
        .isInstanceOf(EnsureException.class)
        .hasMessage("instant must be in the past or present");
  }

  @Test
  @DisplayName("pastOrPresent should throw exception with custom message")
  void pastOrPresentShouldThrowExceptionWithCustomMessage() {
    Instant future = FIXED_TIME_POINT.plusSeconds(10);
    assertThatThrownBy(() -> Ensure.pastOrPresent(future, FIXED_TIME_POINT, "custom message"))
        .isInstanceOf(EnsureException.class)
        .hasMessage("custom message");
  }

  @Test
  @DisplayName("pastOrPresent should throw exception when null")
  void pastOrPresentShouldThrowWhenNull() {
    assertThatThrownBy(() -> Ensure.pastOrPresent(null, FIXED_TIME_POINT))
        .isInstanceOf(EnsureException.class)
        .hasMessage("instant must be in the past or present");
    assertThatThrownBy(() -> Ensure.pastOrPresent(FIXED_TIME_POINT, null))
        .isInstanceOf(EnsureException.class)
        .hasMessage("instant must be in the past or present");
  }
}
