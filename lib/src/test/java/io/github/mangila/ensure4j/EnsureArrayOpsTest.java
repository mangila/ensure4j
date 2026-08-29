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

class EnsureArrayOpsTest implements EnsureOpsArchTest<EnsureArrayOps> {

  @Override
  public Class<EnsureArrayOps> clazz() {
    return EnsureArrayOps.class;
  }

  @Override
  public long expectedMethodCount() {
    return 3;
  }

  @Test
  @DisplayName("deepEqualTo should return array when deeply equal")
  void deepEqualToShouldReturnArrayWhenDeeplyEqual() {
    String[][] array1 = {{"test"}};
    String[][] array2 = {{"test"}};
    assertThat(Ensure.deepEqualTo(array1, array2)).isEqualTo(array1);
  }

  @Test
  @DisplayName("deepEqualTo should throw exception when not deeply equal")
  void deepEqualToShouldThrowExceptionWhenNotDeeplyEqual() {
    String[][] array1 = {{"test1"}};
    String[][] array2 = {{"test2"}};
    assertThatThrownBy(() -> Ensure.deepEqualTo(array1, array2))
        .isInstanceOf(EnsureException.class)
        .hasMessage("arrays must be deep equal");
  }

  @Test
  @DisplayName("equalTo should return array when equal")
  void equalToShouldReturnArrayWhenEqual() {
    String[] array1 = {"test"};
    String[] array2 = {"test"};
    assertThat(Ensure.equalTo(array1, array2)).isEqualTo(array1);
  }

  @Test
  @DisplayName("equalTo should throw exception when not equal")
  void equalToShouldThrowExceptionWhenNotEqual() {
    String[] array1 = {"test1"};
    String[] array2 = {"test2"};
    assertThatThrownBy(() -> Ensure.equalTo(array1, array2))
        .isInstanceOf(EnsureException.class)
        .hasMessage("arrays must be equal");
  }

  @Test
  @DisplayName("notEmpty should return array when not empty")
  void notEmptyShouldReturnArrayWhenNotEmpty() {
    String[] array = {"test"};
    assertThat(Ensure.notEmpty(array)).isEqualTo(array);
  }

  @Test
  @DisplayName("notEmpty should throw custom exception")
  void notEmptyShouldThrowCustomException() {
    assertThatThrownBy(
            () -> Ensure.notEmpty(new String[0], () -> new IllegalArgumentException("custom")))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("custom");
  }

  @Test
  @DisplayName("notEmpty should throw exception when empty")
  void notEmptyShouldThrowExceptionWhenEmpty() {
    assertThatThrownBy(() -> Ensure.notEmpty(new String[0]))
        .isInstanceOf(EnsureException.class)
        .hasMessage("array must not be empty");
  }

  @Test
  @DisplayName("notEmpty should throw exception when null")
  void notEmptyShouldThrowExceptionWhenNull() {
    assertThatThrownBy(() -> Ensure.notEmpty((String[]) null))
        .isInstanceOf(EnsureException.class)
        .hasMessage("array must not be empty");
  }

  @Test
  @DisplayName("notEmpty should throw exception with custom message")
  void notEmptyShouldThrowExceptionWithCustomMessage() {
    assertThatThrownBy(() -> Ensure.notEmpty(new String[0], "custom message"))
        .isInstanceOf(EnsureException.class)
        .hasMessage("custom message");
  }
}
