package io.github.com.ensure4j.examples;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import io.github.mangila.ensure4j.EnsureException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PersonTest {

  @Test
  @DisplayName("Should create person when all inputs are valid")
  void shouldCreatePersonWhenInputsAreValid() {
    Instant now = Instant.now();
    Person person = new Person("Alice", 30, now);

    assertThat(person.name()).isEqualTo("Alice");
    assertThat(person.age()).isEqualTo(30);
    assertThat(person.birthDate()).isEqualTo(now);
  }

  @Test
  @DisplayName("Should throw exception when name is blank")
  void shouldThrowExceptionWhenNameIsBlank() {
    Instant now = Instant.now();
    assertThatThrownBy(() -> new Person("   ", 30, now))
        .isInstanceOf(EnsureException.class)
        .hasMessage("Name cannot be blank");
  }

  @Test
  @DisplayName("Should throw exception when name contains non-letter characters")
  void shouldThrowExceptionWhenNameContainsNonLetterCharacters() {
    Instant now = Instant.now();
    assertThatThrownBy(() -> new Person("Alice123", 30, now))
        .isInstanceOf(EnsureException.class)
        .hasMessage("Name must contain only letters");
  }

  @Test
  @DisplayName("Should throw exception when name is too short")
  void shouldThrowExceptionWhenNameIsTooShort() {
    Instant now = Instant.now();
    assertThatThrownBy(() -> new Person("A", 30, now))
        .isInstanceOf(EnsureException.class)
        .hasMessage("Name must be at least 2 characters long");
  }

  @Test
  @DisplayName("Should throw exception when name is too long")
  void shouldThrowExceptionWhenNameIsTooLong() {
    Instant now = Instant.now();
    assertThatThrownBy(() -> new Person("Abcdefghijklmnopqrstuvwxyz", 30, now))
        .isInstanceOf(EnsureException.class)
        .hasMessage("Name must be at most 20 characters long");
  }

  @Test
  @DisplayName("Should throw exception when age is negative or zero")
  void shouldThrowExceptionWhenAgeIsNotPositive() {
    Instant now = Instant.now();
    assertThatThrownBy(() -> new Person("Alice", 0, now))
        .isInstanceOf(EnsureException.class)
        .hasMessage("Age must be positive");
  }

  @Test
  @DisplayName("Should throw exception when age is less than 18")
  void shouldThrowExceptionWhenAgeIsLessThan18() {
    Instant now = Instant.now();
    assertThatThrownBy(() -> new Person("Alice", 17, now))
        .isInstanceOf(EnsureException.class)
        .hasMessage("Age must not be less than 18");
  }

  @Test
  @DisplayName("Should throw exception when age is greater than 120")
  void shouldThrowExceptionWhenAgeIsGreaterThan120() {
    Instant now = Instant.now();
    assertThatThrownBy(() -> new Person("Alice", 121, now))
        .isInstanceOf(EnsureException.class)
        .hasMessage("Age must not be greater than 120");
  }

  @Test
  @DisplayName("Should throw exception when birthDate is null")
  void shouldThrowExceptionWhenBirthDateIsNull() {
    assertThatThrownBy(() -> new Person("Alice", 30, null))
        .isInstanceOf(EnsureException.class)
        .hasMessage("Birth date cannot be null");
  }

  @Test
  @DisplayName("Should throw exception when birthDate is in the future")
  void shouldThrowExceptionWhenBirthDateIsInFuture() {
    Instant future = Instant.now().plus(1, ChronoUnit.DAYS);
    assertThatThrownBy(() -> new Person("Alice", 30, future))
        .isInstanceOf(EnsureException.class)
        .hasMessage("Birth date must be in the past or present");
  }
}
