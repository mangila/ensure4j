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
}
