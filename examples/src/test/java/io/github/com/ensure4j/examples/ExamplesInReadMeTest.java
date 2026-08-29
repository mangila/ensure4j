package io.github.com.ensure4j.examples;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import io.github.mangila.ensure4j.EnsureException;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ExamplesInReadMeTest {

  private final ExamplesInReadMe examples = new ExamplesInReadMe();

  @Test
  @DisplayName("placeOrder1 should succeed with valid order")
  void placeOrder1ShouldSucceedWithValidOrder() {
    Order order = new Order(100, List.of("Item1"));
    assertThatCode(() -> examples.placeOrder1(order)).doesNotThrowAnyException();
  }

  @Test
  @DisplayName("placeOrder1 should throw EnsureException when order is null")
  void placeOrder1ShouldThrowEnsureExceptionWhenOrderIsNull() {
    assertThatThrownBy(() -> examples.placeOrder1(null))
        .isInstanceOf(EnsureException.class)
        .hasMessage("Order cannot be null");
  }

  @Test
  @DisplayName("placeOrder2 should succeed with valid order")
  void placeOrder2ShouldSucceedWithValidOrder() {
    Order order = new Order(100, List.of("Item1"));
    assertThatCode(() -> examples.placeOrder2(order)).doesNotThrowAnyException();
  }

  @Test
  @DisplayName("placeOrder2 should throw OrderException when order is null")
  void placeOrder2ShouldThrowOrderExceptionWhenOrderIsNull() {
    assertThatThrownBy(() -> examples.placeOrder2(null))
        .isInstanceOf(OrderException.class)
        .hasMessage("Order cannot be null");
  }

  @Test
  @DisplayName("placeOrder3 should succeed with valid order")
  void placeOrder3ShouldSucceedWithValidOrder() {
    Order order = new Order(100, List.of("Item1"));
    assertThatCode(() -> examples.placeOrder3(order)).doesNotThrowAnyException();
  }

  @Test
  @DisplayName("placeOrder3 should throw EnsureException when order is null")
  void placeOrder3ShouldThrowEnsureExceptionWhenOrderIsNull() {
    assertThatThrownBy(() -> examples.placeOrder3(null))
        .isInstanceOf(EnsureException.class)
        .hasMessage("object must not be null");
  }
}
