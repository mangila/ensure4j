package io.github.com.ensure4j.examples;

import io.github.mangila.ensure4j.Ensure;
import java.util.List;

public final class Order {

  private final int amount;
  private final List<String> items;

  public Order(int amount, List<String> items) {
    Ensure.positive(amount, "Amount must be positive");
    Ensure.notEmpty(items, "Items cannot be empty");
    this.amount = amount;
    this.items = items;
  }

  public int getAmount() {
    return amount;
  }

  public List<String> getItems() {
    return items;
  }
}
