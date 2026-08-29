package io.github.com.ensure4j.examples;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import io.github.mangila.ensure4j.EnsureException;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ExamplesTest {

  private final Examples examples = new Examples();

  @Test
  @DisplayName("streamCollection should succeed when all elements are valid")
  void streamCollectionShouldSucceedWhenAllElementsAreValid() {
    List<String> validList = List.of("hello se", "world se", "test123 se");
    assertThatCode(() -> examples.streamCollection(validList)).doesNotThrowAnyException();
  }

  @Test
  @DisplayName("streamCollection should throw EnsureException when list is null")
  void streamCollectionShouldThrowEnsureExceptionWhenListIsNull() {
    assertThatThrownBy(() -> examples.streamCollection(null))
        .isInstanceOf(EnsureException.class)
        .hasMessage("collection must not be empty or null");
  }

  @Test
  @DisplayName("streamCollection should throw EnsureException when list is empty")
  void streamCollectionShouldThrowEnsureExceptionWhenListIsEmpty() {
    List<String> emptyList = Collections.emptyList();
    assertThatThrownBy(() -> examples.streamCollection(emptyList))
        .isInstanceOf(EnsureException.class)
        .hasMessage("collection must not be empty or null");
  }

  @Test
  @DisplayName("streamCollection should throw EnsureException when list contains blank string")
  void streamCollectionShouldThrowEnsureExceptionWhenElementIsBlank() {
    List<String> listWithBlank = List.of("hello se", "   ");
    assertThatThrownBy(() -> examples.streamCollection(listWithBlank))
        .isInstanceOf(EnsureException.class)
        .hasMessage("string must not be blank");
  }

  @Test
  @DisplayName(
      "streamCollection should throw EnsureException when list contains non-alphanumeric string")
  void streamCollectionShouldThrowEnsureExceptionWhenElementIsNotAlphanumeric() {
    List<String> listWithSpecialChars = List.of("hello se", "invalid! se");
    assertThatThrownBy(() -> examples.streamCollection(listWithSpecialChars))
        .isInstanceOf(EnsureException.class)
        .hasMessage("string must match alphanumeric pattern");
  }

  @Test
  @DisplayName(
      "streamCollection should throw EnsureException when list contains string not ending with suffix")
  void streamCollectionShouldThrowEnsureExceptionWhenElementDoesNotEndWithSuffix() {
    List<String> listWithInvalidSuffix = List.of("hello se", "world se", "not a valid string");
    assertThatThrownBy(() -> examples.streamCollection(listWithInvalidSuffix))
        .isInstanceOf(EnsureException.class)
        .hasMessage("string must end with: se");
  }
}
