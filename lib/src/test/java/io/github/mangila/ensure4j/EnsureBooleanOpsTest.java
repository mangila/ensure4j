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

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EnsureBooleanOpsTest implements EnsureOpsArchTest<EnsureBooleanOps> {

  @Override
  public Class<EnsureBooleanOps> clazz() {
    return EnsureBooleanOps.class;
  }

  @Override
  public long expectedMethodCount() {
    return 2;
  }

  @Test
  @DisplayName("isFalse should not throw when false")
  void isFalseShouldNotThrowWhenFalse() {
    assertThatCode(() -> Ensure.isFalse(false)).doesNotThrowAnyException();
  }

  @Test
  @DisplayName("isFalse should throw custom exception")
  void isFalseShouldThrowCustomException() {
    assertThatThrownBy(() -> Ensure.isFalse(true, () -> new IllegalArgumentException("custom")))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("custom");
  }

  @Test
  @DisplayName("isFalse should throw exception when true")
  void isFalseShouldThrowExceptionWhenTrue() {
    assertThatThrownBy(() -> Ensure.isFalse(true))
        .isInstanceOf(EnsureException.class)
        .hasMessage("boolean must be false");
  }

  @Test
  @DisplayName("isFalse should throw exception with custom message")
  void isFalseShouldThrowExceptionWithCustomMessage() {
    assertThatThrownBy(() -> Ensure.isFalse(true, "custom message"))
        .isInstanceOf(EnsureException.class)
        .hasMessage("custom message");
  }

  @Test
  @DisplayName("isTrue should not throw when true")
  void isTrueShouldNotThrowWhenTrue() {
    assertThatCode(() -> Ensure.isTrue(true)).doesNotThrowAnyException();
  }

  @Test
  @DisplayName("isTrue should throw custom exception")
  void isTrueShouldThrowCustomException() {
    assertThatThrownBy(() -> Ensure.isTrue(false, () -> new IllegalArgumentException("custom")))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("custom");
  }

  @Test
  @DisplayName("isTrue should throw exception when false")
  void isTrueShouldThrowExceptionWhenFalse() {
    assertThatThrownBy(() -> Ensure.isTrue(false))
        .isInstanceOf(EnsureException.class)
        .hasMessage("boolean must be true");
  }

  @Test
  @DisplayName("isTrue should throw exception with custom message")
  void isTrueShouldThrowExceptionWithCustomMessage() {
    assertThatThrownBy(() -> Ensure.isTrue(false, "custom message"))
        .isInstanceOf(EnsureException.class)
        .hasMessage("custom message");
  }
}
