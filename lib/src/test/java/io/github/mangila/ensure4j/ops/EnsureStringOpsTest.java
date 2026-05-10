package io.github.mangila.ensure4j.ops;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import io.github.mangila.ensure4j.EnsureException;
import org.junit.jupiter.api.Test;

class EnsureStringOpsTest implements EnsureOpsTest<EnsureStringOps> {

  @Override
  public Class<EnsureStringOps> clazz() {
    return EnsureStringOps.class;
  }

  @Override
  public EnsureStringOps instance() {
    return EnsureStringOps.INSTANCE;
  }

  @Override
  public long expectedPublicMethodCount() {
    return 19;
  }

  @Test
  void notBlankSuccess() {
    String value = "test";
    String result = instance().notBlank(value);
    assertThat(result).isEqualTo(value);
  }

  @Test
  void notBlankFailure() {
    assertThatThrownBy(() -> instance().notBlank(" "))
        .isInstanceOf(EnsureException.class)
        .hasMessage("string must not be blank");
    assertThatThrownBy(() -> instance().notBlank(null))
        .isInstanceOf(EnsureException.class)
        .hasMessage("string must not be blank");
  }

  @Test
  void notBlankCustomMessage() {
    assertThatThrownBy(() -> instance().notBlank(" ", "custom message"))
        .isInstanceOf(EnsureException.class)
        .hasMessage("custom message");
  }

  @Test
  void notBlankCustomSupplier() {
    assertThatThrownBy(
            () -> instance().notBlank(" ", () -> new RuntimeException("custom exception")))
        .isInstanceOf(RuntimeException.class)
        .hasMessage("custom exception");
  }

  @Test
  void notBlankOrElseSuccess() {
    assertThat(instance().notBlankOrElse("test", "default")).isEqualTo("test");
  }

  @Test
  void notBlankOrElseFallback() {
    assertThat(instance().notBlankOrElse("", "default")).isEqualTo("default");
    assertThat(instance().notBlankOrElse("  ", "default")).isEqualTo("default");
    assertThat(instance().notBlankOrElse(null, "default")).isEqualTo("default");
  }

  @Test
  void notBlankOrElseGetSuccess() {
    assertThat(instance().notBlankOrElseGet("test", () -> "default")).isEqualTo("test");
  }

  @Test
  void notBlankOrElseGetFallback() {
    assertThat(instance().notBlankOrElseGet(null, () -> "default")).isEqualTo("default");
    assertThat(instance().notBlankOrElseGet("", () -> "default")).isEqualTo("default");
    assertThat(instance().notBlankOrElseGet("  ", () -> "default")).isEqualTo("default");
  }

  @Test
  void notBlankOrElseGetNullSupplier() {
    assertThatThrownBy(() -> instance().notBlankOrElseGet(null, null))
        .isInstanceOf(EnsureException.class)
        .hasMessage("supplier was null");
  }

  @Test
  void minLengthSuccess() {
    String value = "abcd";
    assertThat(instance().minLength(3, value)).isEqualTo(value);
    assertThat(instance().minLength(4, value)).isEqualTo(value);
  }

  @Test
  void minLengthFailure() {
    assertThatThrownBy(() -> instance().minLength(5, "abcd"))
        .isInstanceOf(EnsureException.class)
        .hasMessage("string length must be at least 5");
    assertThatThrownBy(() -> instance().minLength(1, null))
        .isInstanceOf(EnsureException.class)
        .hasMessage("string length must be at least 1");
  }

  @Test
  void minLengthCustomMessage() {
    assertThatThrownBy(() -> instance().minLength(5, "abcd", "custom message"))
        .isInstanceOf(EnsureException.class)
        .hasMessage("custom message");
  }

  @Test
  void minLengthCustomSupplier() {
    assertThatThrownBy(
            () -> instance().minLength(5, "abcd", () -> new RuntimeException("custom exception")))
        .isInstanceOf(RuntimeException.class)
        .hasMessage("custom exception");
  }

  @Test
  void maxLengthSuccess() {
    String value = "abcd";
    assertThat(instance().maxLength(5, value)).isEqualTo(value);
    assertThat(instance().maxLength(4, value)).isEqualTo(value);
  }

  @Test
  void maxLengthFailure() {
    assertThatThrownBy(() -> instance().maxLength(3, "abcd"))
        .isInstanceOf(EnsureException.class)
        .hasMessage("string length must be at most 3");
    assertThatThrownBy(() -> instance().maxLength(3, null))
        .isInstanceOf(EnsureException.class)
        .hasMessage("string length must be at most 3");
  }

  @Test
  void maxLengthCustomMessage() {
    assertThatThrownBy(() -> instance().maxLength(3, "abcd", "custom message"))
        .isInstanceOf(EnsureException.class)
        .hasMessage("custom message");
  }

  @Test
  void maxLengthCustomSupplier() {
    assertThatThrownBy(
            () -> instance().maxLength(3, "abcd", () -> new RuntimeException("custom exception")))
        .isInstanceOf(RuntimeException.class)
        .hasMessage("custom exception");
  }

  @Test
  void startsWithSuccess() {
    String value = "hello world";
    assertThat(instance().startsWith("hello", value)).isEqualTo(value);
  }

  @Test
  void startsWithFailure() {
    assertThatThrownBy(() -> instance().startsWith("world", "hello world"))
        .isInstanceOf(EnsureException.class)
        .hasMessage("string must start with world");
    assertThatThrownBy(() -> instance().startsWith("hello", null))
        .isInstanceOf(EnsureException.class)
        .hasMessage("string must start with hello");
    assertThatThrownBy(() -> instance().startsWith(null, "hello world"))
        .isInstanceOf(EnsureException.class)
        .hasMessage("prefix must not be null");
  }

  @Test
  void startsWithCustomMessage() {
    assertThatThrownBy(() -> instance().startsWith("world", "hello world", "custom message"))
        .isInstanceOf(EnsureException.class)
        .hasMessage("custom message");
  }

  @Test
  void startsWithCustomSupplier() {
    assertThatThrownBy(
            () ->
                instance()
                    .startsWith(
                        "world", "hello world", () -> new RuntimeException("custom exception")))
        .isInstanceOf(RuntimeException.class)
        .hasMessage("custom exception");
  }

  @Test
  void endsWithSuccess() {
    String value = "hello world";
    assertThat(instance().endsWith("world", value)).isEqualTo(value);
  }

  @Test
  void endsWithFailure() {
    assertThatThrownBy(() -> instance().endsWith("hello", "hello world"))
        .isInstanceOf(EnsureException.class)
        .hasMessage("string must end with hello");
    assertThatThrownBy(() -> instance().endsWith("world", null))
        .isInstanceOf(EnsureException.class)
        .hasMessage("string must end with world");
    assertThatThrownBy(() -> instance().endsWith(null, "hello world"))
        .isInstanceOf(EnsureException.class)
        .hasMessage("suffix must not be null");
  }

  @Test
  void endsWithCustomMessage() {
    assertThatThrownBy(() -> instance().endsWith("hello", "hello world", "custom message"))
        .isInstanceOf(EnsureException.class)
        .hasMessage("custom message");
  }

  @Test
  void endsWithCustomSupplier() {
    assertThatThrownBy(
            () ->
                instance()
                    .endsWith(
                        "hello", "hello world", () -> new RuntimeException("custom exception")))
        .isInstanceOf(RuntimeException.class)
        .hasMessage("custom exception");
  }
}
