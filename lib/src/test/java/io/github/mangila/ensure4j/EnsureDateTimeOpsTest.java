package io.github.mangila.ensure4j;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.Instant;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EnsureDateTimeOpsTest implements EnsureOpsArchTest<EnsureDateTimeOps> {

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
    Instant now = Instant.now();
    Instant future = now.plusSeconds(10);
    assertThat(Ensure.future(future, now)).isEqualTo(future);
  }

  @Test
  @DisplayName("future should throw exception when not in the future")
  void futureShouldThrowExceptionWhenNotInTheFuture() {
    Instant now = Instant.now();
    Instant past = now.minusSeconds(10);
    assertThatThrownBy(() -> Ensure.future(past, now))
        .isInstanceOf(EnsureException.class)
        .hasMessage("instant must be in the future");
  }

  @Test
  @DisplayName("future should throw exception with custom message")
  void futureShouldThrowExceptionWithCustomMessage() {
    Instant now = Instant.now();
    Instant past = now.minusSeconds(10);
    assertThatThrownBy(() -> Ensure.future(past, now, "custom message"))
        .isInstanceOf(EnsureException.class)
        .hasMessage("custom message");
  }

  @Test
  @DisplayName("future should throw custom exception")
  void futureShouldThrowCustomException() {
    Instant now = Instant.now();
    Instant past = now.minusSeconds(10);
    assertThatThrownBy(() -> Ensure.future(past, now, () -> new IllegalArgumentException("custom")))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("custom");
  }

  @Test
  @DisplayName("pastOrPresent should return instant when in the past or present")
  void pastOrPresentShouldReturnInstantWhenInThePastOrPresent() {
    Instant now = Instant.now();
    Instant past = now.minusSeconds(10);
    assertThat(Ensure.pastOrPresent(past, now)).isEqualTo(past);
    assertThat(Ensure.pastOrPresent(now, now)).isEqualTo(now);
  }

  @Test
  @DisplayName("pastOrPresent should throw exception when in the future")
  void pastOrPresentShouldThrowExceptionWhenInTheFuture() {
    Instant now = Instant.now();
    Instant future = now.plusSeconds(10);
    assertThatThrownBy(() -> Ensure.pastOrPresent(future, now))
        .isInstanceOf(EnsureException.class)
        .hasMessage("instant must be in the past or present");
  }

  @Test
  @DisplayName("pastOrPresent should throw exception with custom message")
  void pastOrPresentShouldThrowExceptionWithCustomMessage() {
    Instant now = Instant.now();
    Instant future = now.plusSeconds(10);
    assertThatThrownBy(() -> Ensure.pastOrPresent(future, now, "custom message"))
        .isInstanceOf(EnsureException.class)
        .hasMessage("custom message");
  }

  @Test
  @DisplayName("pastOrPresent should throw custom exception")
  void pastOrPresentShouldThrowCustomException() {
    Instant now = Instant.now();
    Instant future = now.plusSeconds(10);
    assertThatThrownBy(
            () -> Ensure.pastOrPresent(future, now, () -> new IllegalArgumentException("custom")))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("custom");
  }
}
