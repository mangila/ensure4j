package io.github.com.ensure4j.examples;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import io.github.mangila.ensure4j.EnsureException;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderTest {

  @Test
  @DisplayName("Should create order when amount is positive and items list is not empty")
  void shouldCreateOrderWhenInputsAreValid() {
    List<String> items = List.of("Book", "Pen");
    Order order = new Order(100, items);

    assertThat(order.getAmount()).isEqualTo(100);
    assertThat(order.getItems()).isEqualTo(items);
  }

  @Test
  @DisplayName("Should throw exception when amount is zero or negative")
  void shouldThrowExceptionWhenAmountIsNotPositive() {
    List<String> items = List.of("Book");
    assertThatThrownBy(() -> new Order(0, items))
        .isInstanceOf(EnsureException.class)
        .hasMessage("Amount must be positive");

    assertThatThrownBy(() -> new Order(-10, items))
        .isInstanceOf(EnsureException.class)
        .hasMessage("Amount must be positive");
  }

  @Test
  @DisplayName("Should throw exception when items is null")
  void shouldThrowExceptionWhenItemsIsNull() {
    assertThatThrownBy(() -> new Order(50, null))
        .isInstanceOf(EnsureException.class)
        .hasMessage("Items cannot be empty");
  }

  @Test
  @DisplayName("Should throw exception when items is empty")
  void shouldThrowExceptionWhenItemsIsEmpty() {
    List<String> emptyList = Collections.emptyList();
    assertThatThrownBy(() -> new Order(50, emptyList))
        .isInstanceOf(EnsureException.class)
        .hasMessage("Items cannot be empty");
  }
}
