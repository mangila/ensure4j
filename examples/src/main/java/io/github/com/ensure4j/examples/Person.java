package io.github.com.ensure4j.examples;

import io.github.mangila.ensure4j.Ensure;
import java.time.Instant;

public record Person(String name, int age, Instant birthDate) {

  public Person {
    Ensure.notBlank(name, "Name cannot be blank");
    Ensure.matches(name, "[a-zA-Z]+", "Name must contain only letters");
    Ensure.minLength(name, 2, "Name must be at least 2 characters long");
    Ensure.maxLength(name, 20, "Name must be at most 20 characters long");
    Ensure.positive(age, "Age must be positive");
    Ensure.min(age, 18, "Age must not be less than 18");
    Ensure.max(age, 120, "Age must not be greater than 120");
    Ensure.notNull(birthDate, "Birth date cannot be null");
    Ensure.pastOrPresent(birthDate, Instant.now(), "Birth date must be in the past or present");
  }
}
