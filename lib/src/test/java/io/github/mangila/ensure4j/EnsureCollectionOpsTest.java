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

import java.util.Collection;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EnsureCollectionOpsTest implements EnsureOpsArchTest<EnsureCollectionOps> {

  @Override
  public Class<EnsureCollectionOps> clazz() {
    return EnsureCollectionOps.class;
  }

  @Override
  public long expectedMethodCount() {
    return 2;
  }

  @Test
  @DisplayName("containsElement should return collection when element is present")
  void containsElementShouldReturnCollectionWhenElementIsPresent() {
    List<String> list = List.of("test");
    assertThat(Ensure.containsElement(list, "test")).isEqualTo(list);
  }

  @Test
  @DisplayName("containsElement should throw custom exception")
  void containsElementShouldThrowCustomException() {
    List<String> list = List.of("test");
    assertThatThrownBy(
            () ->
                Ensure.containsElement(list, "other", () -> new IllegalArgumentException("custom")))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("custom");
  }

  @Test
  @DisplayName("containsElement should throw exception when element is not present")
  void containsElementShouldThrowExceptionWhenElementIsNotPresent() {
    List<String> list = List.of("test");
    assertThatThrownBy(() -> Ensure.containsElement(list, "other"))
        .isInstanceOf(EnsureException.class)
        .hasMessage("collection must contain element");
  }

  @Test
  @DisplayName("containsElement should throw exception with custom message")
  void containsElementShouldThrowExceptionWithCustomMessage() {
    List<String> list = List.of("test");
    assertThatThrownBy(() -> Ensure.containsElement(list, "other", "custom message"))
        .isInstanceOf(EnsureException.class)
        .hasMessage("custom message");
  }

  @Test
  @DisplayName("notEmpty should return collection when not empty")
  void notEmptyShouldReturnCollectionWhenNotEmpty() {
    List<String> list = List.of("test");
    assertThat(Ensure.notEmpty(list)).isEqualTo(list);
  }

  @Test
  @DisplayName("notEmpty should throw custom exception")
  void notEmptyShouldThrowCustomException() {
    List<String> list = List.of();
    assertThatThrownBy(() -> Ensure.notEmpty(list, () -> new IllegalArgumentException("custom")))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("custom");
  }

  @Test
  @DisplayName("notEmpty should throw exception when empty")
  void notEmptyShouldThrowExceptionWhenEmpty() {
    List<String> list = List.of();
    assertThatThrownBy(() -> Ensure.notEmpty(list))
        .isInstanceOf(EnsureException.class)
        .hasMessage("collection must not be empty or null");
  }

  @Test
  @DisplayName("notEmpty should throw exception when null")
  void notEmptyShouldThrowExceptionWhenNull() {
    assertThatThrownBy(() -> Ensure.notEmpty((Collection<?>) null))
        .isInstanceOf(EnsureException.class)
        .hasMessage("collection must not be empty or null");
  }

  @Test
  @DisplayName("notEmpty should throw exception with custom message")
  void notEmptyShouldThrowExceptionWithCustomMessage() {
    List<String> list = List.of();
    assertThatThrownBy(() -> Ensure.notEmpty(list, "custom message"))
        .isInstanceOf(EnsureException.class)
        .hasMessage("custom message");
  }
}
