package io.github.com.ensure4j.examples.v3;

import io.github.mangila.ensure4j.Ensure;
import io.github.mangila.ensure4j.ops.EnsureCollectionOps;
import io.github.mangila.ensure4j.ops.EnsureNumberOps;

import java.util.List;

public class ExampleInReadme {

    private static final EnsureNumberOps ENSURE_NUMBER_OPS = Ensure.numbers();
    private static final EnsureCollectionOps ENSURE_COLLECTION_OPS = Ensure.collections();

    public static void main(String[] args) {
        var order = new Order(10, List.of("apple", "banana"));
        new ExampleInReadme().placeOrder(order);
    }

    private void placeOrder(Order order) {
        Ensure.notNull(order, () -> new OrderException("Order cannot be null"));
        ENSURE_NUMBER_OPS.positive(order.getAmount(), () -> new OrderException("Order amount must be positive"));
        ENSURE_COLLECTION_OPS.notEmpty(order.getItems(), () -> new OrderException("Order items cannot be empty"));
        // do business logic
    }

    private static class Order {
        private final int amount;
        private final List<String> items;

        public Order(int amount, List<String> items) {
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

    private static class OrderException extends RuntimeException {
        private OrderException(String string) {
            super(string);
        }
    }
}
