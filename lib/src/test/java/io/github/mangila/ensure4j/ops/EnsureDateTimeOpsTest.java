package io.github.mangila.ensure4j.ops;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import io.github.mangila.ensure4j.EnsureException;
import java.time.Instant;
import org.junit.jupiter.api.Test;

class EnsureDateTimeOpsTest implements EnsureOpsTest<EnsureDateTimeOps> {

  @Override
  public Class<EnsureDateTimeOps> clazz() {
    return EnsureDateTimeOps.class;
  }

  @Override
  public EnsureDateTimeOps instance() {
    return EnsureDateTimeOps.INSTANCE;
  }

  @Override
  public long expectedPublicMethodCount() {
    return 8;
  }

  @Test
  void isFutureSuccess() {
    Instant future = Instant.now().plusSeconds(3600);
    assertThat(instance().isFuture(future)).isEqualTo(future);
    assertThat(instance().isFuture(future, "message")).isEqualTo(future);
    assertThat(instance().isFuture(future, () -> new RuntimeException("custom"))).isEqualTo(future);
  }

  @Test
  void isFutureThrow() {
    Instant past = Instant.now().minusSeconds(3600);
    assertThatThrownBy(() -> instance().isFuture(past))
        .isInstanceOf(EnsureException.class)
        .hasMessage("instant must be in the future");
    assertThatThrownBy(() -> instance().isFuture(Instant.now()))
        .isInstanceOf(EnsureException.class);
    assertThatThrownBy(() -> instance().isFuture(null)).isInstanceOf(EnsureException.class);
    assertThatThrownBy(() -> instance().isFuture(past, "message"))
        .isInstanceOf(EnsureException.class)
        .hasMessage("message");
    assertThatThrownBy(() -> instance().isFuture(past, () -> new RuntimeException("custom")))
        .isInstanceOf(RuntimeException.class)
        .hasMessage("custom");
  }

  @Test
  void isPastOrPresentSuccess() {
    Instant past = Instant.now().minusSeconds(3600);
    Instant now = Instant.now();
    assertThat(instance().isPastOrPresent(past)).isEqualTo(past);
    assertThat(instance().isPastOrPresent(now)).isEqualTo(now);
    assertThat(instance().isPastOrPresent(past, "message")).isEqualTo(past);
    assertThat(instance().isPastOrPresent(past, () -> new RuntimeException("custom")))
        .isEqualTo(past);
  }

  @Test
  void isPastOrPresentThrow() {
    Instant future = Instant.now().plusSeconds(3600);
    assertThatThrownBy(() -> instance().isPastOrPresent(future))
        .isInstanceOf(EnsureException.class)
        .hasMessage("instant must be in the past or present");
    assertThatThrownBy(() -> instance().isPastOrPresent(null)).isInstanceOf(EnsureException.class);
    assertThatThrownBy(() -> instance().isPastOrPresent(future, "message"))
        .isInstanceOf(EnsureException.class)
        .hasMessage("message");
    assertThatThrownBy(
            () -> instance().isPastOrPresent(future, () -> new RuntimeException("custom")))
        .isInstanceOf(RuntimeException.class)
        .hasMessage("custom");
  }
}
