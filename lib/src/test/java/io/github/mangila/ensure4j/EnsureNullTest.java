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

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EnsureNullTest implements EnsureOpsArchTest<EnsureNullOps> {

  @Override
  public Class<EnsureNullOps> clazz() {
    return EnsureNullOps.class;
  }

  @Override
  public long expectedMethodCount() {
    return 1;
  }

  @Test
  @DisplayName("notNull should return object when not null")
  void notNullShouldReturnObjectWhenNotNull() {
    String value = "test";
    assertThat(Ensure.notNull(value)).isEqualTo(value);
  }

  @Test
  @DisplayName("notNull should throw custom exception")
  void notNullShouldThrowCustomException() {
    assertThatThrownBy(() -> Ensure.notNull(null, () -> new IllegalArgumentException("custom")))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("custom");
  }

  @Test
  @DisplayName("notNull should throw exception when null")
  void notNullShouldThrowExceptionWhenNull() {
    assertThatThrownBy(() -> Ensure.notNull(null))
        .isInstanceOf(EnsureException.class)
        .hasMessage("object must not be null");
  }

  @Test
  @DisplayName("notNull should throw exception with custom message")
  void notNullShouldThrowExceptionWithCustomMessage() {
    assertThatThrownBy(() -> Ensure.notNull(null, "custom message"))
        .isInstanceOf(EnsureException.class)
        .hasMessage("custom message");
  }
}
