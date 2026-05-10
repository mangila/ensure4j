package io.github.mangila.ensure4j.ops;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import io.github.mangila.ensure4j.EnsureException;
import org.junit.jupiter.api.Test;

class EnsureNumberOpsTest implements EnsureOpsTest<EnsureNumberOps> {

  @Override
  public Class<EnsureNumberOps> clazz() {
    return EnsureNumberOps.class;
  }

  @Override
  public EnsureNumberOps instance() {
    return EnsureNumberOps.INSTANCE;
  }

  @Override
  public long expectedPublicMethodCount() {
    return 38;
  }

  @Test
  void maxIntSuccess() {
    assertThat(instance().max(10, 5)).isEqualTo(5);
    assertThat(instance().max(10, 10)).isEqualTo(10);
  }

  @Test
  void maxIntFailure() {
    assertThatThrownBy(() -> instance().max(10, 11))
        .isInstanceOf(EnsureException.class)
        .hasMessage("value must be less than or equal to 10, but was 11");
  }

  @Test
  void maxLongSuccess() {
    assertThat(instance().max(10L, 5L)).isEqualTo(5L);
    assertThat(instance().max(10L, 10L)).isEqualTo(10L);
  }

  @Test
  void maxLongFailure() {
    assertThatThrownBy(() -> instance().max(10L, 11L))
        .isInstanceOf(EnsureException.class)
        .hasMessage("value must be less than or equal to 10, but was 11");
  }

  @Test
  void minIntSuccess() {
    assertThat(instance().min(5, 10)).isEqualTo(10);
    assertThat(instance().min(5, 5)).isEqualTo(5);
  }

  @Test
  void minIntFailure() {
    assertThatThrownBy(() -> instance().min(5, 4))
        .isInstanceOf(EnsureException.class)
        .hasMessage("value must be greater than or equal to 5, but was 4");
  }

  @Test
  void minLongSuccess() {
    assertThat(instance().min(5L, 10L)).isEqualTo(10L);
    assertThat(instance().min(5L, 5L)).isEqualTo(5L);
  }

  @Test
  void minLongFailure() {
    assertThatThrownBy(() -> instance().min(5L, 4L))
        .isInstanceOf(EnsureException.class)
        .hasMessage("value must be greater than or equal to 5, but was 4");
  }

  @Test
  void positiveIntSuccess() {
    assertThat(instance().positive(1)).isEqualTo(1);
  }

  @Test
  void positiveIntFailure() {
    assertThatThrownBy(() -> instance().positive(0))
        .isInstanceOf(EnsureException.class)
        .hasMessage("value must be positive - (0)");
  }

  @Test
  void positiveLongSuccess() {
    assertThat(instance().positive(1L)).isEqualTo(1L);
  }

  @Test
  void positiveLongFailure() {
    assertThatThrownBy(() -> instance().positive(0L))
        .isInstanceOf(EnsureException.class)
        .hasMessage("value must be positive - (0)");
  }

  @Test
  void negativeIntSuccess() {
    assertThat(instance().negative(-1)).isEqualTo(-1);
  }

  @Test
  void negativeIntFailure() {
    assertThatThrownBy(() -> instance().negative(0))
        .isInstanceOf(EnsureException.class)
        .hasMessage("value must be negative - (0)");
  }

  @Test
  void negativeLongSuccess() {
    assertThat(instance().negative(-1L)).isEqualTo(-1L);
  }

  @Test
  void negativeLongFailure() {
    assertThatThrownBy(() -> instance().negative(0L))
        .isInstanceOf(EnsureException.class)
        .hasMessage("value must be negative - (0)");
  }

  @Test
  void positiveWithZeroIntSuccess() {
    assertThat(instance().positiveWithZero(0)).isZero();
    assertThat(instance().positiveWithZero(1)).isEqualTo(1);
  }

  @Test
  void positiveWithZeroIntFailure() {
    assertThatThrownBy(() -> instance().positiveWithZero(-1))
        .isInstanceOf(EnsureException.class)
        .hasMessage("value must be positive or zero - (-1)");
  }

  @Test
  void positiveWithZeroLongSuccess() {
    assertThat(instance().positiveWithZero(0L)).isZero();
    assertThat(instance().positiveWithZero(1L)).isEqualTo(1L);
  }

  @Test
  void positiveWithZeroLongFailure() {
    assertThatThrownBy(() -> instance().positiveWithZero(-1L))
        .isInstanceOf(EnsureException.class)
        .hasMessage("value must be positive or zero - (-1)");
  }

  @Test
  void negativeWithZeroIntSuccess() {
    assertThat(instance().negativeWithZero(0)).isZero();
    assertThat(instance().negativeWithZero(-1)).isEqualTo(-1);
  }

  @Test
  void negativeWithZeroIntFailure() {
    assertThatThrownBy(() -> instance().negativeWithZero(1))
        .isInstanceOf(EnsureException.class)
        .hasMessage("value must be negative or zero - (1)");
  }

  @Test
  void negativeWithZeroLongSuccess() {
    assertThat(instance().negativeWithZero(0L)).isZero();
    assertThat(instance().negativeWithZero(-1L)).isEqualTo(-1L);
  }

  @Test
  void negativeWithZeroLongFailure() {
    assertThatThrownBy(() -> instance().negativeWithZero(1L))
        .isInstanceOf(EnsureException.class)
        .hasMessage("value must be negative or zero - (1)");
  }
}
